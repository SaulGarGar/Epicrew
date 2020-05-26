package com.saulgargar.gnomedata.data.datasource.remote

class GnomesRemoteDataSource(private val gnomesApi: GnomesApi) {

    suspend fun getGnomeData() = gnomesApi.getGnomeData()
}