package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.usecase.GetGnomeDataUseCase

class GnomeDataViewModel (private val getGnomeDataUseCase: GetGnomeDataUseCase): ViewModel(){




    fun getGnomeData() {
        getGnomeDataUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleDataFailure, ::handleDataSuccess)
        }
    }

    private fun handleDataSuccess(gnomes: List<GnomeUser>) {
        obtainAllProfessions(gnomes)
        obtainAllHairColors(gnomes)
    }

    private fun handleDataFailure(failure: Failure) {
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