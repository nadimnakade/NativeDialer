using System.Collections.Concurrent;
using System.Text.Json;
using System.Threading.Channels;
using Microsoft.AspNetCore.Mvc;

namespace WsClient.Services;

public class SseEvent
{
    [System.Text.Json.Serialization.JsonPropertyName("event")]
    public string Event { get; set; } = "";

    [System.Text.Json.Serialization.JsonPropertyName("call_id")]
    public string CallId { get; set; } = "";

    [System.Text.Json.Serialization.JsonPropertyName("state")]
    public string State { get; set; } = "";

    [System.Text.Json.Serialization.JsonPropertyName("message")]
    public string Message { get; set; } = "";

    [System.Text.Json.Serialization.JsonPropertyName("sip_call_id")]
    public string SipCallId { get; set; } = "";

    [System.Text.Json.Serialization.JsonPropertyName("remote_media_ip")]
    public string RemoteMediaIp { get; set; } = "";

    [System.Text.Json.Serialization.JsonPropertyName("remote_media_port")]
    public int RemoteMediaPort { get; set; }

    [System.Text.Json.Serialization.JsonPropertyName("codec")]
    public string Codec { get; set; } = "";
}

public interface ISseBroadcaster
{
    void Broadcast(SseEvent sseEvent);
    Channel<SseEvent> Subscribe();
    void Unsubscribe(Channel<SseEvent> channel);
}

public class SseBroadcaster : ISseBroadcaster
{
    private readonly ConcurrentDictionary<Channel<SseEvent>, byte> _clients = new();
    private readonly ILogger<SseBroadcaster> _logger;

    public SseBroadcaster(ILogger<SseBroadcaster> logger)
    {
        _logger = logger;
    }

    public void Broadcast(SseEvent sseEvent)
    {
        foreach (var kvp in _clients)
        {
            var channel = kvp.Key;
            if (!channel.Writer.TryWrite(sseEvent))
                _clients.TryRemove(channel, out _);
        }
    }

    public Channel<SseEvent> Subscribe()
    {
        var channel = Channel.CreateUnbounded<SseEvent>(new UnboundedChannelOptions
        {
            SingleReader = true,
            SingleWriter = false
        });
        _clients.TryAdd(channel, 0);
        _logger.LogInformation("SSE client connected ({Count} total)", _clients.Count);
        return channel;
    }

    public void Unsubscribe(Channel<SseEvent> channel)
    {
        _clients.TryRemove(channel, out _);
        _logger.LogInformation("SSE client disconnected ({Count} total)", _clients.Count);
    }
}
