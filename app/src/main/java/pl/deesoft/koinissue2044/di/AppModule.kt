package pl.deesoft.koinissue2044.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.deesoft.koinissue2044.domain.GetGreetingUseCase
import pl.deesoft.koinissue2044.ui.MainViewModel

val appModule = module {
    // Use case
    single { GetGreetingUseCase() }

    // ViewModel
    viewModel { MainViewModel(get()) }
}
