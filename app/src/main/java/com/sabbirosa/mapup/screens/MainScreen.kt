package com.sabbirosa.mapup.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.sabbirosa.mapup.data.model.ApiClient
import com.sabbirosa.mapup.data.model.BASE_URL
import com.sabbirosa.mapup.data.model.Place
import kotlinx.coroutines.launch

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    GoogleMapView()
}

@Composable
fun GoogleMapView() {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(23.6850, 90.3563), 7f)
    }

    var places by remember { mutableStateOf<List<Place>>(emptyList()) }
    var selectedPlace by remember { mutableStateOf<Place?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var showImageDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val response = ApiClient.apiService.getPlaces()
        places = response
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        places.forEach { place ->
            val locationState = rememberMarkerState(
                position = LatLng(place.lat, place.lon),
            )

            Marker(
                state = locationState,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
                title = place.title,
                snippet = "Click for more details",
                onClick = {
                    selectedPlace = place
                    showDialog = true
                    true
                }
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = selectedPlace?.title ?: "", fontWeight = FontWeight.Bold)
            },
            text = {
                Column {
                    AsyncImage(
                        model = BASE_URL + (selectedPlace?.image ?: ""),
                        contentDescription = selectedPlace?.title,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(2.dp, Color.Gray), RoundedCornerShape(8.dp))
                            .clickable { showImageDialog = true }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    if (showImageDialog) {
        AlertDialog(
            onDismissRequest = { showImageDialog = false },
            title = {
                Text(text = selectedPlace?.title ?: "", fontWeight = FontWeight.Bold)
            },
            text = {
                AsyncImage(
                    model = BASE_URL + (selectedPlace?.image ?: ""),
                    contentDescription = selectedPlace?.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(BorderStroke(2.dp, Color.Gray), RoundedCornerShape(8.dp))
                )
            },
            confirmButton = {
                TextButton(onClick = { showImageDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}
