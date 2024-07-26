package com.xheghun.voyatrip.presentation.ui.custom_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.ui.theme.bluePrimary

@Composable
fun ItineraryContainer(
    icon: Painter,
    title: String,
    titleColor: Color,
    backgroundColor: Color,
    iconTint: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 18.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(painter = icon, contentDescription = "section icon", tint = iconTint)
                Spacer(width = 8f)
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = titleColor
                )
            }
            Spacer(12f)
            content.invoke()
        }
    }
}

@Composable
fun ItineraryContentEmpty(message: String, buttonText: String, image: Painter) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 25.dp)
    ) {
        Image(
            painter = image,
            contentDescription = "empty image"
        )

        Text(
            text = message,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 18.dp)
        )

        Box(Modifier.width(150.dp)) {
            VoyaPrimaryButton(
                buttonText = buttonText,
                backgroundColor = bluePrimary,
                buttonTextColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}
