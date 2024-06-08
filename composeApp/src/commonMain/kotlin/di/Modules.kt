package di

import data.repo.FruitRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import data.viewmodel.FruitViewModel

expect val sharedModule: Module

expect fun deleteDbFile()

val databaseModule = module {
    single { getRoomDatabase(get()) }
    single { getFruitDao(get()) }
}

val viewModelModule = module {
    single { FruitViewModel(get()) }
}
val repositoryModule = module {
    single { FruitRepository(get()) }
}

val appModule = arrayOf(databaseModule, repositoryModule, viewModelModule)