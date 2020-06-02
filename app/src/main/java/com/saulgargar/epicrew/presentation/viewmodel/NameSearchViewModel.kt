package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.State
import com.saulgargar.domain.UseCase
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.usecase.RecoverGnomesUseCase

class NameSearchViewModel(private val recoverGnomesUseCase: RecoverGnomesUseCase):ViewModel() {

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
        this.gnomes = gnomes
        _stateRecoverGnomes.value = State.Success(gnomes)
    }

    private fun handleRecoverGnomesFailure(failure: Failure) {
        _stateRecoverGnomes.value = State.Failed(failure)
    }
}