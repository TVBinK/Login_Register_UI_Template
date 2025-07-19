package com.example.learnjetpack.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnjetpack.ui.screens.LoginScreen
import com.example.learnjetpack.ui.screens.HomeScreen
import com.example.learnjetpack.ui.screens.RegisterBirthdayScreen
import com.example.learnjetpack.ui.screens.RegisterConfirmCodeScreen
import com.example.learnjetpack.ui.screens.RegisterGenderScreen
import com.example.learnjetpack.ui.screens.RegisterScreen
import com.example.learnjetpack.ui.screens.RegisterNameScreen
import com.example.learnjetpack.ui.screens.RegisterPasswordScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object RegisterEmail : Screen("register_email")
    object RegisterName : Screen("register_name")
    object RegisterBirthday : Screen("register_birthday")
    object RegisterGender : Screen("register_gender")
    object RegisterPassword : Screen("register_password")
    object RegisterComfirmCode : Screen("register_confirm_code")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = EaseInOut
                )
            ) + fadeIn(
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = EaseInOut
                )
            ) + fadeOut(
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = EaseInOut
                )
            ) + fadeIn(
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = EaseInOut
                )
            ) + fadeOut(
                animationSpec = tween(300)
            )
        }
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onGoogleLoginClick = {
                    // Handle Google login logic
                },
                onFacebookLoginClick = {
                    // Handle Facebook login logic
                },
                onGithubLoginClick = {
                    // Handle GitHub login logic
                },
                onSignUpClick = {
                    navController.navigate(Screen.RegisterEmail.route)
                }
            )

        }
        composable(Screen.RegisterEmail.route) {
            RegisterScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.RegisterName.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.RegisterName.route) {
            RegisterNameScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.RegisterBirthday.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.RegisterBirthday.route) {
            RegisterBirthdayScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.RegisterGender.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.RegisterGender.route) {
            RegisterGenderScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.RegisterPassword.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.RegisterPassword.route) {
            RegisterPasswordScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.RegisterComfirmCode.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.RegisterComfirmCode.route) {
            RegisterConfirmCodeScreen(
                email = "",
                onConfirmSuccess = {
                    navController.navigate(Screen.Home.route)
                },
                onResendCode = {
                    // Handle resend code logic
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onLogoutClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
    }
} 