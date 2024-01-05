package emmanuelmuturia.about.repository

import android.content.Context

interface AboutRepository {

    suspend fun getPrivacyPolicy(context: Context)

    suspend fun getTermsAndConditions(context: Context)

    fun getAppVersion(context: Context): String

}