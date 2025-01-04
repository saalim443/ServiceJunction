package com.service.servicejunction.Routes

sealed class Routes(val route: String) {
    object IntroScreen : Routes("introscreen")
    object SignIn : Routes("signin")
    object Otp : Routes("otp")
    object ForgotPassword : Routes("forgot")
    object LanguageSelection : Routes("selectlanguage")
    object LocationPicker : Routes("location")
    object MainScreen : Routes("mainscreen")
}
