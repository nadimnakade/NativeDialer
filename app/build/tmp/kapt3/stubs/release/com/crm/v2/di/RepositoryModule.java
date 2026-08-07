package com.crm.v2.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0015H\'J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0018H\'J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u001bH\'J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u001eH\'J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0005\u001a\u00020!H\'\u00a8\u0006\""}, d2 = {"Lcom/crm/v2/di/RepositoryModule;", "", "()V", "bindAuthRepository", "Lcom/crm/v2/domain/repository/AuthRepository;", "impl", "Lcom/crm/v2/data/repository/AuthRepositoryImpl;", "bindCallLogRepository", "Lcom/crm/v2/domain/repository/CallLogRepository;", "Lcom/crm/v2/data/repository/CallLogRepositoryImpl;", "bindContactRepository", "Lcom/crm/v2/domain/repository/ContactRepository;", "Lcom/crm/v2/data/repository/ContactRepositoryImpl;", "bindDailyLoginRepository", "Lcom/crm/v2/domain/repository/DailyLoginRepository;", "Lcom/crm/v2/data/repository/DailyLoginRepositoryImpl;", "bindDialerRepository", "Lcom/crm/v2/domain/repository/DialerRepository;", "Lcom/crm/v2/data/repository/DialerRepositoryImpl;", "bindDocumentRepository", "Lcom/crm/v2/domain/repository/DocumentRepository;", "Lcom/crm/v2/data/repository/DocumentRepositoryImpl;", "bindLeadRepository", "Lcom/crm/v2/domain/repository/LeadRepository;", "Lcom/crm/v2/data/repository/LeadRepositoryImpl;", "bindPickupRepository", "Lcom/crm/v2/domain/repository/PickupRepository;", "Lcom/crm/v2/data/repository/PickupRepositoryImpl;", "bindReportingRepository", "Lcom/crm/v2/domain/repository/ReportingRepository;", "Lcom/crm/v2/data/repository/ReportingRepositoryImpl;", "bindSettingsRepository", "Lcom/crm/v2/domain/repository/SettingsRepository;", "Lcom/crm/v2/data/repository/SettingsRepositoryImpl;", "app_release"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.AuthRepository bindAuthRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.AuthRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.LeadRepository bindLeadRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.LeadRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.ReportingRepository bindReportingRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.ReportingRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.DialerRepository bindDialerRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.DialerRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.CallLogRepository bindCallLogRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.CallLogRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.DailyLoginRepository bindDailyLoginRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.DailyLoginRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.DocumentRepository bindDocumentRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.DocumentRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.PickupRepository bindPickupRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.PickupRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.ContactRepository bindContactRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.ContactRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.domain.repository.SettingsRepository bindSettingsRepository(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.repository.SettingsRepositoryImpl impl);
}