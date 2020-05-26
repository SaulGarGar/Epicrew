package com.saulgargar.gnomedata.data.repository

import com.saulgargar.androidext.NetworkHandler
import com.saulgargar.domain.Either
import com.saulgargar.domain.Failure
import com.saulgargar.gnomedata.data.datasource.remote.GnomesRemoteDataSource
import com.saulgargar.gnomedata.data.datasource.remote.model.toDomain
import com.saulgargar.gnomedata.domain.model.GnomeUser

class GnomesRepositoryImpl(private val networkHandler: NetworkHandler,
                           private val remoteDataSource: GnomesRemoteDataSource): GnomesRepository {

    override suspend fun getGnomeData() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(remoteDataSource.getGnomeData().toDomain())
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }
}