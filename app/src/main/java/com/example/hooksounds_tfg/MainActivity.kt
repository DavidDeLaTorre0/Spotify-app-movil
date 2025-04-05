package com.example.hooksounds_tfg

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.hooksounds_tfg.ui.theme.Hooksounds_tfgTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import androidx.navigation.compose.rememberNavController
import com.example.hooksounds_tfg.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    //Creamos aqui las variables de auth, por que noestamos usando MVVM, si no seria en la capa de data, en el repositorio
    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            Hooksounds_tfgTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationWrapper(navHostController,auth,db)
                }
            }
        }
    }

    //Cuando ya estas loggado no deberia entrar en la Initial screen, est emetodo se va a llamar despues del onCreate
    override fun onStart() {
        super.onStart()
        //currentuser es para saber si mi usuario esta loggado
        val currentUser = auth.currentUser
        if(currentUser!= null){
            //Si tenemos usuario reistrado, inicializamos home screen
            //Esto tambien te guarda la sesion, est lo gestiona todo firebase
            Log.i("aris", "Estoy logado")
            //deslogeamos
            auth.signOut()

            Log.i("aris", "Estoy deslogado")
        }
    }
}
