package com.xheghun.voyatrip.presentation.ui.custom_view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xheghun.voyatrip.presentation.ui.theme.Satoshi

@Composable
fun VoyaPrimaryButton(
    buttonText: String,
    backgroundColor: Color,
    buttonTextColor: Color,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(vertical = 18.dp),
    onButtonPressed: () -> Unit
) {
    Button(modifier = modifier,
        contentPadding = contentPadding,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        onClick = { onButtonPressed.invoke() }) {
        Text(text = buttonText, fontFamily = Satoshi, color = buttonTextColor)
    }
}