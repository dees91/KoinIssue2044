package pl.deesoft.koinissue2044.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.deesoft.koinissue2044.ContainerScope
import pl.deesoft.koinissue2044.domain.GetGreetingUseCase
import pl.deesoft.koinissue2044.ui.MainViewModel
import pl.deesoft.koinissue2044.ui.SecondViewModel
import pl.deesoft.koinissue2044.ui.ThirdViewModel

val appModule = module {

    singleOf(::GetGreetingUseCase)

    scope<ContainerScope> {

        viewModelOf(::MainViewModel)
        viewModelOf(::SecondViewModel)
        viewModelOf(::ThirdViewModel)

    }
}
