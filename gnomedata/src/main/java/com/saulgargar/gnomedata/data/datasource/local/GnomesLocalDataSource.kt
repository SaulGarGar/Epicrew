package com.saulgargar.gnomedata.data.datasource.local

import com.saulgargar.gnomedata.domain.model.GnomeUser

interface GnomesLocalDataSource {
    suspend fun saveGnomes(gnomes: List<GnomeUser>)
    suspend fun getAllGnomes(): List<GnomeUser>
    suspend fun findGnomeByName(name: String): List<GnomeUser>
}