package di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import data.local.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedModule: Module
    get() = module {
        single { getDatabaseBuilder(get())  }
    }


fun getDatabaseBuilder(context: Context) = with(context) {
        val dbFile = applicationContext.getDatabasePath("room_android.db")
        Room.databaseBuilder<AppDatabase>(
            context = applicationContext,
            name = dbFile.absolutePath
        )
    }

actual fun deleteDbFile() {
}