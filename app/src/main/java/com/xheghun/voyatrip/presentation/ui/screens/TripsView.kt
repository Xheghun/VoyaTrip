package com.xheghun.voyatrip.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.xheghun.voyatrip.R
import com.xheghun.voyatrip.presentation.Route
import com.xheghun.voyatrip.presentation.ui.custom_view.Item
import com.xheghun.voyatrip.presentation.ui.custom_view.TripListItem
import com.xheghun.voyatrip.presentation.ui.custom_view.VoyaAppBar
import com.xheghun.voyatrip.presentation.ui.theme.Satoshi
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.presentation.ui.utils.parseDate
import com.xheghun.voyatrip.presentation.viewmodel.DatePickerState
import com.xheghun.voyatrip.presentation.viewmodel.TripViewModel
import com.xheghun.voyatrip.ui.theme.bluePrimary
import com.xheghun.voyatrip.ui.theme.darkGray
import com.xheghun.voyatrip.ui.theme.lightGray
import com.xheghun.voyatrip.ui.theme.skyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsView(navController: NavHostController, tripViewModel: TripViewModel) {
    val trips = tripViewModel.trips.collectAsState().value

    if (!tripViewModel.isBusy.collectAsState().value) {
        Column {
            VoyaAppBar(title = "Plan a Trip") {}
            LazyColumn() {
                item {
                    Column {
                        //SECTION 1
                        if (true) {
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
                                                subTitle = parseDate(tripViewModel.selectedStartDate.collectAsState().value),
                                                modifier = Modifier.weight(1f)
                                            ) {

                                                tripViewModel.updateDatePickerState(
                                                    DatePickerState.START_DATE
                                                )
                                                tripViewModel.updateIsChooseDateExpanded(
                                                    true
                                                )
                                            }
                                            Spacer(width = 4f)
                                            Item(
                                                icon = Icons.Outlined.DateRange,
                                                title = "End Date",
                                                subTitle = parseDate(tripViewModel.selectedEndDate.collectAsState().value),
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                tripViewModel.updateDatePickerState(
                                                    DatePickerState.END_DATE
                                                )
                                                tripViewModel.updateIsChooseDateExpanded(
                                                    true
                                                )
                                            }
                                        }

                                        val toast = Toast.makeText(
                                            LocalContext.current,
                                            "Please input all fields",
                                            Toast.LENGTH_SHORT
                                        )

                                        Button(
                                            onClick = {
                                                if (tripViewModel.canDisplayCreateTripForm()) {
                                                    tripViewModel.updateIsCreateTripExpanded(
                                                        true
                                                    )
                                                } else {
                                                    toast.show()
                                                }
                                            },
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
                        }

                        //SECTION 2
                        Column(
                            Modifier
                                .padding(horizontal = 12.dp)
                        ) {
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
                                        textStyle = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = Satoshi
                                        ),
                                        modifier = Modifier
                                            .menuAnchor()
                                            .fillMaxWidth(),
                                        value = tripViewModel.selectedOption.collectAsState().value,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = tripViewModel.isOptionsExpanded.collectAsState().value
                                            )
                                        })

                                    ExposedDropdownMenu(
                                        modifier = Modifier
                                            .background(Color.White),
                                        expanded = tripViewModel.isOptionsExpanded.collectAsState().value,
                                        onDismissRequest = {
                                            tripViewModel.toggleOptions(
                                                false
                                            )
                                        }) {
                                        tripViewModel.options.collectAsState().value.forEach { option ->
                                            DropdownMenuItem(
                                                text = { Text(text = option) },
                                                onClick = {
                                                    tripViewModel.updateSelectedOption(
                                                        option
                                                    ); tripViewModel.toggleOptions(
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

                        }
                    }
                }
                items(trips) { trip ->
                    TripListItem(trip) {
                        tripViewModel.updateSelectedTrip(trip)
                        navController.navigate(Route.TripDetails.name)
                    }
                }
            }
        }
    }

    SelectCityBottomSheet(tripViewModel)
    ChooseDateBottomSheet(tripViewModel)
    CreateTripBottomSheet(tripViewModel)


    if (tripViewModel.isBusy.collectAsState().value) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            DotLottieAnimation(
                source = DotLottieSource.Url("https://lottie.host/47f45989-137c-4d3b-b715-464725552a94/UuRGJhjQ9g.json"),
                autoplay = true,
                loop = true,
                speed = 1f,
                useFrameInterpolation = false,
                playMode = Mode.FORWARD,
                modifier = Modifier
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(6f)
            Text(
                text = "Please wait...",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Medium
            )
        }
    }
}