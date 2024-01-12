package snapbite.app.about.domain

expect interface AboutRepository {

    suspend fun getPrivacyPolicy()

    suspend fun getTermsAndConditions()

    fun getAppVersion(): String

}