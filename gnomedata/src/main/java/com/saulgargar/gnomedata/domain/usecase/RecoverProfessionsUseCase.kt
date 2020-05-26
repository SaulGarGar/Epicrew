package com.saulgargar.gnomedata.domain.usecase

import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.domain.model.Profession

class RecoverProfessionsUseCase(private val repository: GnomesRepository) :
    UseCase<List<Profession>, UseCase.None>() {
    override suspend fun run(params: UseCase.None) = repository.recoverProfessions()
}