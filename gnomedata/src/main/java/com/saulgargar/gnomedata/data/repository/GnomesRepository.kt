package com.saulgargar.gnomedata.data.repository

import com.saulgargar.domain.Either
import com.saulgargar.domain.Failure
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.model.Profession

interface GnomesRepository {
    suspend fun getGnomes(): Either<Failure, List<GnomeUser>>
    suspend fun recoverGnomes(): Either<Failure,List<GnomeUser>>
    suspend fun saveGnomes(gnomes:List<GnomeUser>): Either<Failure,Unit>
    suspend fun findGnomeByName(name: String): Either<Failure,List<GnomeUser>>
    suspend fun recoverHairColors(): Either<Failure,List<HairColor>>
    suspend fun saveHairColors(colors: List<HairColor>): Either<Failure,Unit>
    suspend fun recoverProfessions(): Either<Failure,List<Profession>>
    suspend fun saveProfessions(professions: List<Profession>): Either<Failure,Unit>
    suspend fun findGnomeById(id: Int): Either<Failure,GnomeUser>
}