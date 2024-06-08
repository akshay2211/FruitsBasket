package data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import models.FruitEntity


@Database(entities = [FruitEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(), ClearInterface {
    abstract fun fruitDao(): FruitDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface ClearInterface {
    fun clearAllTables() {}
}