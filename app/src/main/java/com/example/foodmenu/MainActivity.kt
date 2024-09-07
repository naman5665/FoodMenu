package com.example.foodmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodmenu.Data.MenuItemDataModel
import com.example.foodmenu.ScreenUI.DetailScreen
import com.example.foodmenu.ScreenUI.MenuScreen
import com.example.foodmenu.ScreenUI.ShowMeTheMenu
import com.example.foodmenu.ui.theme.FoodMenuTheme

class MainActivity : ComponentActivity() {

//    private lateinit var myViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        myViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        setContent {
            FoodMenuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(modifier = Modifier.padding(bottom = 8.dp)) {
                        MyTopAppBar()
                        SetNavigationComponents()
                    }
                }
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTopAppBar(){
        CenterAlignedTopAppBar(
            title = { Text("Food Menu" , textAlign = TextAlign.Center) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,)
        )
    }

    @Composable
    fun SetNavigationComponents() {
        var selectedItemMenu by remember {
            mutableStateOf<MenuItemDataModel?>(null)
        }
        val navController = rememberNavController()
        NavHost(navController, startDestination = "main") {
            composable("main") {
                ShowMeTheMenu {
                    navController.navigate("menu")
                }
            }
            composable("menu") {
                MenuScreen{ item ->
                    selectedItemMenu = item
                    navController.navigate("detail")
                }
            }
            composable("detail") {
                selectedItemMenu?.let { menuItem ->
                    DetailScreen(menuItemDataModel = menuItem)
                }
            }
        }
    }
}