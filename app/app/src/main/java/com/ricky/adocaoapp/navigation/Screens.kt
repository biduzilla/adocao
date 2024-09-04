package com.ricky.adocaoapp.navigation

sealed class Screens(val route:String) {
    data object SplashScreen:Screens(
        route="splash"
    )

    data object LoginScreen:Screens(
        route="login"
    )

    data object RegisterScreen:Screens(
        route="register"
    )

    data object ForgetPasswordScreen:Screens(
        route="forget_password"
    )
}