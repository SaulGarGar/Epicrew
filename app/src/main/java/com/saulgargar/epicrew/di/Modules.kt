package com.saulgargar.epicrew.di

import com.saulgargar.epicrew.presentation.viewmodel.GnomeDataViewModel
import com.saulgargar.gnomedata.di.useCaseModule
import com.saulgargar.gnomedata.di.repositoryModule
import com.saulgargar.gnomedata.di.dataSourceModule
import com.saulgargar.gnomedata.di.networkModule
import com.saulgargar.gnomedata.di.networkHandlerModule
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
            networkModule,
            networkHandlerModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel {
        GnomeDataViewModel(getGnomeDataUseCase = get())
    }
}
