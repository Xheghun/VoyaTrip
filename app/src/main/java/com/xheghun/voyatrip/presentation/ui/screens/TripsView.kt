package com.xheghun.voyatrip.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.voyatrip.R
import com.xheghun.voyatrip.data.models.dummyLocations
import com.xheghun.voyatrip.data.models.dummyTrips
import com.xheghun.voyatrip.presentation.ui.custom_view.Item
import com.xheghun.voyatrip.presentation.ui.custom_view.LocationListItem
import com.xheghun.voyatrip.presentation.ui.custom_view.TripListItem
import com.xheghun.voyatrip.presentation.ui.custom_view.VoyaAppBar
import com.xheghun.voyatrip.presentation.ui.theme.Satoshi
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.ui.theme.bluePrimary
import com.xheghun.voyatrip.ui.theme.darkGray
import com.xheghun.voyatrip.ui.theme.lightGray
import com.xheghun.voyatrip.ui.theme.skyBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsView() {
    val options = listOf("Planned Trips", "Completed Trips")

    var isOptionsExpanded by remember { mutableStateOf(false) }
    var selectedOptions by remember { mutableStateOf(options[0]) }

    var isSelectCityExpanded by remember { mutableStateOf(false) }

    Column() {
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
                        subTitle = "Select City",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        isSelectCityExpanded = true
                    }
                    Spacer(height = 6f)

                    Row(Modifier.fillMaxWidth()) {
                        Item(
                            icon = Icons.Outlined.DateRange,
                            title = "Start Date",
                            subTitle = "Enter Date",
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(width = 4f)
                        Item(
                            icon = Icons.Outlined.DateRange,
                            title = "Start Date",
                            subTitle = "Enter Date",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Button(
                        onClick = { },
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

            Box(modifier = Modifier.height(10.dp))

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
                    expanded = isOptionsExpanded,
                    onExpandedChange = { isOptionsExpanded = !isOptionsExpanded }) {
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
                        value = selectedOptions,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isOptionsExpanded) })

                    ExposedDropdownMenu(
                        modifier = Modifier
                            .background(Color.White),
                        expanded = isOptionsExpanded,
                        onDismissRequest = { isOptionsExpanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = { selectedOptions = option; isOptionsExpanded = false },
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


        SelectCityBottomSheet(isSelectCityExpanded) { isSelectCityExpanded = false }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCityBottomSheet(isExpanded: Boolean, onDismissRequest: () -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    if (isExpanded) {
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
                            onDismissRequest.invoke()
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
                                LocationListItem(location)
                            }
                        }
                    }
                }
            },
            onDismissRequest = { onDismissRequest.invoke() },
            dragHandle = {}
        )
    }
}