package com.sabbirosa.mapup.navigation

sealed class Screens(var route: String) {
    object MainScreen : Screens("main")
    object PlaceForm : Screens("PlaceForm")
    object Places : Screens("Places")
}