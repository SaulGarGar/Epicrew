package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.State
import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.Profession
import com.saulgargar.gnomedata.domain.usecase.RecoverProfessionsUseCase

class ExpertFinderViewModel(private val recoverProfessionsUseCase: RecoverProfessionsUseCase): ViewModel() {

    private val _stateGetProfessions = MutableLiveData<State>()
    val stateGetProfessions: LiveData<State>
        get() = _stateGetProfessions

    fun getProfessions() {
        recoverProfessionsUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleGetGnomesFailure, ::handleRecoverProfessionsSuccess)
        }
    }

    private fun handleRecoverProfessionsSuccess(professions: List<Profession>) {
        _stateGetProfessions.value = State.Success(professions)
    }

    private fun handleGetGnomesFailure(failure: Failure) {
        _stateGetProfessions.value = State.Failed(failure)
    }
}