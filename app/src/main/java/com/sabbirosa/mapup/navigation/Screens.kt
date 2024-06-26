package com.sabbirosa.mapup.navigation

sealed class Screens(var route: String) {
    object MainScreen : Screens("main")
    object EntityForm : Screens("entityform")
    object EntityList : Screens("entitylist")
}