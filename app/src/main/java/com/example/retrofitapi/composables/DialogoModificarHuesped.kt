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
fun dialogoModificarHuesped(a: Huesped, viewModel: ViewModelRetrofit, onCloseDialog: () -> Unit){
    var nuevoNombre by remember { mutableStateOf(a.nombre) }
    var nuevoTelefono by remember { mutableStateOf(a.telefono) }
    var nuevaFotoPerfil by remember { mutableStateOf(a.fotoPerfil) }

    AlertDialog(
        onDismissRequest = {
            onCloseDialog()

        },
        title = {
            Text(text = "MODIFICAR/ELIMINAR HUESPED")
        },
        text = {
            Column {
                Text("DATOS ACTUALES DEL HUESPED:")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevoNombre,
                    onValueChange = { nuevoNombre = it },
                    label = { Text("Actualizar nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevoTelefono,
                    onValueChange = { nuevoTelefono = it },
                    label = { Text("Actualizar telefono") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevaFotoPerfil,
                    onValueChange = { nuevaFotoPerfil = it },
                    label = { Text("Actualizar foto de perfil") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    a.nombre = nuevoNombre
                    a.telefono = nuevoTelefono
                    a.fotoPerfil = nuevaFotoPerfil
                    viewModel.actulizarHuesped(a.idHuesped, a)
                    onCloseDialog()
                }) {
                Text("Actualizar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.eliminarHuesped(a.idHuesped)
                    onCloseDialog()

                }) {
                Text("Borrar")
            }
        }
    )
}