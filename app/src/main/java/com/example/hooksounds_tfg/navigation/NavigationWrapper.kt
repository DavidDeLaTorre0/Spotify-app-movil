package com.example.hooksounds_tfg.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hooksounds_tfg.views.HomeScreen
import com.example.hooksounds_tfg.views.InitialScreen
import com.example.hooksounds_tfg.views.LoginScreen
import com.example.hooksounds_tfg.views.SignupScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore
) {

    NavHost(navController = navHostController, startDestination ="initial"){
        composable ("initial"){
            InitialScreen(
                navigateToLogin = {navHostController.navigate("logIn")},
                navigateToSignUp = {navHostController.navigate("signUp")}
            )
        }
        composable ("logIn"){
            LoginScreen(
                auth,
                navigateToHome = {navHostController.navigate("home")}
            )
        }
        composable ("signUp"){
            SignupScreen(
                auth,
                db,
                navigateToLogin = {navHostController.navigate("initial")}
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}