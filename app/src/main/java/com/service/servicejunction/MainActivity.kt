package com.service.servicejunction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.service.servicejunction.Routes.Routes
import com.service.servicejunction.auth.ForgotScreen
import com.service.servicejunction.auth.LanguageSelectionScreen
import com.service.servicejunction.auth.OtpScreen
import com.service.servicejunction.auth.Signin
import com.service.servicejunction.map.LocationPickerScreen
import com.service.servicejunction.ui.theme.ServiceJunctionTheme
import com.service.theservicejunction.ui.UI.intro.IntroScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            ServiceJunctionTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Routes.IntroScreen.route) {

                    composable(Routes.IntroScreen.route) {
                        IntroScreen(onLoginClick = { navController.navigate(Routes.Otp.route) },
                            onForgotPasswordClick = { navController.navigate(Routes.ForgotPassword.route) }
                        )
                    }

                    composable(Routes.SignIn.route) {
//                        Signin(
//                            onLoginClick = { navController.navigate(Routes.Otp.route) },
//                            onForgotPasswordClick = { navController.navigate(Routes.ForgotPassword.route) }
//                        )
                    }

                    composable(Routes.Otp.route) {
                        OtpScreen(
                            onSelectLanguageClick = { navController.navigate(Routes.LanguageSelection.route) }
                        )
                    }

                    composable(Routes.ForgotPassword.route) {
                        ForgotScreen(
                            onRememberClick = { navController.navigate(Routes.SignIn.route) },
                            onNextClick = { navController.navigate(Routes.Otp.route) }
                        )
                    }

                    composable(Routes.LanguageSelection.route) {
                        LanguageSelectionScreen(
                            onSelectBottomBarClick = { navController.navigate(Routes.LocationPicker.route) }
                        )
                    }

                    composable(Routes.LocationPicker.route) {
                        LocationPickerScreen(
                            onSelectBottomBarClick = { navController.navigate(Routes.MainScreen.route) }
                        )
                    }

                    composable(Routes.MainScreen.route) {
                        MainScreen()
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServiceJunctionTheme {
        val navController = rememberNavController()
    }
}