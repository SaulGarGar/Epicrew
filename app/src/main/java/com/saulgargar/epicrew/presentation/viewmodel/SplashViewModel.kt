package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.State
import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.usecase.GetGnomesUseCase

class SplashViewModel (private val getGnomesUseCase: GetGnomesUseCase): ViewModel(){

    private val _stateGetGnomes = MutableLiveData<State>()
    val stateGetGnomes: LiveData<State>
        get() = _stateGetGnomes

    fun getGnomes() {
        getGnomesUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleGetGnomesFailure, ::handleGetGnomesSuccess)
        }
    }

    private fun handleGetGnomesSuccess(gnomes: List<GnomeUser>) {
        _stateGetGnomes.value = State.Success(gnomes)
    }

    private fun handleGetGnomesFailure(failure: Failure) {
        _stateGetGnomes.value = State.Failed(failure)
    }
}