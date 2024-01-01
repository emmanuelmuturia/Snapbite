package emmanuelmuturia.navgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import emmanuelmuturia.about.AboutScreen
import emmanuelmuturia.day.DayScreen
import emmanuelmuturia.faq.FAQScreen
import emmanuelmuturia.food.EditFoodScreen
import emmanuelmuturia.food.ViewFoodScreen
import emmanuelmuturia.home.HomeScreen
import emmanuelmuturia.home.WelcomeScreen
import emmanuelmuturia.notifications.NotificationsScreen
import emmanuelmuturia.photography.PhotoScreen
import emmanuelmuturia.profile.ProfileScreen
import emmanuelmuturia.routes.Routes
import emmanuelmuturia.search.SearchScreen
import emmanuelmuturia.settings.SettingsScreen
import emmanuelmuturia.sharedpreferences.SnapbiteSharedPreferences

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {

    val isFirstTimeUser =
        SnapbiteSharedPreferences(context = LocalContext.current).isFirstTimeUser

    NavHost(
        navController = navController, startDestination = if (isFirstTimeUser) {
            Routes.WelcomeScreen.route
        } else {
            Routes.HomeScreen.route
        }
    ) {

        composable(route = Routes.HomeScreen.route) {
            HomeScreen(
                navigateToDayScreen = { navController.navigate(route = Routes.DayScreen.route) },
                navigateToProfileScreen = { navController.navigate(route = Routes.ProfileScreen.route) },
                navigateToSettingsScreen = { navController.navigate(route = Routes.SettingsScreen.route) },
                navigateToNotificationsScreen = { navController.navigate(route = Routes.NotificationsScreen.route) },
                navigateToSearchScreen = { navController.navigate(route = Routes.SearchScreen.route) }
            )
        }

        composable(route = Routes.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

        composable(route = Routes.NotificationsScreen.route) {
            NotificationsScreen(navigateBack = { navController.popBackStack() })
        }

        composable(route = Routes.SettingsScreen.route) {
            SettingsScreen(
                navigateToAbout = { navController.navigate(route = Routes.AboutScreen.route) },
                navigateBack = { navController.popBackStack() },
                navigateToFAQ = { navController.navigate(route = Routes.FAQScreen.route) })
        }

        composable(route = Routes.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Routes.DayScreen.route) {
            DayScreen(navController = navController)
        }

        composable(route = Routes.EditFoodScreen.route) {
            EditFoodScreen(navController = navController)
        }

        composable(route = Routes.ViewFoodScreen.route) {
            ViewFoodScreen(navController = navController)
        }

        composable(route = Routes.PhotoScreen.route) {
            PhotoScreen(navController = navController)
        }

        composable(route = Routes.FAQScreen.route) {
            FAQScreen { navController.popBackStack() }
        }

        composable(route = Routes.AboutScreen.route) {
            AboutScreen { navController.popBackStack() }
        }

        composable(route = Routes.WelcomeScreen.route) {

            val context = LocalContext.current

            val sharedPreferences =
                SnapbiteSharedPreferences(
                    context = context
                ).sharedPreferences

            WelcomeScreen {
                sharedPreferences.edit().putBoolean("isFirstTimeUser", false).apply()
                navController.navigate(route = Routes.HomeScreen.route)
            }

        }

    }
}