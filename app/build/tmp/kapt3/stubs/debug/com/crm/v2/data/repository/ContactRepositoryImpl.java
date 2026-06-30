package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0016J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0016J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0082@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\f\u0010\u0011\u001a\u00020\n*\u00020\u0012H\u0002J\f\u0010\u0013\u001a\u00020\u0012*\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/crm/v2/data/repository/ContactRepositoryImpl;", "Lcom/crm/v2/domain/repository/ContactRepository;", "context", "Landroid/content/Context;", "contactDao", "Lcom/crm/v2/data/database/dao/ContactDao;", "(Landroid/content/Context;Lcom/crm/v2/data/database/dao/ContactDao;)V", "getCachedContacts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/crm/v2/domain/model/Contact;", "getDeviceContacts", "loadContactsFromDevice", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchContacts", "query", "", "toDomain", "Lcom/crm/v2/data/database/entity/ContactEntity;", "toEntity", "app_debug"})
public final class ContactRepositoryImpl implements com.crm.v2.domain.repository.ContactRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.database.dao.ContactDao contactDao = null;
    
    @javax.inject.Inject()
    public ContactRepositoryImpl(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.dao.ContactDao contactDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.crm.v2.domain.model.Contact>> getDeviceContacts() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.crm.v2.domain.model.Contact>> searchContacts(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.crm.v2.domain.model.Contact>> getCachedContacts() {
        return null;
    }
    
    private final java.lang.Object loadContactsFromDevice(kotlin.coroutines.Continuation<? super java.util.List<com.crm.v2.domain.model.Contact>> $completion) {
        return null;
    }
    
    private final com.crm.v2.data.database.entity.ContactEntity toEntity(com.crm.v2.domain.model.Contact $this$toEntity) {
        return null;
    }
    
    private final com.crm.v2.domain.model.Contact toDomain(com.crm.v2.data.database.entity.ContactEntity $this$toDomain) {
        return null;
    }
}