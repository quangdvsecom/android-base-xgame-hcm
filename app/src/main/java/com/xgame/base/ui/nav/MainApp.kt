package com.xgame.base.ui.nav

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xgame.base.base.LocalGlobalViewModel
import com.xgame.base.data.local.AppPreferences
import com.xgame.base.ui.demo.MainScreenDemo
import com.xgame.base.ui.demo.SplashScreen
import com.xgame.base.ui.theme.BaseAndroidXgameTheme

@Composable
fun MainApp(){
    val globalViewModel = LocalGlobalViewModel.current
    val context = LocalContext.current

    val blurRequest by globalViewModel.globalBlur.collectAsStateWithLifecycle()
    val navController = rememberEventAppNavController()
    BaseAndroidXgameTheme {
        val startDestination=Destinations.Splash.route
//        val startDestination=if (AppPreferences.isFirstOpenApp)Destinations.Intro.route else
//            if (!AppPreferences.isPassWordSet) Destinations.SetPassWord.createRoute(true) else Destinations.Main.route
        NavHost(
            navController = navController.navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .blur(if (blurRequest) 30.dp else 0.dp)
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
                SplashScreen (
                    onFinish = {
                        navController.popNavigateToAndClearPrevious(it)

                    })
            }


        }
    }
}