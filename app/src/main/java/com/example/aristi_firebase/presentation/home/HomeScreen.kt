package com.example.aristi_firebase.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopBar() }, // 🔼 Top Bar
        bottomBar = { BottomBar() }, // 🔽 Bottom Bar
        containerColor = Color(0xFFBDBDBD), // 🔴 Fondo gris
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Contenido de la pantalla
            }
        }
    )
}

// 🔼 **Top Bar**
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(), // 🔴 Usa todo el ancho
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // 🔴 Espacia los elementos
            ) {
                // 🔹 Ícono de Perfil
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "Perfil",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                // 🔹 Botón "Inicio" centrado
                TextButton(
                    onClick = { /* Acción del filtro */ },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Green)
                ) {
                    Text(text = "Inicio", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(48.dp)) // 🔴 Para equilibrar el diseño
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}

// 🔽 **Bottom Navigation Bar**
@Composable
fun BottomBar() {
    NavigationBar(
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio",
                    tint = Color.White
                )
            },
            label = { Text("Inicio", color = Color.White, fontSize = 12.sp) },
            selected = false,
            onClick = { /* Acción al hacer clic */ }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color.White
                )
            },
            label = { Text("Buscar", color = Color.White, fontSize = 12.sp) },
            selected = false,
            onClick = { /* Acción al hacer clic */ }
        )
    }
}

data class Artist(
    val name: String,
    val numberOfSongs: Int
)

fun createArtist(db: FirebaseFirestore) {

    val random = (1..10000).random()
    val artist = Artist(name = "Random $random", numberOfSongs = random)

    //Como en MONGO, Firebase es una bd no relacional entonces accedemos a la colecion que hemops creado
    db.collection("artist")
        .add(artist)
        //Si todo ha salido bien entra en este add
        .addOnSuccessListener {
            Log.i("David", "Success")
        }
        //Si ha habido algun error en el proceso de buscar la coleccion entra en este
        .addOnFailureListener {
            Log.i("David", "Failure")
        }

        //Si se ha terminado, entra en este
        .addOnCompleteListener {
            Log.i("David", "Complete")
        }
}
