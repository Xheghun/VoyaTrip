package com.xheghun.voyatrip.presentation.ui.custom_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.voyatrip.R
import com.xheghun.voyatrip.data.models.Location
import com.xheghun.voyatrip.presentation.ui.utils.Spacer
import com.xheghun.voyatrip.ui.theme.darkGray

@Composable
fun LocationListItem(location: Location, onTap: () -> Unit = {}) {
    Row(
        Modifier
            .clickable { onTap.invoke() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = "item icon",
            tint = darkGray,
            modifier = Modifier.size(22.dp)
        )
        Spacer(width = 15f)
        Column(Modifier.weight(1f)) {
            Text(
                location.countryRegion,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(height = 6f)
            Text(location.city, color = darkGray)
        }
        Spacer(width = 15f)
        Column {
            Image(
                painterResource(R.drawable.flag_ng),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(22.dp)
                    .height(16.dp)
            )
            Spacer(height = 6f)
            Text(location.code.uppercase(), color = darkGray)
        }
    }
}