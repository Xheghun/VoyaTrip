package com.xheghun.voyatrip.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xheghun.voyatrip.R
import com.xheghun.voyatrip.data.models.dummyTrips
import com.xheghun.voyatrip.presentation.ui.custom_view.Item
import com.xheghun.voyatrip.presentation.ui.custom_view.TripListItem
import com.xheghun.voyatrip.presentation.ui.custom_view.VoyaAppBar
import com.xheghun.voyatrip.presentation.ui.theme.Satoshi
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.ui.theme.bluePrimary
import com.xheghun.voyatrip.ui.theme.darkGray
import com.xheghun.voyatrip.ui.theme.lightGray
import com.xheghun.voyatrip.ui.theme.skyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsView() {
    val tripViewModel = viewModel<TripViewModel>()

    Column {
        VoyaAppBar(title = "Plan a Trip")

        //SECTION 1
        Box(
            modifier = Modifier
                .height(550.dp)
                .fillMaxWidth()
                .background(skyBlue)
        ) {

            //IMAGE BG
            Column(Modifier.align(Alignment.BottomCenter)) {
                Row {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f),
                        painter = painterResource(id = R.drawable.hotel),
                        contentDescription = "Hotel Image",
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        modifier = Modifier
                            .weight(1f),
                        painter = painterResource(id = R.drawable.cloud),
                        contentDescription = "Cloud Image",
                    )
                }

                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.landscape),
                    contentDescription = "Lanscape Image",
                    contentScale = ContentScale.FillWidth

                )
            }

            //TOP Section
            Column(Modifier.padding(15.dp)) {
                Text(
                    text = "Plan Your Dream Trip in Minutes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Box(modifier = Modifier.height(6.dp))
                Text(
                    color = darkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    text = "Build, personalize, and optimize your itineraries with our trip planner." +
                            " Perfect for getaways, remote workcations, and any spontaneous escapade."
                )
            }

            //QUICK FORM
            Box(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .padding(horizontal = 20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Column(
                    Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                        .padding(12.dp)
                ) {
                    Item(
                        icon = Icons.Outlined.LocationOn,
                        title = "Where to? ",
                        subTitle = tripViewModel.selectedCity.collectAsState().value?.city
                            ?: "Select City",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        tripViewModel.updateIsSelectCityExpanded(true)
                    }
                    Spacer(height = 6f)

                    Row(Modifier.fillMaxWidth()) {
                        Item(
                            icon = Icons.Outlined.DateRange,
                            title = "Start Date",
                            subTitle = tripViewModel.selectedStartDate.collectAsState().value,
                            modifier = Modifier.weight(1f)
                        ) {

                            tripViewModel.updateDatePickerState(DatePickerState.START_DATE)
                            tripViewModel.updateIsChooseDateExpanded(true)
                        }
                        Spacer(width = 4f)
                        Item(
                            icon = Icons.Outlined.DateRange,
                            title = "Start Date",
                            subTitle = tripViewModel.selectedEndDate.collectAsState().value,
                            modifier = Modifier.weight(1f)
                        ) {
                            tripViewModel.updateDatePickerState(DatePickerState.END_DATE)
                            tripViewModel.updateIsChooseDateExpanded(true)
                        }
                    }

                    Button(
                        onClick = { tripViewModel.updateIsCreateTripExpanded(true) },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = bluePrimary,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            text = "Create a Trip",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        //SECTION 2
        Column(Modifier.padding(horizontal = 12.dp)) {
            Text(
                text = "Your Trips",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 18.dp, bottom = 4.dp)
            )
            Text(
                text = "Your trip itineraries and  planned trips are placed here",
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = darkGray
            )

            Spacer(10f)

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(lightGray)
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {

                ExposedDropdownMenuBox(modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White),
                    expanded = tripViewModel.isOptionsExpanded.collectAsState().value,
                    onExpandedChange = { tripViewModel.toggleOptions() }) {
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White

                        ),
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, fontFamily = Satoshi),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        value = tripViewModel.selectedOption.collectAsState().value,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = tripViewModel.isOptionsExpanded.collectAsState().value) })

                    ExposedDropdownMenu(
                        modifier = Modifier
                            .background(Color.White),
                        expanded = tripViewModel.isOptionsExpanded.collectAsState().value,
                        onDismissRequest = { tripViewModel.toggleOptions(false) }) {
                        tripViewModel.options.collectAsState().value.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    tripViewModel.updateSelectedOption(option); tripViewModel.toggleOptions(
                                    false
                                )
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier.height(10.dp))

            LazyColumn(userScrollEnabled = true) {
                items(dummyTrips()) { trip ->
                    TripListItem(trip)
                }
            }
        }

        SelectCityBottomSheet(tripViewModel)
        ChooseDateBottomSheet(tripViewModel)
        CreateTripBottomSheet(tripViewModel)
    }
}