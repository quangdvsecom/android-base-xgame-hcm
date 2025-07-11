package com.xgame.base.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Created by ElChuanmen on 6/5/2025.
 * Telegram : elchuanmen
 * Phone :0949514503-0773209008
 * Mail :doanvanquang146@gmail.com
 */
sealed class Destinations(val route: String) {
    data object Splash : Destinations("splash")
    data object Intro : Destinations("Intro")
    data object Main : Destinations("Main")
    data object Setting : Destinations("setting")
    data object HomeDemo : Destinations("HomeDemo")
    data object SettingAreaBlock : Destinations("SettingAreaBlock")
    data object SetPassWord : Destinations("setpassword/{isNewPassword}") {
        fun createRoute(isNewPassword: Boolean) = "setpassword/$isNewPassword"
    }

//    data object ListNews : Destinations("list_news/{eventId}") {
//        fun createRoute(eventId: String) = "list_news/$eventId"
//    }
//
//    data object NewsDetail : Destinations("news_detail/{newsId}?event_id={eventId}") {
//        fun createRoute(newsId: String, eventId: String?) = "news_detail/$newsId?event_id=$eventId"
//    }

}

object NavParams {
    const val IMAGE_ID = "image_id"
}

@Composable
fun rememberEventAppNavController(
    navController: NavHostController = rememberNavController()
): DemoAppNavController = remember(navController) {
    DemoAppNavController(navController)
}

@Stable
class DemoAppNavController(
    val navController: NavHostController
) {
    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {//Viewmodel se bi kill nhung khong biet khi nao
        navController.navigateUp()
    }

    fun navigateTo(targetRoute: String) {
        if (targetRoute != currentRoute) {
            navController.navigate(targetRoute) {
            }
        }
    }

    // pop to and clear previous
    fun popNavigateToAndClearPrevious(targetRoute: String) {
        if (targetRoute != currentRoute) {
            navController.navigate(targetRoute) {
                popUpTo(0)
                launchSingleTop = true
            }
        }
    }


    // pop to targetRoute and clear itself
    fun popNavigateTo(targetRoute: String) {
        if (targetRoute != currentRoute) {
            navController.popBackStack(findStartDestination(navController.graph).id, true)
            navController.navigate(targetRoute) {
                launchSingleTop = true
            }
        }
    }

    //Chuyển sang một màn hình mới và chỉ xóa màn hình hiện tại
    fun navigateAndClearPrevious(route: String) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (currentRoute != null && currentRoute != route) {
            navController.navigate(route) {
                popUpTo(currentRoute) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    //Quay về một route cụ thể và đảm bảo không có bản cũ trùng lặp trong stack
    fun popGatewayNavigateTo(route: String) {
        if (route != currentRoute) {
            navController.popBackStack(route, true)
            navController.navigate(route) {
                launchSingleTop = true
            }
        }
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}