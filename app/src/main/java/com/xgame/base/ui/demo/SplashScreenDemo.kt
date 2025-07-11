package com.xgame.base.ui.demo

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.animation.content.Content
import com.xgame.base.ui.component.NetworkMonitor
import com.xgame.base.ui.component.isConnectedToInternet
import com.xgame.base.ui.nav.Destinations
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

@Composable
fun SplashScreen(networkMonitor: NetworkMonitor,modifier: Modifier = Modifier,
                 onFinish: (String) -> Unit) {
    val isConnected by networkMonitor.isConnected.collectAsState()
    suspend fun waitForInternet(context: Context) {
        while (!isConnectedToInternet(context)) {
            delay(1000) // chờ 1 giây rồi kiểm tra lại
        }
    }
    val context = LocalContext.current
Timber.d("SplashScreen $isConnected")
    var progress by remember { mutableStateOf(0f) }
    LaunchedEffect(isConnected) {
        if (isConnected) {
            val duration = 3000 // 3 seconds
            val steps = 30
            val delayTime = duration / steps

            repeat(steps) {
                delay(delayTime.toLong())
                progress += 1f / steps
            }

//        onFinish()
            onFinish.invoke(Destinations.Main.route)
        }

    }
    Box(
        modifier = Modifier.padding(bottom = 120.dp)
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Splash Screen",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            LinearProgressIndicator(progress = progress)
        }
    }

}
