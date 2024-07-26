package com.xheghun.voyatrip.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.xheghun.voyatrip.R
import com.xheghun.voyatrip.presentation.ui.custom_view.ItineraryContainer
import com.xheghun.voyatrip.presentation.ui.custom_view.ItineraryContentEmpty
import com.xheghun.voyatrip.presentation.ui.custom_view.VoyaAppBar
import com.xheghun.voyatrip.presentation.ui.custom_view.VoyaPrimaryButton
import com.xheghun.voyatrip.presentation.ui.theme.Satoshi
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.presentation.ui.utils.parseDate
import com.xheghun.voyatrip.presentation.viewmodel.TripViewModel
import com.xheghun.voyatrip.ui.theme.bluePrimary
import com.xheghun.voyatrip.ui.theme.colorBg
import com.xheghun.voyatrip.ui.theme.colorDarkBg
import com.xheghun.voyatrip.ui.theme.darkGray
import com.xheghun.voyatrip.ui.theme.lightGray
import com.xheghun.voyatrip.ui.theme.navyBlueDark
import com.xheghun.voyatrip.ui.theme.skyBlue
import androidx.compose.foundation.shape.RoundedCornerShape as RoundedCornerShape

@Composable
fun TripDetailsView(navController: NavHostController, tripViewModel: TripViewModel) {
    val selectedTrip = tripViewModel.selectedTrip.collectAsState()

    selectedTrip.value?.let { trip ->
        Column {
            VoyaAppBar(title = "Plan a Trip") {
                navController.popBackStack()
            }

            Column(Modifier.verticalScroll(rememberScrollState())) {
                Image(
                    painter = painterResource(id = R.drawable.body),
                    contentDescription = "Header image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
                Spacer(height = 12f)

                Column(Modifier.padding(12.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(colorBg)
                            .padding(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.DateRange,
                            contentDescription = "date icon",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(width = 4f)
                        Text(
                            text = parseDate(trip.startDate), fontWeight = FontWeight.Medium, fontSize = 12.sp
                        )
                        Spacer(width = 4f)
                        Icon(
                            Icons.Filled.ArrowForward,
                            contentDescription = "arrow icon",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(width = 4f)
                        Text(text = parseDate(trip.endDate), fontWeight = FontWeight.Medium, fontSize = 12.sp)
                    }

                    Text(
                        text = trip.tripName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${trip.location.city}, ",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = darkGray
                        )

                        Text(
                            text = trip.location.countryRegion,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = darkGray
                        )
                        Box(
                            Modifier
                                .padding(horizontal = 5.dp)
                                .width(1.dp)
                                .height(8.dp)
                                .background(darkGray)
                        )
                        Text(
                            text = "${trip.travelStyle} Trip",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = darkGray
                        )
                    }


                    //Button ROW
                    Row(Modifier.padding(vertical = 12.dp)) {
                        VOutlinedButton(
                            "Trip Collaboration",
                            painterResource(R.drawable.handshake),
                            Modifier.weight(1f)
                        ) {

                        }

                        Spacer(width = 6f)

                        VOutlinedButton(
                            "Share Trip", painterResource(R.drawable.share), Modifier.weight(1f)
                        ) {

                        }

                        Icon(painterResource(id = R.drawable.more_vert),
                            "more options icon",
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable { }
                                .padding(12.dp))
                    }

                    Spacer(10f)

                    Section(
                        title = "Activities",
                        subtle = "Build, personalize, and optimize your itineraries with our trip planner.",
                        buttonText = "Add Activities",
                        backgroundColor = navyBlueDark,
                        buttonBackgroundColor = bluePrimary,
                        buttonTextColor = Color.White,
                    ) {

                    }

                    Spacer(8f)

                    Section(
                        title = "Hotels",
                        subtle = "Build, personalize, and optimize your itineraries with our trip planner.",
                        buttonText = "Add Hotels",
                        backgroundColor = skyBlue,
                        buttonBackgroundColor = bluePrimary,
                        buttonTextColor = Color.White,
                        textColor = Color.Black
                    ) {

                    }

                    Spacer(8f)

                    Section(
                        title = "Flights",
                        subtle = "Build, personalize, and optimize your itineraries with our trip planner.",
                        buttonText = "Add Flights",
                        backgroundColor = bluePrimary,
                        buttonBackgroundColor = Color.White,
                        buttonTextColor = bluePrimary,
                    ) {

                    }

                    Spacer(20f)

                    Text(text = "Trip itineraries", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(8f)
                    Text(text = "Your trip itineraries are placed here", fontSize = 12.sp)

                    Spacer(16f)

                    ItineraryContainer(
                        icon = painterResource(id = R.drawable.flights),
                        title = "Flights",
                        titleColor = Color.Black,
                        backgroundColor = lightGray,
                        iconTint = darkGray
                    ) {
                        ItineraryContentEmpty(
                            message = "No Request Yet",
                            buttonText = "Add Flight",
                            image = painterResource(
                                R.drawable.empty_flight
                            )
                        )
                    }

                    Spacer(8f)

                    ItineraryContainer(
                        icon = painterResource(id = R.drawable.hotel_vector),
                        title = "Hotels",
                        titleColor = Color.White,
                        backgroundColor = colorDarkBg,
                        iconTint = Color.White
                    ) {
                        ItineraryContentEmpty(
                            message = "No Request Yet",
                            buttonText = "Add Hotels",
                            image = painterResource(
                                R.drawable.empty_hotel
                            )
                        )
                    }

                    Spacer(8f)

                    ItineraryContainer(
                        icon = painterResource(id = R.drawable.travel),
                        title = "Activities",
                        titleColor = Color.White,
                        backgroundColor = bluePrimary,
                        iconTint = Color.White
                    ) {
                        ItineraryContentEmpty(
                            message = "No Request Yet",
                            buttonText = "Add Activity",
                            image = painterResource(
                                R.drawable.empty_activity
                            )
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun VOutlinedButton(
    text: String, icon: Painter, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    OutlinedButton(modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, bluePrimary),
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick.invoke() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 18.dp, horizontal = 4.dp)
        ) {
            Icon(
                painter = icon, contentDescription = "icon", tint = bluePrimary
            )
            Spacer(width = 6f)
            Text(
                text = text,
                fontSize = 14.sp,
                color = bluePrimary,
            )
        }
    }
}


@Composable
fun Section(
    title: String,
    subtle: String,
    buttonText: String,
    textColor: Color = Color.White,
    backgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    onButtonPressed: () -> Unit
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 18.dp)
    ) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                color = textColor,
                fontSize = 16.sp,
            )
            Spacer(height = 10f)
            Text(
                text = subtle,
                color = textColor,
                fontSize = 14.sp,
            )

            Spacer(height = 35f)

            VoyaPrimaryButton(
                buttonText,
                buttonBackgroundColor,
                buttonTextColor,
                Modifier.fillMaxWidth()
            ) {
                onButtonPressed.invoke()
            }
        }
    }
}