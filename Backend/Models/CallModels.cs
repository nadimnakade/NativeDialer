using System.Text.Json.Serialization;

namespace DialerAPI.Models;

public class PlaceCallRequest
{
    [JsonPropertyName("phone_number")]
    public string PhoneNumber { get; set; } = "";

    [JsonPropertyName("user_key")]
    public string UserKey { get; set; } = "";
}

public class PlaceCallResponse
{
    [JsonPropertyName("success")]
    public bool Success { get; set; }

    [JsonPropertyName("call_id")]
    public string CallId { get; set; } = "";

    [JsonPropertyName("message")]
    public string Message { get; set; } = "";
}

public class HangupCallRequest
{
    [JsonPropertyName("call_id")]
    public string CallId { get; set; } = "";

    [JsonPropertyName("user_key")]
    public string UserKey { get; set; } = "";
}

public class CallStatusResponse
{
    [JsonPropertyName("call_id")]
    public string CallId { get; set; } = "";

    [JsonPropertyName("state")]
    public string State { get; set; } = "";

    [JsonPropertyName("duration_seconds")]
    public int DurationSeconds { get; set; }

    [JsonPropertyName("callee")]
    public string Callee { get; set; } = "";

    [JsonPropertyName("user_key")]
    public string UserKey { get; set; } = "";
}

public enum CallSessionState
{
    Idle,
    Calling,
    Ringing,
    Connected,
    Held,
    Ended,
    Failed
}
