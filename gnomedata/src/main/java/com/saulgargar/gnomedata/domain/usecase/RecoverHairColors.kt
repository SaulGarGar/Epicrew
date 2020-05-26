package com.saulgargar.gnomedata.domain.usecase

import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.domain.model.HairColor

class RecoverHairColors(private val repository: GnomesRepository) :
    UseCase<List<HairColor>,UseCase.None>() {
        override suspend fun run(params: UseCase.None) = repository.recoverHairColors()
}