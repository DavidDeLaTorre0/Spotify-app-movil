package com.example.hooksounds_tfg.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hooksounds_tfg.R
import com.example.hooksounds_tfg.ui.theme.Black
import com.example.hooksounds_tfg.ui.theme.SelectedField
import com.example.hooksounds_tfg.ui.theme.UnSelectedField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SignupScreen(auth: FirebaseAuth, db: FirebaseFirestore, navigateToLogin: () -> Unit) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row() {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Text(text = "Email", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnSelectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))

        Text(
            text = "Nombre de usuario",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnSelectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))

        Text(
            text = "Contraseña",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnSelectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))


        Text(
            text = "Repita contraseña",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        TextField(
            value = password2,
            onValueChange = { password2 = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnSelectedField,
                focusedContainerColor = SelectedField
            )
        )

        // Mostrar mensaje de error si es necesario
        if (errorMessage.isNotEmpty()) {

            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp
            )

        }
        Spacer(Modifier.height(48.dp))
        Button(
            //tambien se puede hace que el usuario con el ROL: Anonimo pueda acceder, se haria de la siguiente manera
            //auth.signInAnonymously()
            onClick = {

                //Validaciones

                if (password != password2) {
                    errorMessage = "Las contraseñas no coinciden."
                } else if (email.isBlank() || !email.matches(emailPattern)) {

                    errorMessage = "Correo invalido, reviselo"
                } else {
                    if (username.isBlank()) {
                        errorMessage = "El nombre está vacio"
                    } else {
                        if (password.isBlank() || password2.isBlank()) {
                            errorMessage = "Las contraseñas no pueden estar vacias"
                        } else {
                            errorMessage = ""
                            // Intentar crear el usuario
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {

                                        val user = FirebaseAuth.getInstance().currentUser;

                                        val userMap = hashMapOf(
                                            "nombre" to username,
                                            "email" to email,
                                            "password" to password
                                        )

                                        if (user != null) {
                                            db.collection("usuarios")
                                                .document(user.uid)
                                                .set(userMap).addOnCompleteListener {
                                                    Log.i("CUsuario", "Success")
                                                }.addOnFailureListener {
                                                    Log.i("CUsuario", "Failure")
                                                }.addOnCompleteListener {
                                                    Log.i("CUsuario", "Complete")
                                                }
                                        }

                                        Log.i("REGIS", "Registro OK")

                                        navigateToLogin()

                                        Toast.makeText(
                                            context,
                                            "Cuenta creada con éxito",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Log.i("REGIS", "Registro KO")
                                        errorMessage =
                                            "El correo ya esta asociado a otra cuenta"
                                    }
                                }
                        }
                    }

                }
            }
        ) {
            Text(text = "Sign Up")
        }

    }
}