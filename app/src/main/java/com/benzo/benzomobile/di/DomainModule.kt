package com.benzo.benzomobile.di

import com.benzo.benzomobile.domain.use_case.FetchUserUseCase
import com.benzo.benzomobile.domain.use_case.GetIsAuthenticatedUseCase
import com.benzo.benzomobile.domain.use_case.GetThemeOptionUseCase
import com.benzo.benzomobile.domain.use_case.LoginUserUseCase
import com.benzo.benzomobile.domain.use_case.LogoutUserUseCase
import com.benzo.benzomobile.domain.use_case.RegisterUserUseCase
import com.benzo.benzomobile.domain.use_case.SetThemeConfigUseCase
import com.benzo.benzomobile.domain.use_case.ValidateConfirmPasswordUseCase
import com.benzo.benzomobile.domain.use_case.ValidateLoginUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePasswordUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetThemeOptionUseCase) { bind<GetThemeOptionUseCase>() }
    factoryOf(::SetThemeConfigUseCase) { bind<SetThemeConfigUseCase>() }
    factoryOf(::GetIsAuthenticatedUseCase) { bind<GetIsAuthenticatedUseCase>() }
    factoryOf(::ValidateLoginUseCase) { bind<ValidateLoginUseCase>() }
    factoryOf(::ValidatePasswordUseCase) { bind<ValidatePasswordUseCase>() }
    factoryOf(::ValidateConfirmPasswordUseCase) { bind<ValidateConfirmPasswordUseCase>() }
    factoryOf(::RegisterUserUseCase) { bind<RegisterUserUseCase>() }
    factoryOf(::LoginUserUseCase) { bind<LoginUserUseCase>() }
    factoryOf(::LogoutUserUseCase) { bind<LogoutUserUseCase>() }
    factoryOf(::FetchUserUseCase) { bind<FetchUserUseCase>() }
}