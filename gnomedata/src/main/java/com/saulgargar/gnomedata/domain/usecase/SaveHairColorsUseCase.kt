package com.saulgargar.gnomedata.domain.usecase

import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.data.repository.GnomesRepository
import com.saulgargar.gnomedata.domain.model.HairColor

class SaveHairColorsUseCase(private val repository: GnomesRepository):
    UseCase<Unit, SaveHairColorsUseCase.Params>() {
    override suspend fun run(params: Params) = repository.saveHairColors(params.colors)

    data class Params(val colors: List<HairColor>)
}