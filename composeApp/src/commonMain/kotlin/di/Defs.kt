package di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.local.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    // commented to avoid db cleanup on each start
    // deleteDbFile()
    return builder.fallbackToDestructiveMigrationOnDowngrade(true).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO).build()
}

fun getFruitDao(appDatabase: AppDatabase) = appDatabase.fruitDao()