package com.example.aristi_firebase.presentation.home

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@Composable
fun HomeScreen(db:FirebaseFirestore){

    Button(onClick = {
        createArtist(db)
    }) {
        Text("Añadir artista")
    }
}
data class Artist(
    val name:String,
    val numberOfSongs:Int
)

fun createArtist(db:FirebaseFirestore){

    val random = (1..10000).random()
    val artist = Artist(name ="Random $random", numberOfSongs = random)

    //Como en MONGO, Firebase es una bd no relacional entonces accedemos a la colecion que hemops creado
    db.collection("artist")
        .add(artist)
        //Si todo ha salido bien entra en este add
        .addOnSuccessListener{
            Log.i("David", "Success")
        }
        //Si ha habido algun error en el proceso de buscar la coleccion entra en este
        .addOnFailureListener{
            Log.i("David", "Failure")
        }

        //Si se ha terminado, entra en este
        .addOnCompleteListener{
            Log.i("David", "Complete")
        }
}