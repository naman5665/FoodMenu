package com.example.foodmenu.ScreenUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.foodmenu.Data.DataViewModel
import com.example.foodmenu.Data.MenuItemDataModel
import com.example.foodmenu.ui.theme.LightBlue
import com.example.foodmenu.ui.theme.Purple40

@Composable
fun MenuScreen(onMenuItemClick: (MenuItemDataModel) -> Unit) {

    val viewModel: DataViewModel = viewModel()
    val menuItemsDataList by viewModel.menuItemDataList.observeAsState(listOf())
    val isMenuItemLoading by viewModel.isLoading.observeAsState(false)
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getMenuListItemData(context)
    }

    if(isMenuItemLoading){
        Text(modifier = Modifier.fillMaxWidth(), text = "Loading...")
    }else{
        LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(menuItemsDataList){
                MakeCard(menuItemDataModel = it,onMenuItemClick)
            }
        }
    }

}

@Composable
fun MakeCard(
    menuItemDataModel: MenuItemDataModel,
    onMenuItemClick: (MenuItemDataModel) -> Unit,
){
    val gradientColors = listOf(LightBlue, Purple40, Color.Cyan)
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(2.dp, Brush.linearGradient(colors = gradientColors)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMenuItemClick(menuItemDataModel) }
        ,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Row {
                AsyncImage(model = menuItemDataModel.thumb, contentDescription = menuItemDataModel.id + "thumb")
                Column {
                    Text(
                        text = menuItemDataModel.name,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = menuItemDataModel.headline,
                        modifier = Modifier.padding(4.dp),
                        fontSize = 10.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}