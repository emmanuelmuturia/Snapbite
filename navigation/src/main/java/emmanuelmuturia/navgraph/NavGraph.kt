package emmanuelmuturia.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import emmanuelmuturia.home.HomeScreen
import emmanuelmuturia.routes.Routes
import emmanuelmuturia.welcome.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.routes) {

        composable(route = Routes.WelcomeScreen.routes) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Routes.HomeScreen.routes) {
            HomeScreen(navController = navController)
        }

    }
}