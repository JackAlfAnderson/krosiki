package com.example.myapplication.presentation.mapScreen

import android.content.Context
import android.service.quicksettings.Tile
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.tileprovider.tilesource.TileSourcePolicy
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("osm", Context.MODE_PRIVATE)

    Configuration.getInstance().load(context, sharedPreferences)

    val mapView = remember{
        MapView(context)
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        CustomMap(mapView)
    }

}

@Composable
fun CustomMap(
    mapView: MapView
) {
    AndroidView(factory = {mapView}){
        it.apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            minZoomLevel = 4.0
            maxZoomLevel = 20.0
            controller.setZoom(10.0)
            val startPoint = GeoPoint(6.4, 3.4)
            it.controller.setCenter(startPoint)
        }
    }
}
