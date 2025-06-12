package com.benzo.benzomobile.domain.use_case

class ValidateCarNumberUseCase {
    companion object {
        private const val CAR_NUMBER_REGEX =
            """^(([АВЕКМНОРСТУХ]\d{3}(?<!000)[АВЕКМНОРСТУХ]{1,2})(\d{2,3})|(\d{4}(?<!0000)[АВЕКМНОРСТУХ]{2})(\d{2})|(\d{3}(?<!000)(C?D|[ТНМВКЕ])\d{3}(?<!000))(\d{2}(?<!00))|([ТСК][АВЕКМНОРСТУХ]{2}\d{3}(?<!000))(\d{2})|([АВЕКМНОРСТУХ]{2}\d{3}(?<!000)[АВЕКМНОРСТУХ])(\d{2})|([АВЕКМНОРСТУХ]\d{4}(?<!0000))(\d{2})|(\d{3}(?<!000)[АВЕКМНОРСТУХ])(\d{2})|(\d{4}(?<!0000)[АВЕКМНОРСТУХ])(\d{2})|([АВЕКМНОРСТУХ]{2}\d{4}(?<!0000))(\d{2})|([АВЕКМНОРСТУХ]{2}\d{3}(?<!000))(\d{2,3})|(^Т[АВЕКМНОРСТУХ]{2}\d{3}(?<!000)\d{2,3}))$"""
    }

    operator fun invoke(carNumber: String): String? {
        val regex = Regex(CAR_NUMBER_REGEX)

        return when {
            carNumber.isBlank() -> "Поле не может быть пустым"
            !regex.matches(carNumber.uppercase()) -> "Неправильный формат"
            else -> null
        }
    }
}