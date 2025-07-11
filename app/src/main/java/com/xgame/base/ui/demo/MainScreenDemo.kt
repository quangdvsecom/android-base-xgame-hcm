package com.xgame.base.ui.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.xgame.base.ui.nav.Destinations
import timber.log.Timber

@Composable
fun MainScreenDemo(  modifier: Modifier = Modifier,
                     onNavigateTo: (String) -> Unit,
                     onBackStack: () -> Unit,
                     onExit: () -> Unit,) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Đây tương đương onResume() có thể gắn ads ở đây
                Timber.d("ON_RESUME MainScreenDemo")
            }

        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(), // Full screen
        contentAlignment = Alignment.Center // Center content
    ) {
        Text(
            text = "Main screen",
            style = MaterialTheme.typography.headlineMedium // or use fontSize = 24.sp
        )
        Button(onClick = {
          onNavigateTo.invoke(Destinations.DetailsScreen.route)
        }) {
            Text(text = "Click Me")
        }
    }
}
