package com.xheghun.voyatrip.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.xheghun.voyatrip.presentation.ui.custom_view.VoyaAppBar
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.presentation.ui.utils.parseDate
import com.xheghun.voyatrip.presentation.viewmodel.DatePickerState
import com.xheghun.voyatrip.presentation.viewmodel.TripViewModel
import com.xheghun.voyatrip.ui.theme.bluePrimary
import com.xheghun.voyatrip.ui.theme.colorCalenderDayItem
import com.xheghun.voyatrip.ui.theme.iconColor
import com.xheghun.voyatrip.ui.theme.lightGray
import kotlinx.coroutines.launch
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseDateBottomSheet(tripViewModel: TripViewModel) {
    val bottomSheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

    if (tripViewModel.isChooseDateExpanded.collectAsState().value) {
        ModalBottomSheet(
            shape = RoundedCornerShape(0.dp),
            sheetState = bottomSheetState,
            content = {
                Column(
                    Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) {
                    VoyaAppBar(title = "Date", Icons.Default.Close) {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            tripViewModel.updateIsChooseDateExpanded(false)
                        }
                    }
                    Spacer(height = 6f)
                    Box(Modifier.weight(1f)) {

                        val currentMonth = remember { YearMonth.now() }
                        val startMonth =
                            remember { currentMonth.minusMonths(100) } // Adjust as needed
                        val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
                        val firstDayOfWeek =
                            remember { firstDayOfWeekFromLocale() } // Available from the library

                        val state = rememberCalendarState(
                            startMonth = startMonth,
                            endMonth = endMonth,
                            firstVisibleMonth = currentMonth,
                            firstDayOfWeek = firstDayOfWeek
                        )

                        VerticalCalendar(
                            state = state,
                            dayContent = {
                                Day(it, tripViewModel) {
                                    coroutineScope.launch {
                                        bottomSheetState.hide()
                                        tripViewModel.updateIsChooseDateExpanded(false)
                                    }
                                }
                            },
                            monthHeader = {
                                MonthHeader(it)
                            }
                        )
                    }
                    Spacer(height = 6f)
                    Column(Modifier.padding(12.dp)) {

                        Row {
                            DateItem(
                                title = "Start Date",
                                value = parseDate(tripViewModel.selectedStartDate.collectAsState().value),
                                modifier = Modifier.weight(1f),
                                borderColor = if (tripViewModel.datePickerState.collectAsState().value == DatePickerState.START_DATE) bluePrimary else lightGray
                            ) {
                                tripViewModel.updateDatePickerState(DatePickerState.START_DATE)
                            }

                            Spacer(width = 6f)

                            DateItem(
                                title = "End Date",
                                value = parseDate(tripViewModel.selectedEndDate.collectAsState().value),
                                modifier = Modifier.weight(1f),
                                borderColor = if (tripViewModel.datePickerState.collectAsState().value == DatePickerState.END_DATE) bluePrimary else lightGray
                            ) {
                                tripViewModel.updateDatePickerState(DatePickerState.END_DATE)
                            }
                        }
                    }
                }
            },
            onDismissRequest = {
                tripViewModel.updateIsChooseDateExpanded(false)
            },
            dragHandle = {}
        )
    }
}


@Composable
fun Day(day: CalendarDay, tripViewModel: TripViewModel, onTap: () -> Unit) {

    Box(Modifier.padding(2.dp)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .clickable(enabled = day.position == DayPosition.MonthDate) {
                    tripViewModel.updateSelectedDate(day.date)
                    onTap.invoke()
                }
                .background(
                    if (tripViewModel
                            .getInferredSelectedDate()
                            .collectAsState().value == day.date.toString()
                    ) bluePrimary
                    else if (day.position == DayPosition.MonthDate) colorCalenderDayItem
                    else Color(0xFFF9FAFB)
                )
                .padding(4.dp)
                .aspectRatio(1f), // This is important for square sizing!
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = if (day.position == DayPosition.MonthDate) Color.Black else Color(
                    0xFFC4C4C4
                )
            )
        }
    }
}

@Composable
fun MonthHeader(calMonth: CalendarMonth) {

    val weekDays = daysOfWeek()

    Column {
        Text(
            text = "${
                calMonth.yearMonth.month.name.lowercase()
                    .capitalize(Locale.current)
            } ${calMonth.yearMonth.year}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Row {
            weekDays.forEach {
                DayOfWeekView(
                    name = it.name.substring(0, 2).lowercase().capitalize(Locale.current),
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun DayOfWeekView(name: String, modifier: Modifier) {
    Text(
        text = name,
        textAlign = TextAlign.Center,
        modifier = modifier,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun DateItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    borderColor: Color = lightGray,
    onTap: () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = title, fontSize = 12.sp)
        Spacer(height = 6f)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
            Modifier
                .clickable { onTap.invoke() }
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .border(0.8.dp, borderColor)
                .padding(horizontal = 12.dp, vertical = 16.dp)

        ) {
            Text(text = value, fontWeight = FontWeight.Medium)
            Icon(
                Icons.Default.DateRange,
                contentDescription = "date value $value",
                tint = iconColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}