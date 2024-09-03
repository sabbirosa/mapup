package com.sabbirosa.mapup.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sabbirosa.mapup.backend.Location
import com.sabbirosa.mapup.backend.LocationVM
import com.sabbirosa.mapup.components.BottomNavigation
import com.sabbirosa.mapup.models.BottomNavItem
import com.sabbirosa.mapup.models.BottomNavItem.Companion.bottomNavItemList
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject
import java.io.File


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main() {

    val bottomNav = bottomNavItemList
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    var userLocation by remember{
        mutableStateOf<LatLng?>(LatLng(23.7276, 90.4106))
    }


    Scaffold(
        bottomBar = {
            BottomNavigation(selectedIndex = selectedIndex) {index ->
                selectedIndex = index
                navController.navigate(bottomNav[index].title)
            }
        }
    ) {

        NavHost(navController = navController, startDestination = "Map"){
            composable("Map"){
                Map(setLocation = {coordinates ->
                    userLocation = coordinates
                })
            }
            composable("Add Location"){
                Add(userLocation!!)
            }
            composable("Edit Location"){
                Edit()
            }
            composable("Get Locations"){
                Get()
            }
        }
    }


}