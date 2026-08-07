package com.crm.v2.data.network.dto.pickup;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\bH\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 r2\u00020\u0001:\u0002qrB\u00a1\u0002\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0002\u0010\u001dB\u008d\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u001eJ\u000b\u0010M\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0091\u0002\u0010c\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010d\u001a\u00020e2\b\u0010f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010g\u001a\u00020\u0003H\u00d6\u0001J\t\u0010h\u001a\u00020\u0005H\u00d6\u0001J&\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020\u00002\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020oH\u00c1\u0001\u00a2\u0006\u0002\bpR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b#\u0010 \u001a\u0004\b$\u0010\"R\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b%\u0010 \u001a\u0004\b&\u0010\"R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\'\u0010 \u001a\u0004\b(\u0010\"R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b)\u0010 \u001a\u0004\b*\u0010\"R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b+\u0010 \u001a\u0004\b,\u0010\"R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b-\u0010 \u001a\u0004\b.\u0010\"R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b/\u0010 \u001a\u0004\b0\u0010\"R\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b1\u0010 \u001a\u0004\b2\u0010\"R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b3\u0010 \u001a\u0004\b4\u0010\"R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b5\u0010 \u001a\u0004\b6\u0010\"R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b7\u0010 \u001a\u0004\b8\u0010\"R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b9\u0010 \u001a\u0004\b:\u0010\"R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b;\u0010 \u001a\u0004\b<\u0010\"R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b=\u0010 \u001a\u0004\b>\u0010\"R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b?\u0010 \u001a\u0004\b@\u0010\"R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bA\u0010 \u001a\u0004\bB\u0010\"R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bC\u0010 \u001a\u0004\bD\u0010\"R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bE\u0010 \u001a\u0004\bF\u0010\"R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bG\u0010 \u001a\u0004\bH\u0010\"R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bI\u0010 \u001a\u0004\bJ\u0010\"R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bK\u0010 \u001a\u0004\bL\u0010\"\u00a8\u0006s"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/DialerLiveDto;", "", "seen1", "", "location", "", "tl", "caller", "dnd", "lead", "followUp", "ni", "notDoable", "wrongNo", "blank", "interested", "notContactable", "ringing", "totalCount", "totalClientConnected", "duration", "totalLoginAmt", "totalLogin", "totalDisbursedAmt", "totalDisbursedCount", "dailyLogin", "dailyLoginAmt", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBlank$annotations", "()V", "getBlank", "()Ljava/lang/String;", "getCaller$annotations", "getCaller", "getDailyLogin$annotations", "getDailyLogin", "getDailyLoginAmt$annotations", "getDailyLoginAmt", "getDnd$annotations", "getDnd", "getDuration$annotations", "getDuration", "getFollowUp$annotations", "getFollowUp", "getInterested$annotations", "getInterested", "getLead$annotations", "getLead", "getLocation$annotations", "getLocation", "getNi$annotations", "getNi", "getNotContactable$annotations", "getNotContactable", "getNotDoable$annotations", "getNotDoable", "getRinging$annotations", "getRinging", "getTl$annotations", "getTl", "getTotalClientConnected$annotations", "getTotalClientConnected", "getTotalCount$annotations", "getTotalCount", "getTotalDisbursedAmt$annotations", "getTotalDisbursedAmt", "getTotalDisbursedCount$annotations", "getTotalDisbursedCount", "getTotalLogin$annotations", "getTotalLogin", "getTotalLoginAmt$annotations", "getTotalLoginAmt", "getWrongNo$annotations", "getWrongNo", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"})
public final class DialerLiveDto {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String location = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String tl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String caller = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dnd = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lead = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String followUp = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ni = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String notDoable = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String wrongNo = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String blank = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String interested = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String notContactable = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ringing = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String totalCount = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String totalClientConnected = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String duration = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String totalLoginAmt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String totalLogin = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String totalDisbursedAmt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String totalDisbursedCount = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dailyLogin = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dailyLoginAmt = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.network.dto.pickup.DialerLiveDto.Companion Companion = null;
    
    public DialerLiveDto(@org.jetbrains.annotations.Nullable()
    java.lang.String location, @org.jetbrains.annotations.Nullable()
    java.lang.String tl, @org.jetbrains.annotations.Nullable()
    java.lang.String caller, @org.jetbrains.annotations.Nullable()
    java.lang.String dnd, @org.jetbrains.annotations.Nullable()
    java.lang.String lead, @org.jetbrains.annotations.Nullable()
    java.lang.String followUp, @org.jetbrains.annotations.Nullable()
    java.lang.String ni, @org.jetbrains.annotations.Nullable()
    java.lang.String notDoable, @org.jetbrains.annotations.Nullable()
    java.lang.String wrongNo, @org.jetbrains.annotations.Nullable()
    java.lang.String blank, @org.jetbrains.annotations.Nullable()
    java.lang.String interested, @org.jetbrains.annotations.Nullable()
    java.lang.String notContactable, @org.jetbrains.annotations.Nullable()
    java.lang.String ringing, @org.jetbrains.annotations.Nullable()
    java.lang.String totalCount, @org.jetbrains.annotations.Nullable()
    java.lang.String totalClientConnected, @org.jetbrains.annotations.Nullable()
    java.lang.String duration, @org.jetbrains.annotations.Nullable()
    java.lang.String totalLoginAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String totalLogin, @org.jetbrains.annotations.Nullable()
    java.lang.String totalDisbursedAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String totalDisbursedCount, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLogin, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLoginAmt) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLocation() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Location")
    @java.lang.Deprecated()
    public static void getLocation$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTl() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TL")
    @java.lang.Deprecated()
    public static void getTl$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCaller() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Caller")
    @java.lang.Deprecated()
    public static void getCaller$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDnd() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DND")
    @java.lang.Deprecated()
    public static void getDnd$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLead() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Lead")
    @java.lang.Deprecated()
    public static void getLead$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFollowUp() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "FollowUp")
    @java.lang.Deprecated()
    public static void getFollowUp$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNi() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "NI")
    @java.lang.Deprecated()
    public static void getNi$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNotDoable() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "NotDoable")
    @java.lang.Deprecated()
    public static void getNotDoable$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getWrongNo() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "WrongNo")
    @java.lang.Deprecated()
    public static void getWrongNo$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBlank() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Blank")
    @java.lang.Deprecated()
    public static void getBlank$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getInterested() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Interested")
    @java.lang.Deprecated()
    public static void getInterested$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNotContactable() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "NotContactable")
    @java.lang.Deprecated()
    public static void getNotContactable$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRinging() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ringing")
    @java.lang.Deprecated()
    public static void getRinging$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTotalCount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TotalCount")
    @java.lang.Deprecated()
    public static void getTotalCount$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTotalClientConnected() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TotalClientConnected")
    @java.lang.Deprecated()
    public static void getTotalClientConnected$annotations() {
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
    public final java.lang.String getTotalLoginAmt() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TotalLoginAmt")
    @java.lang.Deprecated()
    public static void getTotalLoginAmt$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTotalLogin() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TotalLogin")
    @java.lang.Deprecated()
    public static void getTotalLogin$annotations() {
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
    public final java.lang.String getTotalDisbursedCount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TotalDisbursedCount")
    @java.lang.Deprecated()
    public static void getTotalDisbursedCount$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDailyLogin() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DailyLogin")
    @java.lang.Deprecated()
    public static void getDailyLogin$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDailyLoginAmt() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DailyLoginAmt")
    @java.lang.Deprecated()
    public static void getDailyLoginAmt$annotations() {
    }
    
    public DialerLiveDto() {
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
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component22() {
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
    public final com.crm.v2.data.network.dto.pickup.DialerLiveDto copy(@org.jetbrains.annotations.Nullable()
    java.lang.String location, @org.jetbrains.annotations.Nullable()
    java.lang.String tl, @org.jetbrains.annotations.Nullable()
    java.lang.String caller, @org.jetbrains.annotations.Nullable()
    java.lang.String dnd, @org.jetbrains.annotations.Nullable()
    java.lang.String lead, @org.jetbrains.annotations.Nullable()
    java.lang.String followUp, @org.jetbrains.annotations.Nullable()
    java.lang.String ni, @org.jetbrains.annotations.Nullable()
    java.lang.String notDoable, @org.jetbrains.annotations.Nullable()
    java.lang.String wrongNo, @org.jetbrains.annotations.Nullable()
    java.lang.String blank, @org.jetbrains.annotations.Nullable()
    java.lang.String interested, @org.jetbrains.annotations.Nullable()
    java.lang.String notContactable, @org.jetbrains.annotations.Nullable()
    java.lang.String ringing, @org.jetbrains.annotations.Nullable()
    java.lang.String totalCount, @org.jetbrains.annotations.Nullable()
    java.lang.String totalClientConnected, @org.jetbrains.annotations.Nullable()
    java.lang.String duration, @org.jetbrains.annotations.Nullable()
    java.lang.String totalLoginAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String totalLogin, @org.jetbrains.annotations.Nullable()
    java.lang.String totalDisbursedAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String totalDisbursedCount, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLogin, @org.jetbrains.annotations.Nullable()
    java.lang.String dailyLoginAmt) {
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
    com.crm.v2.data.network.dto.pickup.DialerLiveDto self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/crm/v2/data/network/dto/pickup/DialerLiveDto.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/crm/v2/data/network/dto/pickup/DialerLiveDto;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_release"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.crm.v2.data.network.dto.pickup.DialerLiveDto> {
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.data.network.dto.pickup.DialerLiveDto.$serializer INSTANCE = null;
        
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
        public com.crm.v2.data.network.dto.pickup.DialerLiveDto deserialize(@org.jetbrains.annotations.NotNull()
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
        com.crm.v2.data.network.dto.pickup.DialerLiveDto value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/DialerLiveDto$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/crm/v2/data/network/dto/pickup/DialerLiveDto;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.crm.v2.data.network.dto.pickup.DialerLiveDto> serializer() {
            return null;
        }
    }
}