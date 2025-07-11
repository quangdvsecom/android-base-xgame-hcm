package com.xgame.base.ui.nav

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xgame.base.R
import com.xgame.base.base.LocalGlobalViewModel
import com.xgame.base.ui.component.AlertDialogCustom
import com.xgame.base.ui.component.NetworkMonitor
import com.xgame.base.ui.component.NoInternetDialog
import com.xgame.base.ui.demo.DetailScreenDemo
import com.xgame.base.ui.demo.MainScreenDemo
import com.xgame.base.ui.demo.SplashScreen
import com.xgame.base.ui.theme.BaseAndroidXgameTheme

@Composable
fun MainApp() {
    val globalViewModel = LocalGlobalViewModel.current
    val context = LocalContext.current
    val context2 = LocalContext.current.applicationContext
    val lifecycleOwner = LocalLifecycleOwner.current
    val forceBlurRequest by globalViewModel.globalBlur.collectAsStateWithLifecycle()
    val navController = rememberEventAppNavController()
    val networkMonitor = remember { NetworkMonitor(context = context2) }
    val isConnected by networkMonitor.isConnected.collectAsState()
    var dialogInterNetShow = false
    BaseAndroidXgameTheme {

        LaunchedEffect(Unit) {
            networkMonitor.registerCallback()

        }
        DisposableEffect(lifecycleOwner) {

            onDispose {
                networkMonitor.unregisterCallback()
            }
        }

        val startDestination = Destinations.Splash.route
//        val startDestination=if (AppPreferences.isFirstOpenApp)Destinations.Intro.route else
//            if (!AppPreferences.isPassWordSet) Destinations.SetPassWord.createRoute(true) else Destinations.Main.route
        NavHost(
            navController = navController.navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .blur(
//                    if (dialogInterNetShow){
//                        30.dp
//
//                    }else if (forceBlurRequest && !dialogInterNetShow){
//                        30.dp
//                    }else 0.dp
////                    if (forceBlurRequest){
////
////
////                    }
//
                    if (forceBlurRequest) 30.dp else 0.dp


                )
        ) {
            composable(Destinations.Main.route) {
                MainScreenDemo(
                    onNavigateTo = { navController.navigateTo(it) },
                    onBackStack = {
                        navController.upPress()
                    },
                    onExit = { (context as? Activity)?.finish() }
                )
            }
            composable(Destinations.Splash.route) {
                SplashScreen(
                    networkMonitor,
                    onFinish = {
                        navController.popNavigateToAndClearPrevious(it)

                    })
            }
            composable(Destinations.DetailsScreen.route) {
                DetailScreenDemo(
                    onBackStack = {
                        navController.upPress()
                    })
            }


        }
        //poup dialog request internet
        if (!isConnected) {
            dialogInterNetShow = true
            globalViewModel.requestGlobalBlur(true)
            NoInternetDialog(
                showDialog = dialogInterNetShow,
                onRetry = {
                    // TODO: xử lý thử lại (ví dụ: kiểm tra mạng)
                    dialogInterNetShow = false // ẩn popup sau khi thử
                },
                onDismiss = {
                    dialogInterNetShow = false
                }
            )
//            AlertDialogCustom(
//                modifier = Modifier
//                    .padding(horizontal = 20.dp)
//                    .fillMaxWidth(),
//                title = stringResource(R.string.lose_connection),
//                message = stringResource(R.string.no_internet_connection),
//                dismissText = stringResource(R.string.try_again),
//                onDismiss = {},
//                confirmText = stringResource(R.string.setting_network),
//                onConfirm = {
//
//                }
//            )
        } else globalViewModel.requestGlobalBlur(false)
    }


}

