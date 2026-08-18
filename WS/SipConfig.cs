namespace WsClient;

public class SipConfig
{
    public string Server { get; set; } = "sip2.cloud-connect.in";
    public int Port { get; set; } = 7443;
    public string Username { get; set; } = "102724701";
    public string Password { get; set; } = "Tejas@001";
    public string Realm { get; set; } = "sip2.cloud-connect.in";
    public string UserAgent { get; set; } = "PJSUA";
    public bool UseTls { get; set; } = true;
}
