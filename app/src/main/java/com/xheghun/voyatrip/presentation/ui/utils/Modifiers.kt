package com.xheghun.voyatrip.presentation.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Deprecated(
    "This Could potentially throw an error", ReplaceWith(
        "Use userScrollEnabled = false on a LazyColumn instead", ""
    )
)
@Composable
fun Modifier.Companion.disableScroll() = this.then(
    Modifier.verticalScroll(rememberScrollState(), false)
)


@Composable
fun Spacer(height: Float = 0f, width: Float = 0f) {
    Box(modifier = Modifier
        .height(height.dp)
        .width(width.dp))
}
