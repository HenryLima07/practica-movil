package com.example.sistema.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

public fun GetLocation(
    context: Context,
    onLocationResult: (String) -> Unit
) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    if (
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
        PackageManager.PERMISSION_GRANTED
    ) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val coordinates = "Lat: ${location.latitude}, Lon: ${location.longitude}"
                onLocationResult(coordinates)
            } else {
                onLocationResult("No se pudo obtener la ubicación.")
            }
        }
    } else {
        onLocationResult("Permiso no concedido.")
    }
}