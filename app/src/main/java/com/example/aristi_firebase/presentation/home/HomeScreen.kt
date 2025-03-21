package com.example.aristi_firebase.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aristi_firebase.R
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
        containerColor = Color(0xFFBDBDBD),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(id = R.string.profile_home),
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.profile_home),
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                TextButton(
                    onClick = { /* Acción del filtro */ },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Green)
                ) {
                    Text(text = stringResource(id = R.string.home_home_screen), fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}

@Composable
fun BottomBar() {
    NavigationBar(
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(id = R.string.home_home_screen),
                    tint = Color.White
                )
            },
            label = { Text(stringResource(id = R.string.home_home_screen), color = Color.White, fontSize = 12.sp) },
            selected = false,
            onClick = { /* Acción al hacer clic */ }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_home),
                    tint = Color.White
                )
            },
            label = { Text(stringResource(id = R.string.search_home), color = Color.White, fontSize = 12.sp) },
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