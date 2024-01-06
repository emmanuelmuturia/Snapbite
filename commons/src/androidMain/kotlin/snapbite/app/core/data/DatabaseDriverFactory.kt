package snapbite.app.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import snapbite.app.database.FoodDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = FoodDatabase.Schema,
            context = context,
            name = "food.db"
        )
    }
}