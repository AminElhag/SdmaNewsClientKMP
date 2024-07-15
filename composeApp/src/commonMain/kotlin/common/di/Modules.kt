package common.di

import features.main.data.remote.MainClient
import features.main.data.repository.MainApi
import features.main.data.repository.MainRepository
import features.main.presentions.viewmodel.MainViewModel
import networking.createHttpClient
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        createHttpClient(get())
    }
    single {
        MainClient(get())
    }
    single {
        MainRepository(get())
    }.bind<MainApi>()
    viewModelOf(::MainViewModel)
}