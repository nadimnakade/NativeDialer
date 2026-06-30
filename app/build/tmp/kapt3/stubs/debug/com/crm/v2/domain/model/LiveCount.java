package com.crm.v2.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b1\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u00c3\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0018J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0010H\u00c6\u0003J\t\u00104\u001a\u00020\u0012H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0012H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0012H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\u0003H\u00c6\u0003J\t\u0010A\u001a\u00020\u0003H\u00c6\u0003J\u00c7\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u0012H\u00c6\u0001J\u0013\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010F\u001a\u00020\u0003H\u00d6\u0001J\t\u0010G\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0011\u0010\u0014\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001a\u00a8\u0006H"}, d2 = {"Lcom/crm/v2/domain/model/LiveCount;", "", "dnd", "", "lead", "followUp", "notInterested", "notDoable", "wrongNo", "blank", "interested", "notContactable", "ringing", "totalCount", "totalClientConnected", "duration", "", "totalLoginAmt", "", "totalLogin", "totalDisbursedAmt", "totalDisbursedCount", "dailyLogin", "dailyLoginAmt", "(IIIIIIIIIIIILjava/lang/String;DIDIID)V", "getBlank", "()I", "getDailyLogin", "getDailyLoginAmt", "()D", "getDnd", "getDuration", "()Ljava/lang/String;", "getFollowUp", "getInterested", "getLead", "getNotContactable", "getNotDoable", "getNotInterested", "getRinging", "getTotalClientConnected", "getTotalCount", "getTotalDisbursedAmt", "getTotalDisbursedCount", "getTotalLogin", "getTotalLoginAmt", "getWrongNo", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class LiveCount {
    private final int dnd = 0;
    private final int lead = 0;
    private final int followUp = 0;
    private final int notInterested = 0;
    private final int notDoable = 0;
    private final int wrongNo = 0;
    private final int blank = 0;
    private final int interested = 0;
    private final int notContactable = 0;
    private final int ringing = 0;
    private final int totalCount = 0;
    private final int totalClientConnected = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String duration = null;
    private final double totalLoginAmt = 0.0;
    private final int totalLogin = 0;
    private final double totalDisbursedAmt = 0.0;
    private final int totalDisbursedCount = 0;
    private final int dailyLogin = 0;
    private final double dailyLoginAmt = 0.0;
    
    public LiveCount(int dnd, int lead, int followUp, int notInterested, int notDoable, int wrongNo, int blank, int interested, int notContactable, int ringing, int totalCount, int totalClientConnected, @org.jetbrains.annotations.NotNull()
    java.lang.String duration, double totalLoginAmt, int totalLogin, double totalDisbursedAmt, int totalDisbursedCount, int dailyLogin, double dailyLoginAmt) {
        super();
    }
    
    public final int getDnd() {
        return 0;
    }
    
    public final int getLead() {
        return 0;
    }
    
    public final int getFollowUp() {
        return 0;
    }
    
    public final int getNotInterested() {
        return 0;
    }
    
    public final int getNotDoable() {
        return 0;
    }
    
    public final int getWrongNo() {
        return 0;
    }
    
    public final int getBlank() {
        return 0;
    }
    
    public final int getInterested() {
        return 0;
    }
    
    public final int getNotContactable() {
        return 0;
    }
    
    public final int getRinging() {
        return 0;
    }
    
    public final int getTotalCount() {
        return 0;
    }
    
    public final int getTotalClientConnected() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDuration() {
        return null;
    }
    
    public final double getTotalLoginAmt() {
        return 0.0;
    }
    
    public final int getTotalLogin() {
        return 0;
    }
    
    public final double getTotalDisbursedAmt() {
        return 0.0;
    }
    
    public final int getTotalDisbursedCount() {
        return 0;
    }
    
    public final int getDailyLogin() {
        return 0;
    }
    
    public final double getDailyLoginAmt() {
        return 0.0;
    }
    
    public LiveCount() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int component12() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    public final double component14() {
        return 0.0;
    }
    
    public final int component15() {
        return 0;
    }
    
    public final double component16() {
        return 0.0;
    }
    
    public final int component17() {
        return 0;
    }
    
    public final int component18() {
        return 0;
    }
    
    public final double component19() {
        return 0.0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.domain.model.LiveCount copy(int dnd, int lead, int followUp, int notInterested, int notDoable, int wrongNo, int blank, int interested, int notContactable, int ringing, int totalCount, int totalClientConnected, @org.jetbrains.annotations.NotNull()
    java.lang.String duration, double totalLoginAmt, int totalLogin, double totalDisbursedAmt, int totalDisbursedCount, int dailyLogin, double dailyLoginAmt) {
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
}