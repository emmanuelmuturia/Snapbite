package snapbite.commons.commons.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SnapbiteSharedPreferences(context: Context) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    val isFirstTimeUser = sharedPreferences.getBoolean("isFirstTimeUser", true)

}