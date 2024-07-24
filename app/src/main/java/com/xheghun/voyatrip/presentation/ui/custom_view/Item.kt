package com.xheghun.voyatrip.presentation.ui.custom_view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.ui.theme.colorBg
import com.xheghun.voyatrip.ui.theme.colorSub
import com.xheghun.voyatrip.ui.theme.darkGray
import com.xheghun.voyatrip.ui.theme.iconColor
import com.xheghun.voyatrip.ui.theme.lightGray

@Composable
fun Item(
    icon: ImageVector,
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    onTap: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        modifier
            .clip(RoundedCornerShape(4.dp))
            .border(0.8.dp, color = lightGray)
            .clickable { onTap.invoke(); Log.v("", "$title clicked") }
            .background(colorBg)
            .padding(18.dp)
    ) {
        Icon(
            icon,
            contentDescription = "Item Icon",
            tint = iconColor
        )
        Spacer(width = 5f)
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                color = colorSub,
                fontSize = 12.sp
            )
            Spacer(4f)
            Text(
                text = subTitle,
                fontWeight = FontWeight.Bold,
                color = darkGray
            )
        }
    }
}