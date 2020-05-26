package com.saulgargar.gnomedata.domain.usecase

import com.saulgargar.domain.Either
import com.saulgargar.domain.Failure
import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.domain.model.GnomeUser

class GetGnomeDataUseCase(private val repository: GnomesRepository) :
    UseCase<List<GnomeUser>,UseCase.None>() {
    override suspend fun run(params: None) = repository.getGnomeData()
}