package com.sabbirosa.mapup.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sabbirosa.mapup.screens.EntityForm
import com.sabbirosa.mapup.screens.EntityList
import com.sabbirosa.mapup.screens.MainScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun setupNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
        NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
            composable(Screens.MainScreen.route) {
                MainScreen(innerPadding = innerPadding)
            }

            composable(Screens.EntityForm.route) {
                EntityForm(innerPadding = innerPadding)
            }

            composable(Screens.EntityList.route) {
                EntityList(innerPadding = innerPadding)
            }
    }
}