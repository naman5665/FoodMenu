package com.example.foodmenu.ScreenUI

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodmenu.R

@Composable
fun ShowMeTheMenu(modifier: Modifier = Modifier, onClick: () -> Unit){

    var visibleTruck by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visibleTruck = true
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row {

            AnimatedVisibility(
                visible = visibleTruck,
                enter = slideInHorizontally(animationSpec = tween(durationMillis = 2000)) { fullWidth ->
                    -fullWidth / 3
                }
            ){
                Image(
                    painter = painterResource(id = R.drawable.food_truck),
                    contentDescription = "Main Screen Truck Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)

                )
            }
            Image(
                painter = painterResource(id = R.drawable.no_junk_food),
                contentDescription = "Main Screen Food Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
            )
        }

        ElevatedButton(onClick = {
            onClick()
        }) {
            Text(text = "Show me the Menu")
        }
    }
}