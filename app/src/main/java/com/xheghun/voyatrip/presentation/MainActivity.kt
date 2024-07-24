package com.xheghun.voyatrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xheghun.voyatrip.presentation.Route
import com.xheghun.voyatrip.presentation.ui.screens.CreateTrip
import com.xheghun.voyatrip.presentation.ui.screens.TripsView
import com.xheghun.voyatrip.ui.theme.VoyaTripTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            VoyaTripTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.TripsView.name,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable(Route.TripsView.name) {
                            TripsView()
                        }

                        composable(Route.CreateTrip.name) {
                            CreateTrip()
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun VoyaPreview() {
    VoyaTripTheme {
        TripsView()
    }
}