package com.crm.v2.presentation.ui.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001aJ\u0006\u0010\u001d\u001a\u00020\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001e"}, d2 = {"Lcom/crm/v2/presentation/ui/auth/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "loginUseCase", "Lcom/crm/v2/domain/usecase/auth/LoginUseCase;", "logoutUseCase", "Lcom/crm/v2/domain/usecase/auth/LogoutUseCase;", "(Lcom/crm/v2/domain/usecase/auth/LoginUseCase;Lcom/crm/v2/domain/usecase/auth/LogoutUseCase;)V", "_events", "Lkotlinx/coroutines/channels/Channel;", "Lcom/crm/v2/presentation/common/Event;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/presentation/ui/auth/LoginUiState;", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "login", "onPasswordChanged", "password", "", "onUsernameChanged", "username", "togglePasswordVisibility", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.usecase.auth.LoginUseCase loginUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.usecase.auth.LogoutUseCase logoutUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.crm.v2.presentation.ui.auth.LoginUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.auth.LoginUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.crm.v2.presentation.common.Event> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.crm.v2.presentation.common.Event> events = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.usecase.auth.LoginUseCase loginUseCase, @org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.usecase.auth.LogoutUseCase logoutUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.auth.LoginUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.crm.v2.presentation.common.Event> getEvents() {
        return null;
    }
    
    public final void onUsernameChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String username) {
    }
    
    public final void onPasswordChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void togglePasswordVisibility() {
    }
    
    public final void clearError() {
    }
    
    public final void login() {
    }
}