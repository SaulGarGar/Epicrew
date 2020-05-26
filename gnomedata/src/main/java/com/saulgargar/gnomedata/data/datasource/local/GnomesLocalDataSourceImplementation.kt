package com.saulgargar.gnomedata.data.datasource.local

import com.saulgargar.gnomedata.domain.model.GnomeUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GnomesLocalDataSourceImplementation(private val gnomeDao: GnomeDao): GnomesLocalDataSource {
    override suspend fun saveGnomes(gnomes: List<GnomeUser>) =
        withContext(Dispatchers.IO) {
            gnomeDao.insertGnomes(gnomes.map { it.toGnomeUserDB() })
        }

    override suspend fun getAllGnomes(): List<GnomeUser> =
        withContext(Dispatchers.IO) { gnomeDao.getAllGnomes().map { it.toDomain() } }


    override suspend fun findGnomeByName(name: String): List<GnomeUser> =
        withContext(Dispatchers.IO) { gnomeDao.findByName(name).map { it.toDomain() } }

}
