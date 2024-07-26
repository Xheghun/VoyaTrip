package com.xheghun.voyatrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xheghun.voyatrip.presentation.Route
import com.xheghun.voyatrip.presentation.ui.screens.TripDetailsView
import com.xheghun.voyatrip.presentation.ui.screens.TripsView
import com.xheghun.voyatrip.presentation.viewmodel.TripViewModel
import com.xheghun.voyatrip.ui.theme.VoyaTripTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            KoinAndroidContext {
                val tripViewModel = koinViewModel<TripViewModel>()
                val navController = rememberNavController()
                VoyaTripTheme(darkTheme = false) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Route.TripsListView.name,
                            modifier = Modifier.padding(innerPadding),
                        ) {
                            composable(Route.TripsListView.name) {
                                TripsView(navController, tripViewModel)
                            }

                            composable(Route.TripDetails.name) {
                                TripDetailsView(navController, tripViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}
