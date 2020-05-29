package com.saulgargar.gnomedata.di

import com.saulgargar.androidext.NetworkHandler
import com.saulgargar.gnomedata.BuildConfig
import com.saulgargar.gnomedata.BuildConfig.BASE_URL
import com.saulgargar.gnomedata.data.datasource.local.GnomeDataBase
import com.saulgargar.gnomedata.data.datasource.local.GnomesLocalDataSource
import com.saulgargar.gnomedata.data.datasource.remote.GnomesApi
import com.saulgargar.gnomedata.data.datasource.remote.GnomesRemoteDataSource
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.data.repository.GnomesRepositoryImpl
import com.saulgargar.gnomedata.domain.usecase.GetGnomesUseCase
import com.saulgargar.gnomedata.domain.usecase.RecoverGnomesUseCase
import com.saulgargar.gnomedata.domain.usecase.RecoverProfessionsUseCase
import com.saulgargar.gnomedata.domain.usecase.SaveGnomesUseCase
import com.saulgargar.network.createNetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val useCaseModule: Module = module {
    factory { GetGnomesUseCase(repository = get()) }
    factory { SaveGnomesUseCase(repository = get()) }
    factory { RecoverProfessionsUseCase(repository = get()) }
    factory { RecoverGnomesUseCase(repository = get()) }
}

val repositoryModule: Module = module {
    single { GnomesRepositoryImpl(networkHandler = get(), remoteDataSource = get(), localDataSource = get()) as GnomesRepository}
}

val dataSourceModule: Module = module {
    single { GnomesRemoteDataSource(gnomesApi = get()) }
    single { GnomesLocalDataSource(gnomeDao = get(), hairColorDao = get(), professionDao = get()) as GnomesLocalDataSource}
}

val dbModule: Module = module {
    single { GnomeDataBase.build(context = get()) }
    single { get<GnomeDataBase>().getGnomeDao() }
    single { get<GnomeDataBase>().getHairColorDao() }
    single { get<GnomeDataBase>().getProfessionDao() }
}

val networkModule: Module = module {
    single { get<Retrofit>().create(GnomesApi::class.java) }
    single { createNetworkClient(baseUrl = BASE_URL, debug = BuildConfig.DEBUG, context = get()) }
}

val networkHandlerModule: Module = module {
    single { NetworkHandler(context = get()) }
}