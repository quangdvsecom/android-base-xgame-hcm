package com.xgame.base.ui.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreenDemo(  modifier: Modifier = Modifier,
                     onNavigateTo: (String) -> Unit,
                     onBackStack: () -> Unit,
                     onExit: () -> Unit,) {
    Box(
        modifier = Modifier
            .fillMaxSize(), // Full screen
        contentAlignment = Alignment.Center // Center content
    ) {
        Text(
            text = "Main screen",
            style = MaterialTheme.typography.headlineMedium // or use fontSize = 24.sp
        )
    }
}
