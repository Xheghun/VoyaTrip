package com.xheghun.voyatrip.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TripsView() {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = "Your Trips", modifier = Modifier.padding(vertical = 12.dp))
        Text(text = "Your trip itineraries and  planned trips are placed here")
    }
}