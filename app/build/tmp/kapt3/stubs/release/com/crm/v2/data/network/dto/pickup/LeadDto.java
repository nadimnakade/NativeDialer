package com.crm.v2.data.network.dto.pickup;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b7\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 V2\u00020\u0001:\u0002UVB\u00cd\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\u0002\u0010\u0017B\u00b9\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0018J\u000b\u00108\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010;\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u00c2\u0001\u0010G\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010HJ\u0013\u0010I\u001a\u00020\u00102\b\u0010J\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010K\u001a\u00020\u0003H\u00d6\u0001J\t\u0010L\u001a\u00020\u0005H\u00d6\u0001J&\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u00002\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020SH\u00c1\u0001\u00a2\u0006\u0002\bTR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010\u001cR\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001a\u001a\u0004\b\"\u0010\u001cR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u001a\u001a\u0004\b$\u0010\u001cR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001a\u001a\u0004\b&\u0010\u001cR \u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010)\u0012\u0004\b\'\u0010\u001a\u001a\u0004\b\u000f\u0010(R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u001a\u001a\u0004\b\u000e\u0010\u001cR \u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010)\u0012\u0004\b+\u0010\u001a\u001a\u0004\b\u0011\u0010(R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u001a\u001a\u0004\b-\u0010\u001cR\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b.\u0010\u001a\u001a\u0004\b/\u0010\u001cR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b0\u0010\u001a\u001a\u0004\b1\u0010\u001cR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b2\u0010\u001a\u001a\u0004\b3\u0010\u001cR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b4\u0010\u001a\u001a\u0004\b5\u0010\u001cR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b6\u0010\u001a\u001a\u0004\b7\u0010\u001c\u00a8\u0006W"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/LeadDto;", "", "seen1", "", "key", "", "clientName", "contact", "product", "salary", "company", "status", "callerName", "callBackDate", "isHLLAPBL", "isCibilBad", "", "isRealEstate", "remarks", "createdDate", "userKey", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCallBackDate$annotations", "()V", "getCallBackDate", "()Ljava/lang/String;", "getCallerName$annotations", "getCallerName", "getClientName$annotations", "getClientName", "getCompany$annotations", "getCompany", "getContact$annotations", "getContact", "getCreatedDate$annotations", "getCreatedDate", "isCibilBad$annotations", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "isHLLAPBL$annotations", "isRealEstate$annotations", "getKey$annotations", "getKey", "getProduct$annotations", "getProduct", "getRemarks$annotations", "getRemarks", "getSalary$annotations", "getSalary", "getStatus$annotations", "getStatus", "getUserKey$annotations", "getUserKey", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/crm/v2/data/network/dto/pickup/LeadDto;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"})
public final class LeadDto {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String key = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String clientName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String contact = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String product = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String salary = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String company = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String status = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String callerName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String callBackDate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String isHLLAPBL = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean isCibilBad = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean isRealEstate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String remarks = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String createdDate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String userKey = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.network.dto.pickup.LeadDto.Companion Companion = null;
    
    public LeadDto(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String clientName, @org.jetbrains.annotations.Nullable()
    java.lang.String contact, @org.jetbrains.annotations.Nullable()
    java.lang.String product, @org.jetbrains.annotations.Nullable()
    java.lang.String salary, @org.jetbrains.annotations.Nullable()
    java.lang.String company, @org.jetbrains.annotations.Nullable()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.String callerName, @org.jetbrains.annotations.Nullable()
    java.lang.String callBackDate, @org.jetbrains.annotations.Nullable()
    java.lang.String isHLLAPBL, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isCibilBad, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isRealEstate, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.Nullable()
    java.lang.String createdDate, @org.jetbrains.annotations.Nullable()
    java.lang.String userKey) {
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
    public final java.lang.String getClientName() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "ClientName")
    @java.lang.Deprecated()
    public static void getClientName$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getContact() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Contact")
    @java.lang.Deprecated()
    public static void getContact$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getProduct() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Product")
    @java.lang.Deprecated()
    public static void getProduct$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSalary() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Salary")
    @java.lang.Deprecated()
    public static void getSalary$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCompany() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Company")
    @java.lang.Deprecated()
    public static void getCompany$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStatus() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Status")
    @java.lang.Deprecated()
    public static void getStatus$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCallerName() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "CallerName")
    @java.lang.Deprecated()
    public static void getCallerName$annotations() {
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
    public final java.lang.String getCreatedDate() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "CreatedDate")
    @java.lang.Deprecated()
    public static void getCreatedDate$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUserKey() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "UserKey")
    @java.lang.Deprecated()
    public static void getUserKey$annotations() {
    }
    
    public LeadDto() {
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
    public final java.lang.Boolean component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
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
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.network.dto.pickup.LeadDto copy(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String clientName, @org.jetbrains.annotations.Nullable()
    java.lang.String contact, @org.jetbrains.annotations.Nullable()
    java.lang.String product, @org.jetbrains.annotations.Nullable()
    java.lang.String salary, @org.jetbrains.annotations.Nullable()
    java.lang.String company, @org.jetbrains.annotations.Nullable()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.String callerName, @org.jetbrains.annotations.Nullable()
    java.lang.String callBackDate, @org.jetbrains.annotations.Nullable()
    java.lang.String isHLLAPBL, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isCibilBad, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isRealEstate, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.Nullable()
    java.lang.String createdDate, @org.jetbrains.annotations.Nullable()
    java.lang.String userKey) {
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
    public static final void write$Self$app_release(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.dto.pickup.LeadDto self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/crm/v2/data/network/dto/pickup/LeadDto.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/crm/v2/data/network/dto/pickup/LeadDto;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_release"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.crm.v2.data.network.dto.pickup.LeadDto> {
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.data.network.dto.pickup.LeadDto.$serializer INSTANCE = null;
        
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
        public com.crm.v2.data.network.dto.pickup.LeadDto deserialize(@org.jetbrains.annotations.NotNull()
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
        com.crm.v2.data.network.dto.pickup.LeadDto value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/LeadDto$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/crm/v2/data/network/dto/pickup/LeadDto;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.crm.v2.data.network.dto.pickup.LeadDto> serializer() {
            return null;
        }
    }
}