package com.example.aristi_firebase.presentation.signup

import android.util.Log
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aristi_firebase.R
import com.example.aristi_firebase.ui.theme.Black
import com.example.aristi_firebase.ui.theme.SelectedField
import com.example.aristi_firebase.ui.theme.UnSelectedField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignupScreen(auth: FirebaseAuth){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row (){
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
        Text(text = "Email", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
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
            text = "Password",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
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
        Button(onClick = {
            //tambien se puede hace que el usuario con el ROL: Anonimo pueda acceder, se haria de la siguiente manera
            //auth.signInAnonymously()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                task ->
                if(task.isSuccessful){
                    //Registrado
                    Log.i("aris", "Registro OK")
                }else {
                    //Error
                    Log.i("aris", "Registro KO")
                }
            }
        }) {
            Text(text = "Sign Up")
        }

    }
}