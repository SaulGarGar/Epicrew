package com.saulgargar.gnomedata.data.repository

import com.saulgargar.androidext.NetworkHandler
import com.saulgargar.domain.Either
import com.saulgargar.domain.Failure
import com.saulgargar.gnomedata.data.datasource.local.GnomesLocalDataSource
import com.saulgargar.gnomedata.data.datasource.local.toDomain
import com.saulgargar.gnomedata.data.datasource.remote.GnomesRemoteDataSource
import com.saulgargar.gnomedata.data.datasource.remote.model.toDomain
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.model.Profession

class GnomesRepositoryImpl(private val networkHandler: NetworkHandler,
                           private val remoteDataSource: GnomesRemoteDataSource,
                            private val localDataSource: GnomesLocalDataSource): GnomesRepository {

    override suspend fun getGnomes() = try {
        when (networkHandler.isConnected) {
            true -> {
                val result = remoteDataSource.getGnomeData()

                localDataSource.saveGnomes(result.toDomain())
                localDataSource.saveProfessions(ExtraDataCompiler.getAllProfessions(result.brastlewark))
                localDataSource.saveHairColors(ExtraDataCompiler.getAllHairColors(result.brastlewark))

                Either.Right(localDataSource.recoverGnomes())
            }
            else -> Either.Left(Failure.NetworkConnection)
        }
    } catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun recoverGnomes() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(localDataSource.recoverGnomes())
            else -> Either.Left(Failure.NetworkConnection)
        }
    }catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }


    override suspend fun saveGnomes(gnomes: List<GnomeUser>) = try {
        Either.Right(localDataSource.saveGnomes(gnomes))
    } catch (ex: Exception){
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun findGnomeByName(name: String) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(localDataSource.findGnomeByName(name))
            else -> Either.Left(Failure.NetworkConnection)
        }
    }catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun recoverHairColors() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(localDataSource.recoverHairColors())
            else -> Either.Left(Failure.NetworkConnection)
        }
    }catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun saveHairColors(colors: List<HairColor>) = try {
        Either.Right(localDataSource.saveHairColors(colors))
    } catch (ex: Exception){
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun recoverProfessions() = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(localDataSource.recoverProfessions())
            else -> Either.Left(Failure.NetworkConnection)
        }
    }catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun saveProfessions(professions: List<Profession>) = try {
        Either.Right(localDataSource.saveProfessions(professions))
    } catch (ex: Exception){
        Either.Left(Failure.GenericError(ex))
    }

    override suspend fun findGnomeById(id: Int) = try {
        when (networkHandler.isConnected) {
            true -> Either.Right(localDataSource.findGnomeById(id).toDomain())
            else -> Either.Left(Failure.NetworkConnection)
        }
    }catch (ex: Exception) {
        Either.Left(Failure.GenericError(ex))
    }
}