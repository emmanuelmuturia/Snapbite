package snapbite.app.about.domain

actual interface AboutRepository {

    actual suspend fun getPrivacyPolicy()

    actual suspend fun getTermsAndConditions()

    actual fun getAppVersion(): String

}