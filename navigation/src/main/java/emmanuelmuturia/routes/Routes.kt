package emmanuelmuturia.routes

sealed class Routes(val route: String) {

    data object WelcomeScreen : Routes(route = "welcomeScreen")

    data object HomeScreen : Routes(route = "homeScreen")

    data object SearchScreen : Routes(route = "searchScreen")

    data object NotificationsScreen : Routes(route = "notificationsScreen")

    data object SettingsScreen : Routes(route = "settingsScreen")

    data object ProfileScreen : Routes(route = "profileScreen")

    data object DayScreen : Routes(route = "dayScreen")

    data object EditFoodScreen : Routes(route = "editFoodScreen")

    data object ViewFoodScreen : Routes(route = "viewFoodScreen")

    data object PhotoScreen : Routes(route = "photoScreen")

    data object FAQScreen : Routes(route = "faqScreen")

    data object AboutScreen : Routes(route = "aboutScreen")

}
