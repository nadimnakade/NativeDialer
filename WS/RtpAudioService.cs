using System.Collections.Concurrent;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.Json;
using System.Threading.Channels;
using System.Net.WebSockets;
using Microsoft.Extensions.Logging;

namespace WsClient;

public class RtpAudioService : IDisposable
{
    private readonly ILogger<RtpAudioService> _logger;
    private UdpClient? _udpListener;
    private CancellationTokenSource? _cts;
    private readonly ConcurrentDictionary<string, AudioSession> _sessions = new();
    private IPEndPoint? _remoteEndpoint;
    private bool _running;

    private const int RTP_PORT = 40000;
    private const int SAMPLE_RATE = 8000;
    private const int FRAME_SIZE = 160;
    private const int PCM_FRAME_SIZE = FRAME_SIZE * 2;

    public RtpAudioService(ILogger<RtpAudioService> logger)
    {
        _logger = logger;
    }

    public void Start()
    {
        if (_running) return;
        _cts = new CancellationTokenSource();
        _udpListener = new UdpClient(RTP_PORT);
        _running = true;
        _ = ReceiveRtpLoopAsync(_cts.Token);
        _logger.LogInformation("RTP audio relay started on UDP port {Port}", RTP_PORT);
    }

    public void Stop()
    {
        _running = false;
        _cts?.Cancel();
        _udpListener?.Close();
        _udpListener = null;
        foreach (var s in _sessions.Values)
            s.Dispose();
        _sessions.Clear();
        _logger.LogInformation("RTP audio relay stopped");
    }

    public void SetRemoteEndpoint(string ip, int port)
    {
        _remoteEndpoint = new IPEndPoint(IPAddress.Parse(ip), port);
        _logger.LogInformation("RTP remote endpoint set to {Ip}:{Port}", ip, port);
    }

    public void RegisterSession(string sessionKey, AudioSession session)
    {
        _sessions[sessionKey] = session;
        _logger.LogInformation("Audio session registered: {Key}", sessionKey);
    }

    public void UnregisterSession(string sessionKey)
    {
        if (_sessions.TryRemove(sessionKey, out var session))
        {
            session.Dispose();
            _logger.LogInformation("Audio session unregistered: {Key}", sessionKey);
        }
    }

    private async Task ReceiveRtpLoopAsync(CancellationToken ct)
    {
        while (!ct.IsCancellationRequested && _running)
        {
            try
            {
                var result = await _udpListener!.ReceiveAsync(ct);
                var rtpPacket = result.Buffer;
                if (rtpPacket.Length < 12) continue;

                var remote = result.RemoteEndPoint;
                _remoteEndpoint ??= remote;

                var payloadType = rtpPacket[1] & 0x7F;
                var sequenceNumber = (rtpPacket[2] << 8) | rtpPacket[3];
                var timestamp = (rtpPacket[4] << 24) | (rtpPacket[5] << 16) | (rtpPacket[6] << 8) | rtpPacket[7];
                var ssrc = (rtpPacket[8] << 24) | (rtpPacket[9] << 16) | (rtpPacket[10] << 8) | rtpPacket[11];

                var headerLen = 12 + (rtpPacket[0] & 0x0F) * 4;
                if (headerLen >= rtpPacket.Length) continue;

                var payload = rtpPacket[headerLen..];

                byte[] pcmData;
                if (payloadType == 0)
                {
                    pcmData = PcmuToPcm(payload);
                }
                else if (payloadType == 8)
                {
                    pcmData = PcmaToPcm(payload);
                }
                else
                {
                    continue;
                }

                foreach (var session in _sessions.Values)
                {
                    try
                    {
                        await session.SendPcmToClient(pcmData);
                    }
                    catch { }
                }
            }
            catch (OperationCanceledException) { break; }
            catch (Exception ex)
            {
                _logger.LogWarning(ex, "RTP receive error");
            }
        }
    }

    public async Task SendRtpFromClient(byte[] pcmData, int payloadType = 0)
    {
        if (_remoteEndpoint == null || _udpListener == null) return;

        byte[] rtpPayload = payloadType == 0 ? PcmToPcmu(pcmData) : PcmToPcma(pcmData);
        var rtpPacket = new byte[12 + rtpPayload.Length];

        rtpPacket[0] = 0x80;
        rtpPacket[1] = (byte)payloadType;

        var seq = (ushort)(Environment.TickCount & 0xFFFF);
        rtpPacket[2] = (byte)(seq >> 8);
        rtpPacket[3] = (byte)(seq & 0xFF);

        var ts = (uint)(Environment.TickCount * 8 / 1000);
        rtpPacket[4] = (byte)(ts >> 24);
        rtpPacket[5] = (byte)(ts >> 16);
        rtpPacket[6] = (byte)(ts >> 8);
        rtpPacket[7] = (byte)(ts & 0xFF);

        var ssrc = 0x12345678;
        rtpPacket[8] = (byte)(ssrc >> 24);
        rtpPacket[9] = (byte)(ssrc >> 16);
        rtpPacket[10] = (byte)(ssrc >> 8);
        rtpPacket[11] = (byte)(ssrc & 0xFF);

        Array.Copy(rtpPayload, 0, rtpPacket, 12, rtpPayload.Length);

        await _udpListener.SendAsync(rtpPacket, rtpPacket.Length, _remoteEndpoint);
    }

    private static byte[] PcmuToPcm(byte[] pcmu)
    {
        short[] pcm = new short[pcmu.Length];
        for (int i = 0; i < pcmu.Length; i++)
        {
            pcm[i] = PcmuDecode(pcmu[i]);
        }
        var result = new byte[pcm.Length * 2];
        Buffer.BlockCopy(pcm, 0, result, 0, result.Length);
        return result;
    }

    private static byte[] PcmaToPcm(byte[] pcma)
    {
        short[] pcm = new short[pcma.Length];
        for (int i = 0; i < pcma.Length; i++)
        {
            pcm[i] = PcmaDecode(pcma[i]);
        }
        var result = new byte[pcm.Length * 2];
        Buffer.BlockCopy(pcm, 0, result, 0, result.Length);
        return result;
    }

    private static byte[] PcmToPcmu(short[] pcm)
    {
        byte[] result = new byte[pcm.Length];
        for (int i = 0; i < pcm.Length; i++)
        {
            result[i] = PcmuEncode(pcm[i]);
        }
        return result;
    }

    private static byte[] PcmToPcma(short[] pcm)
    {
        byte[] result = new byte[pcm.Length];
        for (int i = 0; i < pcm.Length; i++)
        {
            result[i] = PcmaEncode(pcm[i]);
        }
        return result;
    }

    private static byte[] PcmToPcmu(byte[] pcm16)
    {
        int samples = pcm16.Length / 2;
        short[] pcm = new short[samples];
        Buffer.BlockCopy(pcm16, 0, pcm, 0, pcm16.Length);
        byte[] result = new byte[samples];
        for (int i = 0; i < samples; i++)
        {
            result[i] = PcmuEncode(pcm[i]);
        }
        return result;
    }

    private static byte[] PcmToPcma(byte[] pcm16)
    {
        int samples = pcm16.Length / 2;
        short[] pcm = new short[samples];
        Buffer.BlockCopy(pcm16, 0, pcm, 0, pcm16.Length);
        byte[] result = new byte[samples];
        for (int i = 0; i < samples; i++)
        {
            result[i] = PcmaEncode(pcm[i]);
        }
        return result;
    }

    private static short PcmuDecode(byte uval)
    {
        uval = (byte)(~uval);
        int sign = (uval & 0x80) != 0 ? -1 : 1;
        uval &= 0x7F;
        int exponent = (uval >> 4) & 0x07;
        int mantissa = uval & 0x0F;
        int sample = ((mantissa << 1) + 0x21) << (exponent + 2);
        sample -= 0x84;
        return (short)(sign * sample);
    }

    private static byte PcmuEncode(short sample)
    {
        bool sign = sample < 0;
        if (sign) sample = (short)(-sample);
        if (sample > 0x7FFF) sample = 0x7FFF;

        int exponent = 7;
        int expMask;
        for (expMask = 0x4000; exponent > 0; exponent--, expMask >>= 1)
        {
            if (sample >= expMask) break;
        }

        int mantissa = (sample >> (exponent + 3)) & 0x0F;
        byte uval = (byte)(0x80 | (sign ? 0x80 : 0) | (exponent << 4) | mantissa);
        return (byte)(~uval);
    }

    private static short PcmaDecode(byte aval)
    {
        aval &= 0x7F;
        int sign = (aval & 0x80) != 0 ? -1 : 1;
        int exponent = (aval >> 4) & 0x07;
        int mantissa = aval & 0x0F;
        int sample = ((mantissa << 1) + 0x21) << (exponent + 3);
        sample -= 0x84;
        return (short)(sign * sample);
    }

    private static byte PcmaEncode(short sample)
    {
        bool sign = sample < 0;
        if (sign) sample = (short)(-sample);
        if (sample > 0x7FFF) sample = 0x7FFF;

        int exponent = 7;
        int expMask;
        for (expMask = 0x4000; exponent > 0; exponent--, expMask >>= 1)
        {
            if (sample >= expMask) break;
        }

        int mantissa = (sample >> (exponent + 3)) & 0x0F;
        byte aval = (byte)((sign ? 0x80 : 0) | (exponent << 4) | mantissa);
        return (byte)(~aval);
    }

    public void Dispose()
    {
        Stop();
    }
}

public class AudioSession : IDisposable
{
    private readonly WebSocket _webSocket;
    private readonly RtpAudioService _rtpService;
    private readonly ILogger _logger;
    private CancellationTokenSource? _cts;
    private bool _disposed;

    public AudioSession(WebSocket webSocket, RtpAudioService rtpService, ILogger logger)
    {
        _webSocket = webSocket;
        _rtpService = rtpService;
        _logger = logger;
        _cts = new CancellationTokenSource();
    }

    public async Task RunAsync(CancellationToken ct)
    {
        var linkedCts = CancellationTokenSource.CreateLinkedTokenSource(ct, _cts?.Token ?? CancellationToken.None);
        var buffer = new byte[4096];

        _ = SendKeepaliveLoopAsync(linkedCts.Token);

        try
        {
            while (_webSocket.State == WebSocketState.Open && !linkedCts.Token.IsCancellationRequested)
            {
                var result = await _webSocket.ReceiveAsync(new ArraySegment<byte>(buffer), linkedCts.Token);

                if (result.MessageType == WebSocketMessageType.Close)
                    break;

                if (result.MessageType == WebSocketMessageType.Binary && result.Count > 0)
                {
                    var pcmData = new byte[result.Count];
                    Array.Copy(buffer, pcmData, result.Count);
                    await _rtpService.SendRtpFromClient(pcmData);
                }
                else if (result.MessageType == WebSocketMessageType.Text && result.Count > 0)
                {
                    var msg = Encoding.UTF8.GetString(buffer, 0, result.Count);
                    try
                    {
                        var json = JsonDocument.Parse(msg);
                        var root = json.RootElement;
                        if (root.TryGetProperty("command", out var cmd) && cmd.GetString() == "start")
                        {
                            _logger.LogInformation("Audio session started via WebSocket");
                        }
                    }
                    catch { }
                }
            }
        }
        catch (OperationCanceledException) { }
        catch (WebSocketException) { }
        catch (Exception ex)
        {
            _logger.LogWarning(ex, "Audio session error");
        }

        linkedCts.Dispose();
    }

    public async Task SendPcmToClient(byte[] pcmData)
    {
        if (_webSocket.State != WebSocketState.Open || _disposed) return;
        try
        {
            await _webSocket.SendAsync(
                new ArraySegment<byte>(pcmData),
                WebSocketMessageType.Binary,
                true,
                CancellationToken.None);
        }
        catch { }
    }

    private async Task SendKeepaliveLoopAsync(CancellationToken ct)
    {
        try
        {
            while (!ct.IsCancellationRequested && _webSocket.State == WebSocketState.Open)
            {
                await Task.Delay(5000, ct);
                try
                {
                    var ping = "{\"type\":\"ping\"}";
                    var bytes = Encoding.UTF8.GetBytes(ping);
                    await _webSocket.SendAsync(new ArraySegment<byte>(bytes), WebSocketMessageType.Text, true, ct);
                }
                catch { break; }
            }
        }
        catch (OperationCanceledException) { }
    }

    public void Dispose()
    {
        if (_disposed) return;
        _disposed = true;
        _cts?.Cancel();
        _cts?.Dispose();
        try { _webSocket.CloseAsync(WebSocketCloseStatus.NormalClosure, "Session ended", CancellationToken.None).Wait(1000); } catch { }
    }
}
