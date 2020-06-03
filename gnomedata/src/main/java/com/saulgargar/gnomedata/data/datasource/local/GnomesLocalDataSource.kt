package com.saulgargar.gnomedata.data.datasource.local

import com.saulgargar.gnomedata.data.datasource.local.dao.GnomeDao
import com.saulgargar.gnomedata.data.datasource.local.dao.HairColorDao
import com.saulgargar.gnomedata.data.datasource.local.dao.ProfessionDao
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.model.Profession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GnomesLocalDataSource(private val gnomeDao: GnomeDao,
                            private val hairColorDao: HairColorDao,
                            private val professionDao: ProfessionDao){

    suspend fun saveGnomes(gnomes: List<GnomeUser>) =
        withContext(Dispatchers.IO) {
            gnomeDao.insertGnomes(gnomes.map { it.toGnomeUserDB() })
        }

    suspend fun recoverGnomes(): List<GnomeUser> =
        withContext(Dispatchers.IO) { gnomeDao.recoverGnomes().map { it.toDomain() } }


    suspend fun findGnomeByName(name: String) =
        withContext(Dispatchers.IO) { gnomeDao.findGnomeByName(name).map { it.toDomain() } }

    suspend fun saveHairColors(colors: List<HairColor>) {
        withContext(Dispatchers.IO) {
            hairColorDao.insertHairColors(colors.map { it.toHairColorDB() }) }
    }

    suspend fun recoverHairColors(): List<HairColor> =
        withContext(Dispatchers.IO) { hairColorDao.recoverHairColors().map { it.toDomain() } }

    suspend fun saveProfessions(professions: List<Profession>) {
        withContext(Dispatchers.IO) {
            professionDao.insertProfessions(professions.map { it.toProfessionDB() }) }
    }

    suspend fun recoverProfessions(): List<Profession> =
        withContext(Dispatchers.IO) { professionDao.recoverProfessions().map { it.toDomain() } }

    suspend fun recoverGnomeById(id: Int) =
        withContext(Dispatchers.IO) {gnomeDao.recoverGnomeById(id)}
}
