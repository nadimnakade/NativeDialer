package com.crm.v2.presentation.ui.dailylogin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/crm/v2/presentation/ui/dailylogin/DailyLoginViewModel;", "Landroidx/lifecycle/ViewModel;", "dailyLoginRepository", "Lcom/crm/v2/domain/repository/DailyLoginRepository;", "authDataStore", "Lcom/crm/v2/data/datastore/AuthDataStore;", "(Lcom/crm/v2/domain/repository/DailyLoginRepository;Lcom/crm/v2/data/datastore/AuthDataStore;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/presentation/ui/dailylogin/DailyLoginUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadList", "", "onFieldChange", "field", "", "value", "setActiveTab", "tab", "", "submit", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DailyLoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.repository.DailyLoginRepository dailyLoginRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.datastore.AuthDataStore authDataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.crm.v2.presentation.ui.dailylogin.DailyLoginUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.dailylogin.DailyLoginUiState> uiState = null;
    
    @javax.inject.Inject()
    public DailyLoginViewModel(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.repository.DailyLoginRepository dailyLoginRepository, @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.datastore.AuthDataStore authDataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.dailylogin.DailyLoginUiState> getUiState() {
        return null;
    }
    
    public final void setActiveTab(int tab) {
    }
    
    public final void onFieldChange(@org.jetbrains.annotations.NotNull()
    java.lang.String field, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void submit() {
    }
    
    public final void loadList() {
    }
}