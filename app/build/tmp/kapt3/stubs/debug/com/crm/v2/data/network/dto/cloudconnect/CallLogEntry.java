package com.crm.v2.data.network.dto.cloudconnect;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 j2\u00020\u0001:\u0002ijB\u0089\u0002\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u00a2\u0006\u0002\u0010\u001bB\u00cd\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u001cJ\t\u0010G\u001a\u00020\u0005H\u00c6\u0003J\t\u0010H\u001a\u00020\u0005H\u00c6\u0003J\t\u0010I\u001a\u00020\u0005H\u00c6\u0003J\t\u0010J\u001a\u00020\u0005H\u00c6\u0003J\t\u0010K\u001a\u00020\u0005H\u00c6\u0003J\t\u0010L\u001a\u00020\u0005H\u00c6\u0003J\t\u0010M\u001a\u00020\u0005H\u00c6\u0003J\t\u0010N\u001a\u00020\u0005H\u00c6\u0003J\t\u0010O\u001a\u00020\u0005H\u00c6\u0003J\t\u0010P\u001a\u00020\u0005H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0005H\u00c6\u0003J\t\u0010R\u001a\u00020\u0005H\u00c6\u0003J\t\u0010S\u001a\u00020\u0005H\u00c6\u0003J\t\u0010T\u001a\u00020\u0005H\u00c6\u0003J\t\u0010U\u001a\u00020\u0005H\u00c6\u0003J\t\u0010V\u001a\u00020\u0005H\u00c6\u0003J\t\u0010W\u001a\u00020\u0005H\u00c6\u0003J\t\u0010X\u001a\u00020\u0005H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0005H\u00c6\u0003J\t\u0010Z\u001a\u00020\u0005H\u00c6\u0003J\u00d1\u0001\u0010[\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\\\u001a\u00020]2\b\u0010^\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010_\u001a\u00020\u0003H\u00d6\u0001J\t\u0010`\u001a\u00020\u0005H\u00d6\u0001J&\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020\u00002\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020gH\u00c1\u0001\u00a2\u0006\u0002\bhR\u001c\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001c\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001e\u001a\u0004\b\"\u0010 R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u001e\u001a\u0004\b$\u0010 R\u001c\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001e\u001a\u0004\b&\u0010 R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\'\u0010\u001e\u001a\u0004\b(\u0010 R\u001c\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001e\u001a\u0004\b*\u0010 R\u001c\u0010\u0018\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u001e\u001a\u0004\b,\u0010 R\u001c\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u001e\u001a\u0004\b.\u0010 R\u001c\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u001e\u001a\u0004\b0\u0010 R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b1\u0010\u001e\u001a\u0004\b2\u0010 R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u001e\u001a\u0004\b4\u0010 R\u001c\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001e\u001a\u0004\b6\u0010 R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b7\u0010\u001e\u001a\u0004\b8\u0010 R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001e\u001a\u0004\b:\u0010 R\u001c\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b;\u0010\u001e\u001a\u0004\b<\u0010 R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b=\u0010\u001e\u001a\u0004\b>\u0010 R\u001c\u0010\u0016\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b?\u0010\u001e\u001a\u0004\b@\u0010 R\u001c\u0010\u0017\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bA\u0010\u001e\u001a\u0004\bB\u0010 R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bC\u0010\u001e\u001a\u0004\bD\u0010 R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bE\u0010\u001e\u001a\u0004\bF\u0010 \u00a8\u0006k"}, d2 = {"Lcom/crm/v2/data/network/dto/cloudconnect/CallLogEntry;", "", "seen1", "", "callId", "", "tenantId", "startDate", "answerTime", "endDate", "caller", "callee", "forward", "callDirection", "did", "callSec", "callMinute", "answerSec", "answerMinute", "callStatus", "hangupBy", "hangupReason", "incomingCallCharges", "outgoingCallCharges", "callRecordingPath", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswerMinute$annotations", "()V", "getAnswerMinute", "()Ljava/lang/String;", "getAnswerSec$annotations", "getAnswerSec", "getAnswerTime$annotations", "getAnswerTime", "getCallDirection$annotations", "getCallDirection", "getCallId$annotations", "getCallId", "getCallMinute$annotations", "getCallMinute", "getCallRecordingPath$annotations", "getCallRecordingPath", "getCallSec$annotations", "getCallSec", "getCallStatus$annotations", "getCallStatus", "getCallee$annotations", "getCallee", "getCaller$annotations", "getCaller", "getDid$annotations", "getDid", "getEndDate$annotations", "getEndDate", "getForward$annotations", "getForward", "getHangupBy$annotations", "getHangupBy", "getHangupReason$annotations", "getHangupReason", "getIncomingCallCharges$annotations", "getIncomingCallCharges", "getOutgoingCallCharges$annotations", "getOutgoingCallCharges", "getStartDate$annotations", "getStartDate", "getTenantId$annotations", "getTenantId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"})
public final class CallLogEntry {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tenantId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String startDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String answerTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String endDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String caller = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callee = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String forward = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callDirection = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String did = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callSec = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callMinute = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String answerSec = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String answerMinute = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String hangupBy = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String hangupReason = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String incomingCallCharges = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String outgoingCallCharges = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callRecordingPath = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.network.dto.cloudconnect.CallLogEntry.Companion Companion = null;
    
    public CallLogEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String callId, @org.jetbrains.annotations.NotNull()
    java.lang.String tenantId, @org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String answerTime, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate, @org.jetbrains.annotations.NotNull()
    java.lang.String caller, @org.jetbrains.annotations.NotNull()
    java.lang.String callee, @org.jetbrains.annotations.NotNull()
    java.lang.String forward, @org.jetbrains.annotations.NotNull()
    java.lang.String callDirection, @org.jetbrains.annotations.NotNull()
    java.lang.String did, @org.jetbrains.annotations.NotNull()
    java.lang.String callSec, @org.jetbrains.annotations.NotNull()
    java.lang.String callMinute, @org.jetbrains.annotations.NotNull()
    java.lang.String answerSec, @org.jetbrains.annotations.NotNull()
    java.lang.String answerMinute, @org.jetbrains.annotations.NotNull()
    java.lang.String callStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String hangupBy, @org.jetbrains.annotations.NotNull()
    java.lang.String hangupReason, @org.jetbrains.annotations.NotNull()
    java.lang.String incomingCallCharges, @org.jetbrains.annotations.NotNull()
    java.lang.String outgoingCallCharges, @org.jetbrains.annotations.NotNull()
    java.lang.String callRecordingPath) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallId() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "callid")
    @java.lang.Deprecated()
    public static void getCallId$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTenantId() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "tenant_id")
    @java.lang.Deprecated()
    public static void getTenantId$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStartDate() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "start_date")
    @java.lang.Deprecated()
    public static void getStartDate$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAnswerTime() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "answer_time")
    @java.lang.Deprecated()
    public static void getAnswerTime$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEndDate() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "end_date")
    @java.lang.Deprecated()
    public static void getEndDate$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCaller() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "caller")
    @java.lang.Deprecated()
    public static void getCaller$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallee() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "callee")
    @java.lang.Deprecated()
    public static void getCallee$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getForward() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "forward")
    @java.lang.Deprecated()
    public static void getForward$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallDirection() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "call_direction")
    @java.lang.Deprecated()
    public static void getCallDirection$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDid() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "did")
    @java.lang.Deprecated()
    public static void getDid$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallSec() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "call_sec")
    @java.lang.Deprecated()
    public static void getCallSec$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallMinute() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "call_minute")
    @java.lang.Deprecated()
    public static void getCallMinute$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAnswerSec() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "answer_sec")
    @java.lang.Deprecated()
    public static void getAnswerSec$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAnswerMinute() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "answer_minute")
    @java.lang.Deprecated()
    public static void getAnswerMinute$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallStatus() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "call_status")
    @java.lang.Deprecated()
    public static void getCallStatus$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHangupBy() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "hangup_by")
    @java.lang.Deprecated()
    public static void getHangupBy$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHangupReason() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "hangup_reason")
    @java.lang.Deprecated()
    public static void getHangupReason$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIncomingCallCharges() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "incoming_call_charges")
    @java.lang.Deprecated()
    public static void getIncomingCallCharges$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOutgoingCallCharges() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "outgoing_call_charges")
    @java.lang.Deprecated()
    public static void getOutgoingCallCharges$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallRecordingPath() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "call_rec_path")
    @java.lang.Deprecated()
    public static void getCallRecordingPath$annotations() {
    }
    
    public CallLogEntry() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.network.dto.cloudconnect.CallLogEntry copy(@org.jetbrains.annotations.NotNull()
    java.lang.String callId, @org.jetbrains.annotations.NotNull()
    java.lang.String tenantId, @org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String answerTime, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate, @org.jetbrains.annotations.NotNull()
    java.lang.String caller, @org.jetbrains.annotations.NotNull()
    java.lang.String callee, @org.jetbrains.annotations.NotNull()
    java.lang.String forward, @org.jetbrains.annotations.NotNull()
    java.lang.String callDirection, @org.jetbrains.annotations.NotNull()
    java.lang.String did, @org.jetbrains.annotations.NotNull()
    java.lang.String callSec, @org.jetbrains.annotations.NotNull()
    java.lang.String callMinute, @org.jetbrains.annotations.NotNull()
    java.lang.String answerSec, @org.jetbrains.annotations.NotNull()
    java.lang.String answerMinute, @org.jetbrains.annotations.NotNull()
    java.lang.String callStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String hangupBy, @org.jetbrains.annotations.NotNull()
    java.lang.String hangupReason, @org.jetbrains.annotations.NotNull()
    java.lang.String incomingCallCharges, @org.jetbrains.annotations.NotNull()
    java.lang.String outgoingCallCharges, @org.jetbrains.annotations.NotNull()
    java.lang.String callRecordingPath) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void write$Self$app_debug(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.dto.cloudconnect.CallLogEntry self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/crm/v2/data/network/dto/cloudconnect/CallLogEntry.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/crm/v2/data/network/dto/cloudconnect/CallLogEntry;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.crm.v2.data.network.dto.cloudconnect.CallLogEntry> {
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.data.network.dto.cloudconnect.CallLogEntry.$serializer INSTANCE = null;
        
        private $serializer() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.crm.v2.data.network.dto.cloudconnect.CallLogEntry deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.descriptors.SerialDescriptor getDescriptor() {
            return null;
        }
        
        @java.lang.Override()
        public void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.crm.v2.data.network.dto.cloudconnect.CallLogEntry value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/crm/v2/data/network/dto/cloudconnect/CallLogEntry$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/crm/v2/data/network/dto/cloudconnect/CallLogEntry;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.crm.v2.data.network.dto.cloudconnect.CallLogEntry> serializer() {
            return null;
        }
    }
}