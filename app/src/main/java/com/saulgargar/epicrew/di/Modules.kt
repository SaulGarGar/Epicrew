package com.saulgargar.epicrew.di

import com.saulgargar.epicrew.presentation.viewmodel.ExpertFinderViewModel
import com.saulgargar.epicrew.presentation.viewmodel.SplashViewModel
import com.saulgargar.gnomedata.di.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeatures() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        arrayListOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            dbModule,
            networkModule,
            networkHandlerModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel {
        SplashViewModel(getGnomesUseCase = get())
    }

    viewModel {
        ExpertFinderViewModel(recoverProfessionsUseCase = get(), recoverGnomesUseCase = get())
    }
}
