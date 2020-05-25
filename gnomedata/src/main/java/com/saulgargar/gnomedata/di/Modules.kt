package com.saulgargar.gnomedata.di

import com.saulgargar.androidext.NetworkHandler
import com.saulgargar.network.createNetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val useCaseModule: Module = module {
    factory { GetSummaryInfoUseCase(repository = get()) }
    factory { GetDataByStatusUseCase(repository = get()) }
}

val repositoryModule: Module = module {
    single { StatsRepositoryImpl(networkHandler = get(), remoteDataSource = get()) as StatsRepository }
}

val dataSourceModule: Module = module {
    single { StatsRemoteDataSource(statsApi = get()) }
}

val networkModule: Module = module {
    single { get<Retrofit>().create(StatsApi::class.java) }
    single { createNetworkClient(baseUrl = BASE_URL, debug = BuildConfig.DEBUG, context = get()) }
}

val networkHandlerModule: Module = module {
    single { NetworkHandler(context = get()) }
}