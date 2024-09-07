package com.example.foodmenu.ScreenUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodmenu.Data.MenuItemDataModel
import com.example.foodmenu.ui.theme.LightBlue
import com.example.foodmenu.ui.theme.Purple

@Composable
fun DetailScreen(menuItemDataModel: MenuItemDataModel){
    LazyColumn(modifier = Modifier.padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            HeadingText(menuItemDataModel)
        }
        item {
            ImagesPart(menuItemDataModel)
        }
        item {
            DescriptionText(menuItemDataModel)
        }
        item {
            DetailsPart(menuItemDataModel)
        }
        item {
            ElevatedButton(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Order From Restaurant")
            }
        }

    }
}

@Composable
fun HeadingText(menuItemDataModel: MenuItemDataModel) {
    val gradientColors = listOf(Cyan, LightBlue, Purple)

    Text(
        text = menuItemDataModel.name,
        style = TextStyle(
            brush = Brush.linearGradient(colors = gradientColors),
            fontSize = 20.sp
        ),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun ImagesPart(menuItemDataModel: MenuItemDataModel) {
    AsyncImage(
        model = menuItemDataModel.image,
        contentDescription = "Main Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}
@Composable
fun DescriptionText(menuItemDataModel: MenuItemDataModel){
    var isExpanded by remember { mutableStateOf(false) }

    Text(
        text = "\"${menuItemDataModel.description}\"",
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        maxLines = if (isExpanded) Int.MAX_VALUE else 3,
        overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
        modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }
    )
}

@Composable
fun DetailsPart(menuItemDataModel: MenuItemDataModel) {
    ElevatedCard(modifier = Modifier.padding(4.dp)) {
        // This column is used to give modifier for card
        Column(modifier = Modifier.padding(6.dp)) {
            CardOfDetails(menuItemDataModel)
        }
    }
}

@Composable
fun CardOfDetails(menuItemDataModel: MenuItemDataModel) {
    Column() {
        for(index in 0..3){
            LayoutEachItem(index,menuItemDataModel)
        }
        Text(
            text = "*Note: Details are per 100g",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
    }
}


@Composable
fun LayoutEachItem(index: Int, menuItemDataModel: MenuItemDataModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = showTextKey(index),
            modifier = Modifier.weight(1f),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = ":",
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = showTextValue(index,menuItemDataModel),
            modifier = Modifier.weight(1f),
            color = Color.DarkGray,
            textAlign = TextAlign.End
        )
    }
}

fun showTextKey(i: Int): String {
   return when(i){
       0 -> "Protein"
       1 -> "Calories"
       2 -> "Fats"
       3 -> "Carbs"
       else -> ""
   }
}

fun showTextValue(i: Int, menuItemDataModel: MenuItemDataModel): String {
    return when(i){
        0 -> menuItemDataModel.proteins + "*"
        1 -> menuItemDataModel.calories
        2 -> menuItemDataModel.fats + "*"
        3 -> menuItemDataModel.carbos + "*"
        else -> ""
    }
}


