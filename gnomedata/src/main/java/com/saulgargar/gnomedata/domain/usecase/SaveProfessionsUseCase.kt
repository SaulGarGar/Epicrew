package com.saulgargar.gnomedata.domain.usecase

import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.domain.model.Profession

class SaveProfessionsUseCase(private val repository: GnomesRepository):
    UseCase<Unit, SaveProfessionsUseCase.Params>() {
    override suspend fun run(params: Params) = repository.saveProfessions(params.professions)

    data class Params(val professions: List<Profession>)
}