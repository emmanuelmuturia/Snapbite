package emmanuelmuturia.navigation.navgraph

import android.app.Activity.RESULT_OK
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.identity.Identity
import emmanuelmuturia.about.AboutScreen
import emmanuelmuturia.food.day.DayScreen
import emmanuelmuturia.food.day.DayScreenViewModel
import emmanuelmuturia.faq.FAQScreen
import emmanuelmuturia.food.CreateFoodScreen
import emmanuelmuturia.food.EditFoodScreen
import emmanuelmuturia.food.ViewFoodScreen
import emmanuelmuturia.google.GoogleAuthUiClient
import emmanuelmuturia.google.SignInScreen
import emmanuelmuturia.google.SignInViewModel
import emmanuelmuturia.home.HomeScreen
import emmanuelmuturia.home.WelcomeScreen
import emmanuelmuturia.notifications.NotificationsScreen
import emmanuelmuturia.food.photography.PhotoScreen
import emmanuelmuturia.profile.ProfileScreen
import emmanuelmuturia.navigation.routes.Routes
import emmanuelmuturia.search.SearchScreen
import emmanuelmuturia.settings.SettingsScreen
import emmanuelmuturia.commons.SnapbiteSharedPreferences
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {

    val isFirstTimeUser =
        emmanuelmuturia.commons.SnapbiteSharedPreferences(context = LocalContext.current).isFirstTimeUser

    val context = LocalContext.current

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val scope = rememberCoroutineScope()

    val dayScreenViewModel: emmanuelmuturia.food.day.DayScreenViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = if (isFirstTimeUser) {
            Routes.WelcomeScreen.route
        } else {
            Routes.HomeScreen.route
        }
    ) {

        composable(route = Routes.HomeScreen.route) {
            emmanuelmuturia.home.HomeScreen(
                navigateToProfileScreen = { navController.navigate(route = Routes.SignInScreen.route) },
                navigateToSettingsScreen = { navController.navigate(route = Routes.SettingsScreen.route) },
                navigateToNotificationsScreen = { navController.navigate(route = Routes.NotificationsScreen.route) },
                navigateToSearchScreen = { navController.navigate(route = Routes.SearchScreen.route) },
                navController = navController
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
            ProfileScreen(
                navigateBack = { navController.popBackStack() },
                onSignOut = {
                    scope.launch { googleAuthUiClient.signOut() }
                    Toast.makeText(context, "You have signed out successfully!", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                            },
                userData = googleAuthUiClient.getSignedInUser()
            )
        }

        composable(
            route = Routes.DayScreen.route
            //, arguments = listOf(navArgument(name = "dayId") {
            //type = NavType.IntType })
        ) {
            emmanuelmuturia.food.day.DayScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController
            )
        }

        composable(
            route = Routes.EditFoodScreen.route, arguments = listOf(navArgument(name = "foodId") {
                type = NavType.IntType
            })
        ) {
            emmanuelmuturia.food.EditFoodScreen(navController = navController)
        }

        composable(route = Routes.ViewFoodScreen.route) {
            emmanuelmuturia.food.ViewFoodScreen(navController = navController)
        }

        composable(
            route = Routes.PhotoScreen.route
            //, arguments = listOf(navArgument(name = "dayId") {
            //type = NavType.IntType })
        ) {
            emmanuelmuturia.food.photography.PhotoScreen(
                navController = navController,
                dayScreenViewModel = dayScreenViewModel
            )
        }

        composable(route = Routes.FAQScreen.route) {
            emmanuelmuturia.faq.FAQScreen { navController.popBackStack() }
        }

        composable(route = Routes.AboutScreen.route) {
            emmanuelmuturia.about.AboutScreen { navController.popBackStack() }
        }

        composable(route = Routes.WelcomeScreen.route) {

            val sharedPreferences =
                emmanuelmuturia.commons.SnapbiteSharedPreferences(
                    context = context
                ).sharedPreferences

            emmanuelmuturia.home.WelcomeScreen {
                sharedPreferences.edit().putBoolean("isFirstTimeUser", false).apply()
                navController.navigate(route = Routes.HomeScreen.route)
            }

        }

        composable(route = Routes.CreateFoodScreen.route) {
            emmanuelmuturia.food.CreateFoodScreen(
                navController = navController,
                dayScreenViewModel = dayScreenViewModel
            )
        }

        composable(route = Routes.SignInScreen.route) {

            val signInViewModel: SignInViewModel = hiltViewModel()
            val state by signInViewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(route = Routes.ProfileScreen.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            signInViewModel.onSignInResult(result = signInResult)
                        }
                    }
                })

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(context, "You have signed in successfully!", Toast.LENGTH_LONG).show()
                    navController.navigate(route = Routes.ProfileScreen.route)
                    signInViewModel.resetState()
                }
            }

            SignInScreen(navigateBack = { navController.popBackStack() }, onSignInClick = {
                scope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            }, signInState = state)
        }

    }
}