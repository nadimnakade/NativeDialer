package com.crm.v2.presentation.ui.documentupload;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0018\u001a\u00020\u0011R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lcom/crm/v2/presentation/ui/documentupload/DocumentUploadViewModel;", "Landroidx/lifecycle/ViewModel;", "documentRepository", "Lcom/crm/v2/domain/repository/DocumentRepository;", "authDataStore", "Lcom/crm/v2/data/datastore/AuthDataStore;", "app", "Landroid/app/Application;", "(Lcom/crm/v2/domain/repository/DocumentRepository;Lcom/crm/v2/data/datastore/AuthDataStore;Landroid/app/Application;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/presentation/ui/documentupload/DocumentUploadUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onClientNameChange", "", "v", "", "onFileSelected", "path", "onFileTypeChange", "onMobileNoChange", "submit", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DocumentUploadViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.repository.DocumentRepository documentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.datastore.AuthDataStore authDataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final android.app.Application app = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.crm.v2.presentation.ui.documentupload.DocumentUploadUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.documentupload.DocumentUploadUiState> uiState = null;
    
    @javax.inject.Inject()
    public DocumentUploadViewModel(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.repository.DocumentRepository documentRepository, @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.datastore.AuthDataStore authDataStore, @org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.documentupload.DocumentUploadUiState> getUiState() {
        return null;
    }
    
    public final void onClientNameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onMobileNoChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onFileTypeChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onFileSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    public final void submit() {
    }
}