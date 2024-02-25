package com.example.retrofitapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofitapi.composables.ItemHuesped
import com.example.retrofitapi.composables.dialogoCrearHuesped
import com.example.retrofitapi.ui.theme.RetroFitApiTheme
import com.example.retrofitapi.viewmodel.ViewModelRetrofit
import com.example.retrofitapi.viewmodel.estadoApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetroFitApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel : ViewModelRetrofit = viewModel()
                    val estado by viewModel.estadoLlamada.collectAsState()
                    val listaHuesped by viewModel.listaHuesped.collectAsState()
                    
                    if(estado == estadoApi.LOADING){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
                            Text(
                                text = "CARGANDO API HOTEL",
                                fontSize = 20.sp
                            )
                        }
                    }else{
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top) {

                            var openDialogCrearHuesped by remember { mutableStateOf(false) }
                            //TEXTO BIENVENIDA
                            Text(text = "BIENVENIDO A RETROFIT",
                                modifier = Modifier
                                    .padding(10.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                fontStyle = FontStyle.Italic
                            )
                            //---------------------

                            //ELEMENTOS DE LA LISTA
                            LazyColumn(modifier = Modifier
                                .weight(7f)
                                .fillMaxWidth()){
                                items(listaHuesped!!){
                                    ItemHuesped(a = it, viewModel = viewModel)
                                }
                            }
                            //BOTON AÑADIR HUESPED
                            ExtendedFloatingActionButton(
                                onClick = {
                                    openDialogCrearHuesped = true
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f)
                                    .fillMaxWidth(),
                            ) {
                                Text(
                                    text = "ADD HUESPED",
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier.padding(8.dp),
                                )
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = "ADD",
                                    tint = Color.Red,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            //CAMBIO EL ESTADO PARA ABRIR EL DIALOGO DE CREAR
                            if(openDialogCrearHuesped){
                                dialogoCrearHuesped(viewModel = viewModel) {
                                    openDialogCrearHuesped = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetroFitApiTheme {
        Greeting("Android")
    }
}