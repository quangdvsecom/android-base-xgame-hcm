package com.xgame.base.ui.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.xgame.base.helpers.volume.VolumeController
import timber.log.Timber

@Composable
fun DetailScreenDemo(
                     onBackStack: () -> Unit ) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val controller = remember { VolumeController(context) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Đây tương đương onResume() có thể gắn ads ở đây
                Timber.d("ON_RESUME DetailScreenDemo ")
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
            text = "Details screen",
            style = MaterialTheme.typography.headlineMedium // or use fontSize = 24.sp
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Current Volume: ${controller.currentVolume}/${controller.maxVolume}")

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Button(onClick = { controller.decreaseVolume() }) {
                    Text("-")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = { controller.increaseVolume() }) {
                    Text("+")
                }
            }
        }


    }
}
