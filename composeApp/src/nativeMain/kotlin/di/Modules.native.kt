package di

import androidx.room.Room
import androidx.room.RoomDatabase
import data.local.database.AppDatabase
import data.local.database.instantiateImpl
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory

actual val sharedModule: Module
    get() = module {
        single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder()  }
    }
val fileName = NSHomeDirectory() + "/room_ios.db"
fun getDatabaseBuilder() = Room.databaseBuilder<AppDatabase>(
        name = fileName,
        factory =  { AppDatabase::class.instantiateImpl() }
    )

@OptIn(ExperimentalForeignApi::class)
actual fun deleteDbFile() {
    NSFileManager.defaultManager.removeItemAtPath(fileName, null)
}