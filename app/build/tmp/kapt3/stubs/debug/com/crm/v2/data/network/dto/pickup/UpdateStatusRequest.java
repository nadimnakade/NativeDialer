package com.crm.v2.data.network.dto.pickup;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 Q2\u00020\u0001:\u0002PQB\u00b5\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0002\u0010\u0016B\u00a1\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\u0017J\u000b\u00105\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\"J\u0010\u00109\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\"J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\"J\u0010\u0010<\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\"J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010%J\u0010\u0010A\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010%J\u00aa\u0001\u0010B\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\bH\u00c6\u0001\u00a2\u0006\u0002\u0010CJ\u0013\u0010D\u001a\u00020\u000e2\b\u0010E\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010F\u001a\u00020\u0003H\u00d6\u0001J\t\u0010G\u001a\u00020\u0005H\u00d6\u0001J&\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u00002\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u00c1\u0001\u00a2\u0006\u0002\bOR\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001f\u0010\u001bR \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010#\u0012\u0004\b \u0010\u0019\u001a\u0004\b!\u0010\"R \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010&\u0012\u0004\b$\u0010\u0019\u001a\u0004\b\r\u0010%R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\'\u0010\u0019\u001a\u0004\b\f\u0010\u001bR \u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010&\u0012\u0004\b(\u0010\u0019\u001a\u0004\b\u000f\u0010%R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u0019\u001a\u0004\b*\u0010\u001bR \u0010\u0012\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010#\u0012\u0004\b+\u0010\u0019\u001a\u0004\b,\u0010\"R \u0010\u0013\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010#\u0012\u0004\b-\u0010\u0019\u001a\u0004\b.\u0010\"R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u0019\u001a\u0004\b0\u0010\u001bR \u0010\t\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010#\u0012\u0004\b1\u0010\u0019\u001a\u0004\b2\u0010\"R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u0019\u001a\u0004\b4\u0010\u001b\u00a8\u0006R"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/UpdateStatusRequest;", "", "seen1", "", "key", "", "cpvStatus", "disbursedAmount", "", "salary", "callBackDate", "callBackTime", "isHLLAPBL", "isCibilBad", "", "isRealEstate", "remarks", "userKey", "latitude", "longitude", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)V", "getCallBackDate$annotations", "()V", "getCallBackDate", "()Ljava/lang/String;", "getCallBackTime$annotations", "getCallBackTime", "getCpvStatus$annotations", "getCpvStatus", "getDisbursedAmount$annotations", "getDisbursedAmount", "()Ljava/lang/Double;", "Ljava/lang/Double;", "isCibilBad$annotations", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "isHLLAPBL$annotations", "isRealEstate$annotations", "getKey$annotations", "getKey", "getLatitude$annotations", "getLatitude", "getLongitude$annotations", "getLongitude", "getRemarks$annotations", "getRemarks", "getSalary$annotations", "getSalary", "getUserKey$annotations", "getUserKey", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)Lcom/crm/v2/data/network/dto/pickup/UpdateStatusRequest;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"})
public final class UpdateStatusRequest {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String key = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String cpvStatus = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double disbursedAmount = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double salary = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String callBackDate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String callBackTime = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String isHLLAPBL = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean isCibilBad = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean isRealEstate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String remarks = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String userKey = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double latitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double longitude = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.network.dto.pickup.UpdateStatusRequest.Companion Companion = null;
    
    public UpdateStatusRequest(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String cpvStatus, @org.jetbrains.annotations.Nullable()
    java.lang.Double disbursedAmount, @org.jetbrains.annotations.Nullable()
    java.lang.Double salary, @org.jetbrains.annotations.Nullable()
    java.lang.String callBackDate, @org.jetbrains.annotations.Nullable()
    java.lang.String callBackTime, @org.jetbrains.annotations.Nullable()
    java.lang.String isHLLAPBL, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isCibilBad, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isRealEstate, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.Nullable()
    java.lang.String userKey, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getKey() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Key")
    @java.lang.Deprecated()
    public static void getKey$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCpvStatus() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "CPVStatus")
    @java.lang.Deprecated()
    public static void getCpvStatus$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getDisbursedAmount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DisbursedAmount")
    @java.lang.Deprecated()
    public static void getDisbursedAmount$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getSalary() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Salary")
    @java.lang.Deprecated()
    public static void getSalary$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCallBackDate() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "CallBackDate")
    @java.lang.Deprecated()
    public static void getCallBackDate$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCallBackTime() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "CallBackTime")
    @java.lang.Deprecated()
    public static void getCallBackTime$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String isHLLAPBL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "IsHLLAPBL")
    @java.lang.Deprecated()
    public static void isHLLAPBL$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isCibilBad() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "IsCibilBad")
    @java.lang.Deprecated()
    public static void isCibilBad$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isRealEstate() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "IsRealEstate")
    @java.lang.Deprecated()
    public static void isRealEstate$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRemarks() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Remarks")
    @java.lang.Deprecated()
    public static void getRemarks$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUserKey() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "UserKey")
    @java.lang.Deprecated()
    public static void getUserKey$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLatitude() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Latitude")
    @java.lang.Deprecated()
    public static void getLatitude$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLongitude() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Longitude")
    @java.lang.Deprecated()
    public static void getLongitude$annotations() {
    }
    
    public UpdateStatusRequest() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.network.dto.pickup.UpdateStatusRequest copy(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String cpvStatus, @org.jetbrains.annotations.Nullable()
    java.lang.Double disbursedAmount, @org.jetbrains.annotations.Nullable()
    java.lang.Double salary, @org.jetbrains.annotations.Nullable()
    java.lang.String callBackDate, @org.jetbrains.annotations.Nullable()
    java.lang.String callBackTime, @org.jetbrains.annotations.Nullable()
    java.lang.String isHLLAPBL, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isCibilBad, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isRealEstate, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.Nullable()
    java.lang.String userKey, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude) {
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
    com.crm.v2.data.network.dto.pickup.UpdateStatusRequest self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/crm/v2/data/network/dto/pickup/UpdateStatusRequest.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/crm/v2/data/network/dto/pickup/UpdateStatusRequest;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.crm.v2.data.network.dto.pickup.UpdateStatusRequest> {
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.data.network.dto.pickup.UpdateStatusRequest.$serializer INSTANCE = null;
        
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
        public com.crm.v2.data.network.dto.pickup.UpdateStatusRequest deserialize(@org.jetbrains.annotations.NotNull()
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
        com.crm.v2.data.network.dto.pickup.UpdateStatusRequest value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/UpdateStatusRequest$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/crm/v2/data/network/dto/pickup/UpdateStatusRequest;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.crm.v2.data.network.dto.pickup.UpdateStatusRequest> serializer() {
            return null;
        }
    }
}