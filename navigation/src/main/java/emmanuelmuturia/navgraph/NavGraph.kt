package emmanuelmuturia.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import emmanuelmuturia.home.EmptyHomeScreen
import emmanuelmuturia.home.HomeScreen
import emmanuelmuturia.notifications.NotificationsScreen
import emmanuelmuturia.profile.ProfileScreen
import emmanuelmuturia.routes.Routes
import emmanuelmuturia.search.SearchScreen
import emmanuelmuturia.settings.SettingsScreen
import emmanuelmuturia.welcome.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.routes) {

        composable(route = Routes.WelcomeScreen.routes) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Routes.HomeScreen.routes) {
            EmptyHomeScreen(navController = navController)
        }

        composable(route = Routes.SearchScreen.routes) {
            SearchScreen(navController = navController)
        }

        composable(route = Routes.NotificationsScreen.routes) {
            NotificationsScreen(navController = navController)
        }

        composable(route = Routes.SettingsScreen.routes) {
            SettingsScreen(navController = navController)
        }

        composable(route = Routes.ProfileScreen.routes) {
            ProfileScreen(navController = navController)
        }

    }
}