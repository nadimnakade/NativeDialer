package com.crm.v2.data.network.dto.pickup;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b<\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 b2\u00020\u0001:\u0002abB\u00f1\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0002\u0010\u0019B\u00dd\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u001aJ\u000b\u0010A\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u00e1\u0001\u0010S\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010T\u001a\u00020U2\b\u0010V\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010W\u001a\u00020\u0003H\u00d6\u0001J\t\u0010X\u001a\u00020\u0005H\u00d6\u0001J&\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_H\u00c1\u0001\u00a2\u0006\u0002\b`R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u001c\u001a\u0004\b$\u0010\u001eR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010\u001eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\'\u0010\u001c\u001a\u0004\b(\u0010\u001eR\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001c\u001a\u0004\b*\u0010\u001eR\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u001c\u001a\u0004\b,\u0010\u001eR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u001c\u001a\u0004\b.\u0010\u001eR\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u001c\u001a\u0004\b0\u0010\u001eR\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b1\u0010\u001c\u001a\u0004\b2\u0010\u001eR\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u001c\u001a\u0004\b4\u0010\u001eR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001c\u001a\u0004\b6\u0010\u001eR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b7\u0010\u001c\u001a\u0004\b8\u0010\u001eR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001c\u001a\u0004\b:\u0010\u001eR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b;\u0010\u001c\u001a\u0004\b<\u0010\u001eR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b=\u0010\u001c\u001a\u0004\b>\u0010\u001eR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b?\u0010\u001c\u001a\u0004\b@\u0010\u001e\u00a8\u0006c"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/DashboardCounterDto;", "", "seen1", "", "rank", "", "points", "totalDisbursedAmt", "disbursedAmt", "clientConnected", "duration", "dailyLeadCount", "monthlyLeadCount", "monthlyLoginCount", "monthlyLoginAmt", "dailyLoginCount", "dailyLoginAmt", "incentive", "pl", "hl", "bl", "lap", "cc", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBl$annotations", "()V", "getBl", "()Ljava/lang/String;", "getCc$annotations", "getCc", "getClientConnected$annotations", "getClientConnected", "getDailyLeadCount$annotations", "getDailyLeadCount", "getDailyLoginAmt$annotations", "getDailyLoginAmt", "getDailyLoginCount$annotations", "getDailyLoginCount", "getDisbursedAmt$annotations", "getDisbursedAmt", "getDuration$annotations", "getDuration", "getHl$annotations", "getHl", "getIncentive$annotations", "getIncentive", "getLap$annotations", "getLap", "getMonthlyLeadCount$annotations", "getMonthlyLeadCount", "getMonthlyLoginAmt$annotations", "getMonthlyLoginAmt", "getMonthlyLoginCount$annotations", "getMonthlyLoginCount", "getPl$annotations", "getPl", "getPoints$annotations", "getPoints", "getRank$annotations", "getRank", "getTotalDisbursedAmt$annotations", "getTotalDisbursedAmt", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"})
public final class DashboardCounterDto {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String rank = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String points = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String totalDisbursedAmt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String disbursedAmt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String clientConnected = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String duration = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dailyLeadCount = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String monthlyLeadCount = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String monthlyLoginCount = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String monthlyLoginAmt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dailyLoginCount = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dailyLoginAmt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String incentive = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String pl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String hl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String bl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lap = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String cc = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.network.dto.pickup.DashboardCounterDto.Companion Companion = null;
    
    public DashboardCounterDto(@org.jetbrains.annotations.Nullable()
    java.lang.String rank, @org.jetbrains.annotations.Nullable()
    java.lang.String points, @org.jetbrains.annotations.Nullable()
    java.lang.String totalDisbursedAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String disbursedAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String clientConnected, @org.jetbrains.annotations.Nullable()
    java.lang.String duration, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLeadCount, @org.jetbrains.annotations.Nullable()
    java.lang.String monthlyLeadCount, @org.jetbrains.annotations.Nullable()
    java.lang.String monthlyLoginCount, @org.jetbrains.annotations.Nullable()
    java.lang.String monthlyLoginAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLoginCount, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLoginAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String incentive, @org.jetbrains.annotations.Nullable()
    java.lang.String pl, @org.jetbrains.annotations.Nullable()
    java.lang.String hl, @org.jetbrains.annotations.Nullable()
    java.lang.String bl, @org.jetbrains.annotations.Nullable()
    java.lang.String lap, @org.jetbrains.annotations.Nullable()
    java.lang.String cc) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRank() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Rank")
    @java.lang.Deprecated()
    public static void getRank$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPoints() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Points")
    @java.lang.Deprecated()
    public static void getPoints$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTotalDisbursedAmt() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TotalDisbursedAmt")
    @java.lang.Deprecated()
    public static void getTotalDisbursedAmt$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDisbursedAmt() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DisbursedAmt")
    @java.lang.Deprecated()
    public static void getDisbursedAmt$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getClientConnected() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "ClientConnected")
    @java.lang.Deprecated()
    public static void getClientConnected$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDuration() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Duration")
    @java.lang.Deprecated()
    public static void getDuration$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDailyLeadCount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DailyLeadCount")
    @java.lang.Deprecated()
    public static void getDailyLeadCount$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMonthlyLeadCount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "MonthlyLeadCount")
    @java.lang.Deprecated()
    public static void getMonthlyLeadCount$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMonthlyLoginCount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "MonthlyLoginCount")
    @java.lang.Deprecated()
    public static void getMonthlyLoginCount$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMonthlyLoginAmt() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "MonthlyLoginAmt")
    @java.lang.Deprecated()
    public static void getMonthlyLoginAmt$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDailyLoginCount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DailyLoginCount")
    @java.lang.Deprecated()
    public static void getDailyLoginCount$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDailyLoginAmt() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DailyLoginAmt")
    @java.lang.Deprecated()
    public static void getDailyLoginAmt$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getIncentive() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Incentive")
    @java.lang.Deprecated()
    public static void getIncentive$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPl() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "PL")
    @java.lang.Deprecated()
    public static void getPl$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getHl() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "HL")
    @java.lang.Deprecated()
    public static void getHl$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBl() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "BL")
    @java.lang.Deprecated()
    public static void getBl$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLap() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "LAP")
    @java.lang.Deprecated()
    public static void getLap$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCc() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "CC")
    @java.lang.Deprecated()
    public static void getCc$annotations() {
    }
    
    public DashboardCounterDto() {
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
    public final java.lang.String component12() {
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
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component18() {
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
    public final com.crm.v2.data.network.dto.pickup.DashboardCounterDto copy(@org.jetbrains.annotations.Nullable()
    java.lang.String rank, @org.jetbrains.annotations.Nullable()
    java.lang.String points, @org.jetbrains.annotations.Nullable()
    java.lang.String totalDisbursedAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String disbursedAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String clientConnected, @org.jetbrains.annotations.Nullable()
    java.lang.String duration, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLeadCount, @org.jetbrains.annotations.Nullable()
    java.lang.String monthlyLeadCount, @org.jetbrains.annotations.Nullable()
    java.lang.String monthlyLoginCount, @org.jetbrains.annotations.Nullable()
    java.lang.String monthlyLoginAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLoginCount, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLoginAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String incentive, @org.jetbrains.annotations.Nullable()
    java.lang.String pl, @org.jetbrains.annotations.Nullable()
    java.lang.String hl, @org.jetbrains.annotations.Nullable()
    java.lang.String bl, @org.jetbrains.annotations.Nullable()
    java.lang.String lap, @org.jetbrains.annotations.Nullable()
    java.lang.String cc) {
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
    com.crm.v2.data.network.dto.pickup.DashboardCounterDto self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/crm/v2/data/network/dto/pickup/DashboardCounterDto.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/crm/v2/data/network/dto/pickup/DashboardCounterDto;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_release"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.crm.v2.data.network.dto.pickup.DashboardCounterDto> {
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.data.network.dto.pickup.DashboardCounterDto.$serializer INSTANCE = null;
        
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
        public com.crm.v2.data.network.dto.pickup.DashboardCounterDto deserialize(@org.jetbrains.annotations.NotNull()
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
        com.crm.v2.data.network.dto.pickup.DashboardCounterDto value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/DashboardCounterDto$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/crm/v2/data/network/dto/pickup/DashboardCounterDto;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.crm.v2.data.network.dto.pickup.DashboardCounterDto> serializer() {
            return null;
        }
    }
}