package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.State
import com.saulgargar.domain.UseCase
import com.saulgargar.epicrew.presentation.model.PhysicalFeaturesQuery
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.usecase.RecoverGnomesUseCase
import com.saulgargar.gnomedata.domain.usecase.RecoverHairColors

class CreatureFinderViewModel(private val recoverGnomesUseCase: RecoverGnomesUseCase,
                              private val recoverHairColors: RecoverHairColors): ViewModel() {

    private val _stateGetHairColors = MutableLiveData<State>()
    val stateGetHairColors: LiveData<State>
        get() = _stateGetHairColors

    private var hairColors = listOf<HairColor>()
    var physicalFeaturesQuery = PhysicalFeaturesQuery()

    private val _stateRecoverGnomes = MutableLiveData<State>()
    val stateRecoverGnomes: LiveData<State>
        get() = _stateRecoverGnomes

    private var gnomes = listOf<GnomeUser>()

    fun recoverGnomes(){
        recoverGnomesUseCase.invoke(viewModelScope, UseCase.None()){
            it.either(::handleRecoverGnomesFailure, ::handleRecoverGnomesSuccess)
        }
    }

    private fun handleRecoverGnomesSuccess(gnomes: List<GnomeUser>) {
        if (physicalFeaturesQuery.hairColor.isNullOrBlank()){
            filterGnomesNoHairColor(gnomes)
        }
        else{
            filterGnomes(gnomes)
        }
    }

    private fun handleRecoverGnomesFailure(failure: Failure) {
        _stateRecoverGnomes.value = State.Failed(failure)
    }

    fun getHairColors() {
        recoverHairColors.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleRecoverHairColorsFailure, ::handleRecoverHairColorsSuccess)
        }
    }

    private fun handleRecoverHairColorsSuccess(hairColors: List<HairColor>) {
        this.hairColors = hairColors
        _stateGetHairColors.value = State.Success(hairColors)
    }

    private fun handleRecoverHairColorsFailure(failure: Failure) {
        _stateGetHairColors.value = State.Failed(failure)
    }

    fun saveHairColor(position: Int){
        physicalFeaturesQuery.hairColor = hairColors[position].color
    }

    private fun filterGnomesNoHairColor(gnomes: List<GnomeUser>){
        val filteredGnomes = mutableListOf<GnomeUser>()

        for (gnome in gnomes){
            if (gnome.age in physicalFeaturesQuery.minAge..physicalFeaturesQuery.maxAge
                && gnome.age in physicalFeaturesQuery.minWeight..physicalFeaturesQuery.maxWeight
                && gnome.age in physicalFeaturesQuery.minHeight..physicalFeaturesQuery.maxHeight){
                filteredGnomes.add(gnome)
            }
        }

        this.gnomes = listOf()
        this.gnomes = filteredGnomes
        _stateRecoverGnomes.value = State.Success(this.gnomes)
    }

    private fun filterGnomes(gnomes: List<GnomeUser>){
        val filteredGnomes = mutableListOf<GnomeUser>()

        for (gnome in gnomes){
            if (gnome.age in physicalFeaturesQuery.minAge..physicalFeaturesQuery.maxAge
                && gnome.age in physicalFeaturesQuery.minWeight..physicalFeaturesQuery.maxWeight
                && gnome.age in physicalFeaturesQuery.minHeight..physicalFeaturesQuery.maxHeight
                && gnome.hairColor == physicalFeaturesQuery.hairColor){
                filteredGnomes.add(gnome)
            }
        }

        this.gnomes = listOf()
        this.gnomes = filteredGnomes
        _stateRecoverGnomes.value = State.Success(this.gnomes)
    }
}