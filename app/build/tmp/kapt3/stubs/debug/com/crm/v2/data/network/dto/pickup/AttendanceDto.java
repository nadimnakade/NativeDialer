package com.crm.v2.data.network.dto.pickup;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ;2\u00020\u0001:\u0002:;Bm\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000eBY\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010$\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001bJ\u0010\u0010%\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001bJ\u0010\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013Jb\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001J\t\u00100\u001a\u000201H\u00d6\u0001J&\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u00c1\u0001\u00a2\u0006\u0002\b9R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0013R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0013R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u001c\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u001bR \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u001c\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u001bR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b\u001f\u0010\u0011\u001a\u0004\b \u0010\u0013R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b!\u0010\u0011\u001a\u0004\b\"\u0010\u0013\u00a8\u0006<"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/AttendanceDto;", "", "seen1", "", "totalDays", "salary", "", "salaryEarned", "absentDays", "halfDay", "late", "workingDays", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getAbsentDays$annotations", "()V", "getAbsentDays", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHalfDay$annotations", "getHalfDay", "getLate$annotations", "getLate", "getSalary$annotations", "getSalary", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getSalaryEarned$annotations", "getSalaryEarned", "getTotalDays$annotations", "getTotalDays", "getWorkingDays$annotations", "getWorkingDays", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/crm/v2/data/network/dto/pickup/AttendanceDto;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"})
public final class AttendanceDto {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer totalDays = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double salary = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double salaryEarned = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer absentDays = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer halfDay = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer late = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer workingDays = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.network.dto.pickup.AttendanceDto.Companion Companion = null;
    
    public AttendanceDto(@org.jetbrains.annotations.Nullable()
    java.lang.Integer totalDays, @org.jetbrains.annotations.Nullable()
    java.lang.Double salary, @org.jetbrains.annotations.Nullable()
    java.lang.Double salaryEarned, @org.jetbrains.annotations.Nullable()
    java.lang.Integer absentDays, @org.jetbrains.annotations.Nullable()
    java.lang.Integer halfDay, @org.jetbrains.annotations.Nullable()
    java.lang.Integer late, @org.jetbrains.annotations.Nullable()
    java.lang.Integer workingDays) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getTotalDays() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "TotalDays")
    @java.lang.Deprecated()
    public static void getTotalDays$annotations() {
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
    public final java.lang.Double getSalaryEarned() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "SalaryEarned")
    @java.lang.Deprecated()
    public static void getSalaryEarned$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getAbsentDays() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "AbsentDays")
    @java.lang.Deprecated()
    public static void getAbsentDays$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getHalfDay() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "HalfDay")
    @java.lang.Deprecated()
    public static void getHalfDay$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getLate() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Late")
    @java.lang.Deprecated()
    public static void getLate$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getWorkingDays() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "WorkingDays")
    @java.lang.Deprecated()
    public static void getWorkingDays$annotations() {
    }
    
    public AttendanceDto() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.network.dto.pickup.AttendanceDto copy(@org.jetbrains.annotations.Nullable()
    java.lang.Integer totalDays, @org.jetbrains.annotations.Nullable()
    java.lang.Double salary, @org.jetbrains.annotations.Nullable()
    java.lang.Double salaryEarned, @org.jetbrains.annotations.Nullable()
    java.lang.Integer absentDays, @org.jetbrains.annotations.Nullable()
    java.lang.Integer halfDay, @org.jetbrains.annotations.Nullable()
    java.lang.Integer late, @org.jetbrains.annotations.Nullable()
    java.lang.Integer workingDays) {
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
    com.crm.v2.data.network.dto.pickup.AttendanceDto self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/crm/v2/data/network/dto/pickup/AttendanceDto.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/crm/v2/data/network/dto/pickup/AttendanceDto;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.crm.v2.data.network.dto.pickup.AttendanceDto> {
        @org.jetbrains.annotations.NotNull()
        public static final com.crm.v2.data.network.dto.pickup.AttendanceDto.$serializer INSTANCE = null;
        
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
        public com.crm.v2.data.network.dto.pickup.AttendanceDto deserialize(@org.jetbrains.annotations.NotNull()
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
        com.crm.v2.data.network.dto.pickup.AttendanceDto value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/crm/v2/data/network/dto/pickup/AttendanceDto$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/crm/v2/data/network/dto/pickup/AttendanceDto;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.crm.v2.data.network.dto.pickup.AttendanceDto> serializer() {
            return null;
        }
    }
}