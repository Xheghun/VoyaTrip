package com.xheghun.voyatrip.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xheghun.voyatrip.data.models.dummyLocations
import com.xheghun.voyatrip.presentation.ui.custom_view.LocationListItem
import com.xheghun.voyatrip.presentation.ui.custom_view.VoyaAppBar
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.ui.theme.darkGray
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCityBottomSheet(tripViewModel: TripViewModel) {
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    if (tripViewModel.isSelectCityExpanded.collectAsState().value) {
        ModalBottomSheet(
            shape = RoundedCornerShape(0.dp),
            sheetState = bottomSheetState,
            content = {
                Column(
                    Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) {
                    VoyaAppBar(title = "Where", Icons.Default.Close) {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            tripViewModel.updateIsSelectCityExpanded(false)
                        }
                    }
                    Column(Modifier.padding(12.dp)) {
                        Text("Please select a city", color = darkGray)
                        Spacer(height = 10f)

                        OutlinedTextField(
                            "",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(height = 10f)

                        LazyColumn {
                            items(dummyLocations(12)) { location ->
                                LocationListItem(location) {
                                    tripViewModel.updateSelectedCity(location)
                                    coroutineScope.launch {
                                        bottomSheetState.hide()
                                        tripViewModel.updateIsSelectCityExpanded(false)
                                    }
                                }
                            }
                        }
                    }
                }
            },
            onDismissRequest = {
                tripViewModel.updateIsSelectCityExpanded(false)
            },
            dragHandle = {}
        )
    }
}