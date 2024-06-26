package com.sabbirosa.mapup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavbarLayout(
    items: List<NavigationItem>,
    currentRoute: String?,
    onClick: (NavigationItem) -> Unit
) {

    items.forEachIndexed { index, navigationItem ->
        NavigationDrawerItem(label = {
            Text(
                text = navigationItem.title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (navigationItem.route == currentRoute) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }, selected = currentRoute == navigationItem.route, onClick = {
            onClick(navigationItem)
        },
            modifier = Modifier.padding(
                PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = if (index == 0) 40.dp else 8.dp,
                    bottom = 8.dp
                )
            ))
    }
}