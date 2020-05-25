package com.saulgargar.gnomedata.data.datasource.remote

import com.saulgargar.gnomedata.data.datasource.remote.model.GnomeInfoResponse
import retrofit2.http.GET

interface GnomesApi {
    @GET("data.json")
    suspend fun getGnomeData(): GnomeInfoResponse
}