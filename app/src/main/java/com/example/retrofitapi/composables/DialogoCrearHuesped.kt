package com.example.retrofitapi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitapi.model.Huesped
import com.example.retrofitapi.viewmodel.ViewModelRetrofit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dialogoCrearHuesped(viewModel: ViewModelRetrofit, onCloseDialog: () -> Unit){
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var fotoPerfil by remember { mutableStateOf("") }
    val a = Huesped(nombre,telefono,fotoPerfil)

    AlertDialog(
        onDismissRequest = {
            onCloseDialog()

        },
        title = {
            Text(text = "CREAR HUESPED")
        },
        text = {
            Column {
                Text("Introduce los datos para el huesped:")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Telefono") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = fotoPerfil,
                    onValueChange = { fotoPerfil = it },
                    label = { Text("Foto de perfil") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    a.nombre = nombre
                    a.telefono = telefono
                    a.fotoPerfil = fotoPerfil
                    viewModel.postHuesped(a)
                    onCloseDialog()

                }) {
                Text("Crear")
            }
        },
        dismissButton = {
            Button(
                onClick = {

                    onCloseDialog()

                }) {
                Text("Salir")
            }
        }
    )
}