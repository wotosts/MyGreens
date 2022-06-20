package com.wotosts.mygreens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {
    Text(
        text = "home screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomScreen() {
    HomeScreen()
}