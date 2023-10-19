package emmanuelmuturia.routes

sealed class Routes(val routes: String) {

    data object WelcomeScreen : Routes(routes = "welcomeScreen")

}
