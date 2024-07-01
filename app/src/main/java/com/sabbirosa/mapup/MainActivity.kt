package com.sabbirosa.mapup

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sabbirosa.mapup.navigation.NavbarLayout
import com.sabbirosa.mapup.navigation.NavigationItem
import com.sabbirosa.mapup.navigation.Screens
import com.sabbirosa.mapup.navigation.setupNavGraph
import com.sabbirosa.mapup.ui.theme.MapUpTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapUpTheme {
                val items = listOf(
                    NavigationItem(title = "Main Screen", route = Screens.MainScreen.route),
                    NavigationItem(title = "Place Form", route = Screens.PlaceForm.route),
                    NavigationItem(title = "Places", route = Screens.Places.route),
                )
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                ModalNavigationDrawer(drawerContent = {

                   ModalDrawerSheet {
                        NavbarLayout(items = items, currentRoute = Screens.MainScreen.route) {currentNavigationItem ->
                            navController.navigate(currentNavigationItem.route)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                   }
                }, drawerState = drawerState) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("MapUp") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(imageVector = Icons.Default.Menu, contentDescription = "MapUp App Menu Icon")
                                    }
                                }
                            )
                        }
                    ) {innerPadding ->
                        setupNavGraph(navController = navController, innerPadding = innerPadding)
                    }
                }
            }
        }

    }
}