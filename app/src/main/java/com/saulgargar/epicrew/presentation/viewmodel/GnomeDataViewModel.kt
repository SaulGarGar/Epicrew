package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.usecase.RecoverGnomesUseCase
import com.saulgargar.gnomedata.domain.usecase.SaveGnomesUseCase

class GnomeDataViewModel (private val recoverGnomesUseCase: RecoverGnomesUseCase,
                          private val saveGnomesUseCase: SaveGnomesUseCase): ViewModel(){

    fun getGnomeData() {
        recoverGnomesUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleGnomeDataFailure, ::handleGnomeDataSuccess)
        }
    }

    private fun handleGnomeDataSuccess(gnomes: List<GnomeUser>) {
        obtainAllProfessions(gnomes)
        obtainAllHairColors(gnomes)
        val params = SaveGnomesUseCase.Params(gnomes)
        saveGnomesUseCase.invoke(viewModelScope,params){
            it
        }
    }

    private fun handleGnomeDataFailure(failure: Failure) {
        failure.toString()
    }

    private fun obtainAllProfessions(gnomes: List<GnomeUser>){
        val professions = arrayListOf<String>()

        for (gnome in gnomes){
            for (profession in gnome.professions){
                professions.add(profession)
            }
        }

        val cleanedProfessions = professions.distinct()
        cleanedProfessions.toString()
    }

    private fun obtainAllHairColors(gnomes: List<GnomeUser>){
        val colors = arrayListOf<String>()

        for (gnome in gnomes){
            colors.add(gnome.hairColor)
        }

        val cleanedColors = colors.distinct()
        cleanedColors.toString()
    }

    private companion object {

    }
}