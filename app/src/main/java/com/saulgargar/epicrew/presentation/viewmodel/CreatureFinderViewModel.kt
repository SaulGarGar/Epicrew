package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.State
import com.saulgargar.domain.UseCase
import com.saulgargar.epicrew.presentation.model.PhysicalFeaturesQuery
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.usecase.RecoverGnomesUseCase
import com.saulgargar.gnomedata.domain.usecase.RecoverHairColors

class CreatureFinderViewModel(private val recoverGnomesUseCase: RecoverGnomesUseCase,
                              private val recoverHairColors: RecoverHairColors): ViewModel() {

    private val _stateGetHairColors = MutableLiveData<State>()
    val stateGetHairColors: LiveData<State>
        get() = _stateGetHairColors

    private var hairColors = listOf<HairColor>()
    val physicalFeaturesQuery = PhysicalFeaturesQuery()

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
}