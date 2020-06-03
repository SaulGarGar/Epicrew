package com.saulgargar.gnomedata.domain.usecase

import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.domain.model.GnomeUser

class RecoverGnomeByIdUseCase(private val repository: GnomesRepository) :
    UseCase<GnomeUser,RecoverGnomeByIdUseCase.Params>(){
    override suspend fun run(params: Params) = repository.recoverGnomeById(id = params.id)

    data class Params(val id: Int)
}