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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.identity.Identity
import emmanuelmuturia.navigation.routes.Routes
import kotlinx.coroutines.launch
import snapbite.commons.about.ui.AboutScreen
import snapbite.commons.commons.sharedpreferences.SnapbiteSharedPreferences
import snapbite.commons.faq.ui.FAQScreen
import snapbite.commons.food.day.DayScreen
import snapbite.commons.food.day.DayScreenViewModel
import snapbite.commons.food.photography.PhotoScreen
import snapbite.commons.food.ui.CreateFoodScreen
import snapbite.commons.food.ui.EditFoodScreen
import snapbite.commons.food.ui.ViewFoodScreen
import snapbite.commons.home.ui.HomeScreen
import snapbite.commons.home.ui.WelcomeScreen
import snapbite.commons.notifications.ui.NotificationsScreen
import snapbite.commons.profile.google.GoogleAuthUiClient
import snapbite.commons.profile.google.SignInScreen
import snapbite.commons.profile.google.SignInViewModel
import snapbite.commons.profile.ui.ProfileScreen
import snapbite.commons.search.ui.SearchScreen
import snapbite.commons.settings.ui.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {

    val isFirstTimeUser =
        SnapbiteSharedPreferences(context = LocalContext.current).isFirstTimeUser

    val context = LocalContext.current

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val scope = rememberCoroutineScope()

    val dayScreenViewModel: DayScreenViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = if (isFirstTimeUser) {
            Routes.WelcomeScreen.route
        } else {
            Routes.HomeScreen.route
        }
    ) {

        composable(route = Routes.HomeScreen.route) {
            HomeScreen(
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
            DayScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController
            )
        }

        composable(
            route = Routes.EditFoodScreen.route, arguments = listOf(navArgument(name = "foodId") {
                type = NavType.IntType
            })
        ) {
            EditFoodScreen(navController = navController)
        }

        composable(route = Routes.ViewFoodScreen.route) {
            ViewFoodScreen(navController = navController)
        }

        composable(
            route = Routes.PhotoScreen.route
            //, arguments = listOf(navArgument(name = "dayId") {
            //type = NavType.IntType })
        ) {
            PhotoScreen(
                navController = navController,
                dayScreenViewModel = dayScreenViewModel
            )
        }

        composable(route = Routes.FAQScreen.route) {
            FAQScreen { navController.popBackStack() }
        }

        composable(route = Routes.AboutScreen.route) {
            AboutScreen { navController.popBackStack() }
        }

        composable(route = Routes.WelcomeScreen.route) {

            val sharedPreferences =
                SnapbiteSharedPreferences(
                    context = context
                ).sharedPreferences

            WelcomeScreen {
                sharedPreferences.edit().putBoolean("isFirstTimeUser", false).apply()
                navController.navigate(route = Routes.HomeScreen.route)
            }

        }

        composable(route = Routes.CreateFoodScreen.route) {
            CreateFoodScreen(
                navController = navController,
                dayScreenViewModel = dayScreenViewModel
            )
        }

        composable(route = Routes.SignInScreen.route) {

            val signInViewModel: SignInViewModel = hiltViewModel()
            val state by signInViewModel.state.collectAsState()

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