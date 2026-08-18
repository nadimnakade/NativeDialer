using System.Collections.Concurrent;

namespace WsClient;

public class LogBuffer
{
    private readonly ConcurrentQueue<string> _entries = new();
    private readonly ConcurrentQueue<SipEvent> _sipEvents = new();
    private const int MaxEntries = 500;
    private const int MaxEvents = 50;

    public void Write(string message)
    {
        var timestamp = DateTime.UtcNow.ToString("HH:mm:ss.fff");
        _entries.Enqueue($"[{timestamp}] {message}");
        while (_entries.Count > MaxEntries)
            _entries.TryDequeue(out _);
    }

    public void SipRequest(string direction, string message)
    {
        var lines = message.Split("\r\n");
        var startLine = lines.Length > 0 ? lines[0] : "";
        var headers = new Dictionary<string, string>();
        foreach (var line in lines.Skip(1))
        {
            var idx = line.IndexOf(':');
            if (idx > 0)
                headers[line[..idx].Trim()] = line[(idx + 1)..].Trim();
        }

        _sipEvents.Enqueue(new SipEvent
        {
            Timestamp = DateTime.UtcNow,
            Direction = direction,
            StartLine = startLine,
            Headers = headers,
            Body = lines.Length > 0 ? string.Join("\r\n", lines.SkipWhile(l => l != "")) : ""
        });
        while (_sipEvents.Count > MaxEvents)
            _sipEvents.TryDequeue(out _);

        Write($">> {startLine}");
    }

    public void SipResponse(string message)
    {
        var lines = message.Split("\r\n");
        var startLine = lines.Length > 0 ? lines[0] : "";
        var headers = new Dictionary<string, string>();
        foreach (var line in lines.Skip(1))
        {
            var idx = line.IndexOf(':');
            if (idx > 0)
                headers[line[..idx].Trim()] = line[(idx + 1)..].Trim();
        }

        _sipEvents.Enqueue(new SipEvent
        {
            Timestamp = DateTime.UtcNow,
            Direction = "received",
            StartLine = startLine,
            Headers = headers,
            Body = lines.Length > 0 ? string.Join("\r\n", lines.SkipWhile(l => l != "")) : ""
        });
        while (_sipEvents.Count > MaxEvents)
            _sipEvents.TryDequeue(out _);

        Write($"<< {startLine}");
    }

    public string[] GetRecent(int count = 100) =>
        _entries.Reverse().Take(count).Reverse().ToArray();

    public SipEvent[] GetSipEvents() =>
        _sipEvents.ToArray();
}

public class SipEvent
{
    public DateTime Timestamp { get; set; }
    public string Direction { get; set; } = "";
    public string StartLine { get; set; } = "";
    public Dictionary<string, string> Headers { get; set; } = new();
    public string Body { get; set; } = "";
}
