package com.saulgargar.gnomedata.data.repository

import com.saulgargar.domain.Either
import com.saulgargar.domain.Failure
import com.saulgargar.gnomedata.domain.model.GnomeUser

interface GnomesRepository {
    suspend fun getGnomeData(): Either<Failure, List<GnomeUser>>
    suspend fun recoverGnomeData(): List<GnomeUser>
    suspend fun saveGnomes(gnomes:List<GnomeUser>)
    suspend fun findGnomeByName(name: String): List<GnomeUser>
}