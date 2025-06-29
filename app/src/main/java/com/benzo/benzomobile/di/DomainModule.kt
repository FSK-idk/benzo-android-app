package com.benzo.benzomobile.di

import com.benzo.benzomobile.domain.use_case.GetGasStationStationsUseCase
import com.benzo.benzomobile.domain.use_case.GetGasStationsUseCase
import com.benzo.benzomobile.domain.use_case.GetUserUseCase
import com.benzo.benzomobile.domain.use_case.GetIsAuthenticatedUseCase
import com.benzo.benzomobile.domain.use_case.GetLoyaltyCardUseCase
import com.benzo.benzomobile.domain.use_case.GetPaymentHistoryCardUseCase
import com.benzo.benzomobile.domain.use_case.GetStationFuelsUseCase
import com.benzo.benzomobile.domain.use_case.GetThemeUseCase
import com.benzo.benzomobile.domain.use_case.LoginUseCase
import com.benzo.benzomobile.domain.use_case.LogoutUseCase
import com.benzo.benzomobile.domain.use_case.PayUseCase
import com.benzo.benzomobile.domain.use_case.RegisterUseCase
import com.benzo.benzomobile.domain.use_case.SetThemeUseCase
import com.benzo.benzomobile.domain.use_case.UpdateUserUseCase
import com.benzo.benzomobile.domain.use_case.ValidateBirthDateUseCase
import com.benzo.benzomobile.domain.use_case.ValidateCarNumberUseCase
import com.benzo.benzomobile.domain.use_case.ValidateCardNumberUseCase
import com.benzo.benzomobile.domain.use_case.ValidateRepeatPasswordUseCase
import com.benzo.benzomobile.domain.use_case.ValidateEmailUseCase
import com.benzo.benzomobile.domain.use_case.ValidateExpirationDateUseCase
import com.benzo.benzomobile.domain.use_case.ValidateFuelAmountUseCase
import com.benzo.benzomobile.domain.use_case.ValidateGenderUseCase
import com.benzo.benzomobile.domain.use_case.ValidateHolderNameUseCase
import com.benzo.benzomobile.domain.use_case.ValidateLoginUseCase
import com.benzo.benzomobile.domain.use_case.ValidateNameUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePasswordUseCase
import com.benzo.benzomobile.domain.use_case.ValidatePaymentAmountUseCase
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
    factoryOf(::ValidateRepeatPasswordUseCase) { bind<ValidateRepeatPasswordUseCase>() }

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
    factoryOf(::UpdateUserUseCase) { bind<UpdateUserUseCase>() }

    factoryOf(::GetLoyaltyCardUseCase) { bind<GetLoyaltyCardUseCase>() }

    factoryOf(::GetPaymentHistoryCardUseCase) { bind<GetPaymentHistoryCardUseCase>() }

    factoryOf(::GetGasStationsUseCase) { bind<GetGasStationsUseCase>() }
    factoryOf(::GetGasStationStationsUseCase) { bind<GetGasStationStationsUseCase>() }
    factoryOf(::GetStationFuelsUseCase) { bind<GetStationFuelsUseCase>() }

    factoryOf(::ValidateFuelAmountUseCase) { bind<ValidateFuelAmountUseCase>() }
    factoryOf(::ValidatePaymentAmountUseCase) { bind<ValidatePaymentAmountUseCase>() }

    factoryOf(::PayUseCase) { bind<PayUseCase>() }

    factoryOf(::ValidateCardNumberUseCase) { bind<ValidateCardNumberUseCase>() }
    factoryOf(::ValidateExpirationDateUseCase) { bind<ValidateExpirationDateUseCase>() }
    factoryOf(::ValidateHolderNameUseCase) { bind<ValidateHolderNameUseCase>() }
}