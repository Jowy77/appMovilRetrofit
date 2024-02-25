package com.example.retrofitapi.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import com.example.retrofitapi.model.Huesped
import com.example.retrofitapi.viewmodel.ViewModelRetrofit

@Composable
fun ItemHuesped(a: Huesped, viewModel: ViewModelRetrofit){
    var openDialogModificar by remember { mutableStateOf(false) }

    if(openDialogModificar){
        dialogoModificarHuesped(a = a, viewModel = viewModel) {
            openDialogModificar = false
        }
    }

    Card(modifier = Modifier
        .size(width = 600.dp, height = 120.dp)
        .padding(4.dp)
        .clickable {
            openDialogModificar = true
        }
        .fillMaxWidth(), elevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp
    ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.Cyan)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement =  Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var imageSize by remember { mutableStateOf(Size.Zero) }
            AsyncImage(model = a.fotoPerfil, contentDescription = null, modifier =
            Modifier
                .size(150.dp)
                .wrapContentWidth(
                    if (imageSize.width < 400) {
                        Alignment.CenterHorizontally
                    } else {
                        Alignment.Start
                    }
                )
                .onSizeChanged { imageSize = it.toSize() }, contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
            println("Image size: ${imageSize.width} x ${imageSize.height}")
            //NOMBRE DEL HUESPED

            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                a.nombre?.let {
                    Text(text = "Nombre : " + it, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally, true),
                        textAlign = TextAlign.Center
                    )
                }
                //TELEFONO DEL HUESPED
                a.telefono?.let {
                    Text(text = "Telefono: " + it, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally, true),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}