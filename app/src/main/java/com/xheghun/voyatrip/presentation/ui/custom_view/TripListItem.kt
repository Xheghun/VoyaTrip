package com.xheghun.voyatrip.presentation.ui.custom_view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.xheghun.voyatrip.R
import com.xheghun.voyatrip.data.models.Trip
import com.xheghun.voyatrip.data.models.dummyLocations
import com.xheghun.voyatrip.presentation.Route
import com.xheghun.voyatrip.presentation.ui.utils.parseDate
import com.xheghun.voyatrip.ui.theme.VoyaTripTheme
import com.xheghun.voyatrip.ui.theme.bluePrimary
import com.xheghun.voyatrip.ui.theme.colorBg
import com.xheghun.voyatrip.ui.theme.darkGray
import com.xheghun.voyatrip.ui.theme.lightGray

@Composable
fun TripListItem(trip: Trip, onClick: () -> Unit) {
    Box(Modifier.padding(bottom = 10.dp)) {
        Card(
            border = BorderStroke(0.8.dp, lightGray),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Box {
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .height(230.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.trip_img),
                        contentDescription = "Trip Image",
                        contentScale = ContentScale.Crop

                    )

                    Text(
                        text = trip.location.city,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .padding(20.dp)
                            .width(100.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.TopEnd)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(228, 231, 236),
                                        darkGray,
                                    )
                                )
                            )
                            .padding(vertical = 12.dp)
                    )
                }
                Text(
                    text = trip.tripName,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = parseDate(trip.startDate), fontWeight = FontWeight.Medium)
                    Text(text = trip.duration)
                }

                Button(
                    onClick = { onClick.invoke() },
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
                        text = "View",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}