package com.saulgargar.gnomedata.domain.usecase

import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.domain.model.GnomeUser

class SaveGnomesUseCase(private val repository: GnomesRepository):
    UseCase<Unit,SaveGnomesUseCase.Params>() {
    override suspend fun run(params: Params) = repository.saveGnomes(params.gnomes)

    data class Params(val gnomes: List<GnomeUser>)
}

