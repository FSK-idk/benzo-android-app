package com.benzo.benzomobile.di

import com.benzo.benzomobile.domain.use_case.FetchGasStationsUseCase
import com.benzo.benzomobile.domain.use_case.FetchLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.FetchPaymentHistoryUseCase
import com.benzo.benzomobile.domain.use_case.FetchUserUseCase
import com.benzo.benzomobile.domain.use_case.GetGasStationsUseCase
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import com.benzo.benzomobile.domain.use_case.GetIsAuthenticatedUseCase
import com.benzo.benzomobile.domain.use_case.GetLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.GetPaymentHistoryCardUseCase
import com.benzo.benzomobile.domain.use_case.GetThemeUseCase
import com.benzo.benzomobile.domain.use_case.LoginUseCase
import com.benzo.benzomobile.domain.use_case.LogoutUseCase
import com.benzo.benzomobile.domain.use_case.RegisterUseCase
import com.benzo.benzomobile.domain.use_case.SetThemeUseCase
import com.benzo.benzomobile.domain.use_case.UpdateUserUseCase
import com.benzo.benzomobile.domain.use_case.ValidateBirthDateUseCase
import com.benzo.benzomobile.domain.use_case.ValidateCarNumberUseCase
import com.benzo.benzomobile.domain.use_case.ValidateConfirmPasswordUseCase
import com.benzo.benzomobile.domain.use_case.ValidateEmailUseCase
import com.benzo.benzomobile.domain.use_case.ValidateGenderUseCase
import com.benzo.benzomobile.domain.use_case.ValidateLoginUseCase
import com.benzo.benzomobile.domain.use_case.ValidateNameUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePasswordUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePhoneNumberUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetThemeUseCase) { bind<GetThemeUseCase>() }
    factoryOf(::SetThemeUseCase) { bind<SetThemeUseCase>() }

    factoryOf(::GetIsAuthenticatedUseCase) { bind<GetIsAuthenticatedUseCase>() }

    factoryOf(::ValidateLoginUseCase) { bind<ValidateLoginUseCase>() }
    factoryOf(::ValidatePasswordUseCase) { bind<ValidatePasswordUseCase>() }
    factoryOf(::ValidateConfirmPasswordUseCase) { bind<ValidateConfirmPasswordUseCase>() }

    factoryOf(::ValidateNameUseCase) { bind<ValidateNameUseCase>() }
    factoryOf(::ValidateCarNumberUseCase) { bind<ValidateCarNumberUseCase>() }
    factoryOf(::ValidatePhoneNumberUseCase) { bind<ValidatePhoneNumberUseCase>() }
    factoryOf(::ValidateEmailUseCase) { bind<ValidateEmailUseCase>() }
    factoryOf(::ValidateBirthDateUseCase) { bind<ValidateBirthDateUseCase>() }
    factoryOf(::ValidateGenderUseCase) { bind<ValidateGenderUseCase>() }

    factoryOf(::RegisterUseCase) { bind<RegisterUseCase>() }
    factoryOf(::LoginUseCase) { bind<LoginUseCase>() }
    factoryOf(::LogoutUseCase) { bind<LogoutUseCase>() }

    factoryOf(::GetUserUseCase) { bind<GetUserUseCase>() }
    factoryOf(::FetchUserUseCase) { bind<FetchUserUseCase>() }
    factoryOf(::UpdateUserUseCase) { bind<UpdateUserUseCase>() }

    factoryOf(::GetLoyaltyCardUseCase) { bind<GetLoyaltyCardUseCase>() }
    factoryOf(::FetchLoyaltyCardUseCase) { bind<FetchLoyaltyCardUseCase>() }

    factoryOf(::GetPaymentHistoryCardUseCase) { bind<GetPaymentHistoryCardUseCase>() }
    factoryOf(::FetchPaymentHistoryUseCase) { bind<FetchPaymentHistoryUseCase>() }

    factoryOf(::GetGasStationsUseCase) { bind<GetGasStationsUseCase>() }
    factoryOf(::FetchGasStationsUseCase) { bind<FetchGasStationsUseCase>() }
}