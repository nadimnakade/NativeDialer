package com.crm.v2.presentation.ui.auth;

import com.crm.v2.domain.usecase.auth.LoginUseCase;
import com.crm.v2.domain.usecase.auth.LogoutUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<LoginUseCase> loginUseCaseProvider;

  private final Provider<LogoutUseCase> logoutUseCaseProvider;

  public LoginViewModel_Factory(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<LogoutUseCase> logoutUseCaseProvider) {
    this.loginUseCaseProvider = loginUseCaseProvider;
    this.logoutUseCaseProvider = logoutUseCaseProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(loginUseCaseProvider.get(), logoutUseCaseProvider.get());
  }

  public static LoginViewModel_Factory create(
      javax.inject.Provider<LoginUseCase> loginUseCaseProvider,
      javax.inject.Provider<LogoutUseCase> logoutUseCaseProvider) {
    return new LoginViewModel_Factory(Providers.asDaggerProvider(loginUseCaseProvider), Providers.asDaggerProvider(logoutUseCaseProvider));
  }

  public static LoginViewModel_Factory create(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<LogoutUseCase> logoutUseCaseProvider) {
    return new LoginViewModel_Factory(loginUseCaseProvider, logoutUseCaseProvider);
  }

  public static LoginViewModel newInstance(LoginUseCase loginUseCase, LogoutUseCase logoutUseCase) {
    return new LoginViewModel(loginUseCase, logoutUseCase);
  }
}
