using System.Collections.Concurrent;
using DialerAPI.Models;

namespace DialerAPI.Services;

public class CallSession
{
    public string CallId { get; set; } = "";
    public string UserKey { get; set; } = "";
    public string Callee { get; set; } = "";
    public string SipCallId { get; set; } = "";
    public CallSessionState State { get; set; } = CallSessionState.Idle;
    public DateTime CreatedAt { get; set; } = DateTime.UtcNow;
    public DateTime? ConnectedAt { get; set; }
    public DateTime? EndedAt { get; set; }
    public string ErrorMessage { get; set; } = "";
}

public class CallSessionManager
{
    private readonly ConcurrentDictionary<string, CallSession> _sessions = new();
    private readonly ConcurrentDictionary<string, string> _sipCallIdToCallId = new();

    public CallSession CreateSession(string userKey, string callee)
    {
        var callId = Guid.NewGuid().ToString("N");
        var session = new CallSession
        {
            CallId = callId,
            UserKey = userKey,
            Callee = callee,
            State = CallSessionState.Calling,
            CreatedAt = DateTime.UtcNow
        };
        _sessions[callId] = session;
        return session;
    }

    public CallSession? GetSession(string callId)
    {
        _sessions.TryGetValue(callId, out var session);
        return session;
    }

    public CallSession? GetSessionBySipCallId(string sipCallId)
    {
        if (_sipCallIdToCallId.TryGetValue(sipCallId, out var callId))
            return GetSession(callId);
        return null;
    }

    public bool UpdateState(string callId, CallSessionState state)
    {
        if (_sessions.TryGetValue(callId, out var session))
        {
            session.State = state;
            if (state == CallSessionState.Connected)
                session.ConnectedAt = DateTime.UtcNow;
            if (state is CallSessionState.Ended or CallSessionState.Failed)
                session.EndedAt = DateTime.UtcNow;
            return true;
        }
        return false;
    }

    public void MapSipCallId(string callId, string sipCallId)
    {
        _sipCallIdToCallId[sipCallId] = callId;
        if (_sessions.TryGetValue(callId, out var session))
            session.SipCallId = sipCallId;
    }

    public CallSession? EndSession(string callId)
    {
        if (_sessions.TryRemove(callId, out var session))
        {
            session.State = CallSessionState.Ended;
            session.EndedAt = DateTime.UtcNow;
            if (!string.IsNullOrEmpty(session.SipCallId))
                _sipCallIdToCallId.TryRemove(session.SipCallId, out _);
            return session;
        }
        return null;
    }

    public IEnumerable<CallSession> GetActiveSessions()
    {
        return _sessions.Values.Where(s => s.State is
            CallSessionState.Calling or
            CallSessionState.Ringing or
            CallSessionState.Connected or
            CallSessionState.Held);
    }

    public IEnumerable<CallSession> GetUserSessions(string userKey)
    {
        return _sessions.Values.Where(s => s.UserKey == userKey);
    }
}
