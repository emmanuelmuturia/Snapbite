package snapbite.app.core.data

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {

    fun create(): SqlDriver

}