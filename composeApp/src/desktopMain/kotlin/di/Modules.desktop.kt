package di

import androidx.room.Room
import androidx.room.RoomDatabase
import data.local.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual val sharedModule: Module
    get() = module {
        single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder() }
    }

val dataBaseFile: File = File(System.getProperty("java.io.tmpdir"), "room_desktop.db")

fun getDatabaseBuilder() = Room.databaseBuilder<AppDatabase>(
        name = dataBaseFile.absolutePath
    )


actual fun deleteDbFile() {
    dataBaseFile.delete()
}