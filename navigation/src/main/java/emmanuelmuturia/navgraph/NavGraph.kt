package emmanuelmuturia.navgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import emmanuelmuturia.day.DayScreen
import emmanuelmuturia.faq.FAQScreen
import emmanuelmuturia.food.EditFoodScreen
import emmanuelmuturia.food.ViewFoodScreen
import emmanuelmuturia.home.HomeScreen
import emmanuelmuturia.notifications.NotificationsScreen
import emmanuelmuturia.photography.PhotoScreen
import emmanuelmuturia.profile.ProfileScreen
import emmanuelmuturia.routes.Routes
import emmanuelmuturia.search.SearchScreen
import emmanuelmuturia.settings.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(route = Routes.HomeScreen.route) {
            HomeScreen(navController = navController)
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

        }

    }
}