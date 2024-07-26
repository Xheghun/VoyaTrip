package com.xheghun.voyatrip.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.voyatrip.R
import com.xheghun.voyatrip.presentation.ui.theme.Satoshi
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.presentation.viewmodel.TripViewModel
import com.xheghun.voyatrip.ui.theme.bluePrimary
import com.xheghun.voyatrip.ui.theme.darkGray
import com.xheghun.voyatrip.ui.theme.lightGray
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripBottomSheet(tripViewModel: TripViewModel) {
    val bottomSheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

    if (tripViewModel.isCreateTripExpanded.collectAsState().value) {
        ModalBottomSheet(
            shape = RoundedCornerShape(0.dp),
            sheetState = bottomSheetState,
            containerColor = Color.Transparent,
            content = {
                Column {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                coroutineScope.launch {
                                    bottomSheetState.hide()
                                    tripViewModel.updateIsCreateTripExpanded(false)
                                }
                            }
                            .weight(1f)
                            .background(Color.Transparent)
                    )
                    Column(
                        Modifier
                            .weight(4f)
                            .background(Color.White)
                    ) {
                        Box {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(90.dp),
                                painter = painterResource(id = R.drawable.create_trip_overlay),
                                contentDescription = "header overlay",
                                contentScale = ContentScale.Crop
                            )

                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "close bottomsheet",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable {
                                        coroutineScope.launch {
                                            bottomSheetState.hide()
                                            tripViewModel.updateIsCreateTripExpanded(false)
                                        }
                                    }
                                    .padding(16.dp)
                                    .align(Alignment.TopEnd)
                            )

                            Box(
                                Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(20.dp)
                            ) {
                                Image(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .size(50.dp),
                                    painter = painterResource(id = R.drawable.tree),
                                    contentDescription = "tree palm image",
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Column(
                            Modifier
                                .padding(12.dp)
                        ) {
                            Text("Create a Trip", color = Color.Black, fontWeight = FontWeight.Bold)
                            Spacer(height = 6f)
                            Text("Let's Go! Build Your Next Adventure", fontSize = 12.sp)
                            Spacer(height = 10f)

                            Text(
                                text = "Trip Name",
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = tripViewModel.tripName.collectAsState().value,
                                onValueChange = {
                                    tripViewModel.updateTripName(it)
                                },
                                placeholder = {
                                    Text(
                                        text = "Enter the Trip name",
                                        fontSize = 12.sp,
                                        color = lightGray
                                    )
                                })
                            Spacer(6f)

                            Text(
                                text = "Travel Style",
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(darkGray)
                                    .padding(1.dp)
                                    .fillMaxWidth()
                            ) {

                                ExposedDropdownMenuBox(modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color.White),
                                    expanded = tripViewModel.isTravelStyleOptionsExpanded.collectAsState().value,
                                    onExpandedChange = { tripViewModel.toggleTravelStyleOptions() }) {
                                    OutlinedTextField(
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedContainerColor = Color.White,
                                            unfocusedContainerColor = Color.White,
                                            focusedBorderColor = Color.White,
                                            unfocusedBorderColor = Color.White

                                        ),
                                        textStyle = TextStyle(
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = Satoshi,
                                            color = if (tripViewModel.selectedTravelStyle.collectAsState().value == tripViewModel.defaulTravel
                                            ) lightGray else Color.Black
                                        ),
                                        modifier = Modifier
                                            .menuAnchor()
                                            .fillMaxWidth(),
                                        value = tripViewModel.selectedTravelStyle.collectAsState().value,
                                        onValueChange = { tripViewModel.updateSelectedTravelStyle(it) },
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = tripViewModel.isTravelStyleOptionsExpanded.collectAsState().value
                                            )
                                        })

                                    ExposedDropdownMenu(
                                        modifier = Modifier
                                            .background(Color.White),
                                        expanded = tripViewModel.isTravelStyleOptionsExpanded.collectAsState().value,
                                        onDismissRequest = {
                                            tripViewModel.toggleTravelStyleOptions(
                                                false
                                            )
                                        }) {
                                        tripViewModel.travelStyleOptions.collectAsState().value.forEach { option ->
                                            DropdownMenuItem(
                                                modifier = Modifier.background(if (tripViewModel.selectedTravelStyle.collectAsState().value == option) bluePrimary else Color.White),
                                                text = {
                                                    if (tripViewModel.selectedTravelStyle.collectAsState().value == option)
                                                        Text(
                                                            text = option,
                                                            color = Color.White,
                                                            fontFamily = Satoshi
                                                        )
                                                    else
                                                        Text(option, fontFamily = Satoshi)
                                                },
                                                onClick = {
                                                    tripViewModel.updateSelectedTravelStyle(option)
                                                    tripViewModel.toggleTravelStyleOptions(false)
                                                },
                                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(6f)
                            Text(
                                text = "Trip Description",
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                value = tripViewModel.tripDescription.collectAsState().value,
                                onValueChange = {
                                    tripViewModel.updateTripDescription(it)
                                },
                                maxLines = 10,
                                singleLine = false,
                                placeholder = {
                                    Text(
                                        text = "Tell us more about the trip",
                                        fontSize = 12.sp,
                                        color = lightGray
                                    )
                                }
                            )

                            Box(Modifier.weight(1f))

                            val toast = Toast.makeText(
                                LocalContext.current,
                                "Please input all fields",
                                Toast.LENGTH_SHORT
                            )

                            Button(
                                enabled = tripViewModel.isTripValid(),
                                onClick = {
                                    if (tripViewModel.isTripValid()) {
                                        coroutineScope.launch {
                                            bottomSheetState.hide()
                                            tripViewModel.updateIsCreateTripExpanded(false)
                                            tripViewModel.createTrip(
                                                {
                                                    toast.setText("Trip created successfully")
                                                    toast.show()
                                                },
                                                {
                                                    toast.setText("Trip creation failed: Contact admin")
                                                    toast.show()
                                                })
                                        }
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
                                    text = "Next",
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            },
            onDismissRequest = { tripViewModel.updateIsCreateTripExpanded(false) },
            dragHandle = {}
        )
    }
}