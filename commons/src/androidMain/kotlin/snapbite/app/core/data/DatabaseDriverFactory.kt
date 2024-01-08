package snapbite.app.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import snapbite.app.database.SnapbiteDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createSnapbiteDatabase(): SqlDriver {
        return AndroidSqliteDriver(
            schema = SnapbiteDatabase.Schema,
            context = context,
            name = "snapbiteDatabase.db"
        )
    }

}