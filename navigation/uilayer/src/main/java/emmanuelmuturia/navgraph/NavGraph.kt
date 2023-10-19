package emmanuelmuturia.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import emmanuelmuturia.routes.Routes
import emmanuelmuturia.welcomescreen.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.WelcomeScreen.routes) {
        composable(route = Routes.WelcomeScreen.routes) {
            WelcomeScreen()
        }
    }
}