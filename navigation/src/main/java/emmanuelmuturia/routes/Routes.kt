package emmanuelmuturia.routes

sealed class Routes(val routes: String) {

    data object WelcomeScreen : Routes(routes = "welcomeScreen")

    data object HomeScreen : Routes(routes = "homeScreen")

    data object SearchScreen : Routes(routes = "searchScreen")

    data object NotificationsScreen : Routes(routes = "notificationsScreen")

    data object SettingsScreen : Routes(routes = "settingsScreen")

    data object ProfileScreen : Routes(routes = "profileScreen")

}
