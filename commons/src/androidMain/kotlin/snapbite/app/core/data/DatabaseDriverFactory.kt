package snapbite.app.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import snapbite.app.database.SnapbiteDatabase

actual class DatabaseDriverFactory(
    private val context: Context,
    private val inMemory: Boolean = false
) {
    actual fun createSnapbiteDatabase(): SqlDriver {
        return if (inMemory) {
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
                SnapbiteDatabase.Schema.create(this)
            }
        } else {
            return AndroidSqliteDriver(
                schema = SnapbiteDatabase.Schema,
                context = context,
                name = "snapbiteDatabase.db"
            )
        }
    }

}