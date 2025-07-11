package com.xgame.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.xgame.base.base.GlobalViewModel
import com.xgame.base.base.LocalGlobalViewModel
import com.xgame.base.ui.nav.MainApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val globalViewModel: GlobalViewModel by viewModels()
    private var isLoading = true
    private var connectivityManager: ConnectivityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            CompositionLocalProvider(LocalGlobalViewModel provides  globalViewModel) {
                MainApp()
            }
//            initNetworkListener()
//            BaseAndroidXamgeTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting2(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }

//    private fun initNetworkListener() {
//        connectivityManager =
//            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
//        if (connectivityManager?.activeNetwork == null) {
//            runOnUiThread {
//                globalViewModel.setNetworkState(false)
//            }
//        }
//        connectivityManager?.registerDefaultNetworkCallback(object :
//            ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                runOnUiThread {
//                    globalViewModel.setNetworkState(true)
//                }
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                runOnUiThread {
//                    globalViewModel.setNetworkState(false)
//                }
//
//            }
//
//            override fun onUnavailable() {
//                super.onUnavailable()
//                runOnUiThread {
//                    globalViewModel.setNetworkState(false)
//                }
//            }
//        })
//        /* Lấy thông tin WiFi lúc khởi tạo
//        getNetworkInfo()
//        */
//    }


}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//    BaseAndroidXamgeTheme {
//        Greeting2("Android")
//    }
//}