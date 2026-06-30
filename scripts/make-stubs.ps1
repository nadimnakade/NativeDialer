# Generate stub pjsua2.jar so the project compiles without real PJSIP
$srcDir = "$env:TEMP\pjsua2-stubs-src\org\pjsip\pjsua2"
$outDir = "$env:TEMP\pjsua2-stubs-out"
$jarPath = "D:\Git_Repos\Dialer\Frontend\app\libs\pjsua2.jar"

Remove-Item -Recurse -Force "$env:TEMP\pjsua2-stubs-src", $outDir -ErrorAction Ignore
New-Item -ItemType Directory -Path $srcDir -Force | Out-Null
New-Item -ItemType Directory -Path $outDir -Force | Out-Null

$classes = @(
    "public class LogConfig { public int level; }"
    "public class MediaConfig { public int clockRate;public int sndClockRate;public int channelCount;public int audioFramePtTime; }"
    "public class TransportConfig { public int port; }"
    "public class AuthCredInfo { public AuthCredInfo(){}public AuthCredInfo(String a,String b,String c,int d,String e){} }"
    "public class StringVector extends java.util.Vector<String> {}"
    "public class AuthCredInfoVector extends java.util.Vector<AuthCredInfo> {}"
    "public class RegConfig { public String registrarUri; }"
    "public class NatConfig { public boolean iceEnabled; }"
    "public class OnRegStateParam { public String accountId;public int code;public String reason; }"
    "public class OnIncomingCallParam { public int callId; }"
    "public class OnCallStateParam {}"
    "public class OnCallMediaStateParam {}"
    "public class ConnectDuration { public long sec; }"
    "public class CallMediaInfoVector { public long size(){return 0;}public CallMediaInfo get(int i){return new CallMediaInfo();} }"
    "public class CallMediaInfo { public pjmedia_type type = pjmedia_type.PJMEDIA_TYPE_NONE; }"
    "public class EpConfig { public LogConfig logConfig=new LogConfig();public MediaConfig mediaConfig=new MediaConfig(); }"
    "public class SipConfig { public AuthCredInfoVector authCreds=new AuthCredInfoVector();public StringVector proxies=new StringVector(); }"
    "public class AccountConfig { public String idUri;public RegConfig regConfig=new RegConfig();public SipConfig sipConfig=new SipConfig();public NatConfig natConfig=new NatConfig(); }"
    "public class CallInfo { public String callIdString;public String remoteUri;public pjsip_inv_state state=pjsip_inv_state.PJSIP_INV_STATE_NULL;public CallMediaInfoVector media=new CallMediaInfoVector();public ConnectDuration connectDuration=new ConnectDuration(); }"
    "public class CallOpParam { public CallOpParam(){}public CallOpParam(boolean a){}public pjsip_status_code statusCode; }"
    "public enum pjsip_transport_type_e { PJSIP_TRANSPORT_UDP,PJSIP_TRANSPORT_TCP }"
    "public enum pjsip_inv_state { PJSIP_INV_STATE_NULL,PJSIP_INV_STATE_CALLING,PJSIP_INV_STATE_CONNECTING,PJSIP_INV_STATE_CONFIRMED,PJSIP_INV_STATE_DISCONNECTED }"
    "public enum pjsip_status_code { PJSIP_SC_OK,PJSIP_SC_DECLINE }"
    "public enum pjmedia_type { PJMEDIA_TYPE_NONE,PJMEDIA_TYPE_AUDIO,PJMEDIA_TYPE_VIDEO }"
    "public class Endpoint { public void libCreate(){}public void libInit(EpConfig a){}public void libStart(){}public void libDestroy(){}public int transportCreate(pjsip_transport_type_e a,TransportConfig b){return 0;} }"
    "public class Account { protected Account(){}public void create(AccountConfig a){}public void modify(AccountConfig a){}public void shutdown(){}public void delete(){}protected void onRegState(OnRegStateParam a){}protected void onIncomingCall(OnIncomingCallParam a){} }"
    "public class Call { public Call(Account a,int b){}public void makeCall(String a,CallOpParam b){}public void answer(CallOpParam a){}public void hangup(CallOpParam a){}public void setHold(CallOpParam a){}public void reinvite(CallOpParam a){}public void xfer(String a,CallOpParam b){}public CallInfo info(){return new CallInfo();}public void delete(){}protected void onCallState(OnCallStateParam a){}protected void onCallMediaState(OnCallMediaStateParam a){} }"
)

foreach ($cls in $classes) {
    $name = if ($cls -match "(?:enum|class) (\w+)") { $matches[1] } else { continue }
    "package org.pjsip.pjsua2;$cls" | Set-Content -Path "$srcDir\$name.java"
}

$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
$files = Get-ChildItem "$srcDir\*.java" | ForEach-Object { $_.FullName }
& "$env:JAVA_HOME\bin\javac" -d $outDir $files 2>&1
if ($LASTEXITCODE -eq 0) {
    & "$env:JAVA_HOME\bin\jar" cf $jarPath -C $outDir .
    Write-Output "STUB JAR CREATED: $jarPath"
    exit 0
} else {
    Write-Output "FAILED"
    exit 1
}
