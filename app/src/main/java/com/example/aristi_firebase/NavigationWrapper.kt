package com.example.aristi_firebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aristi_firebase.presentation.home.HomeScreen
import com.example.aristi_firebase.presentation.home.SearchSongsScreen
import com.example.aristi_firebase.presentation.initial.InitialScreen
import com.example.aristi_firebase.presentation.login.LoginScreen
import com.example.aristi_firebase.presentation.signup.SignupScreen
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
            SignupScreen(auth)
        }
        composable("home") {
            HomeScreen()
        }
        composable("search_songs") {
            SearchSongsScreen()
        }
    }
}