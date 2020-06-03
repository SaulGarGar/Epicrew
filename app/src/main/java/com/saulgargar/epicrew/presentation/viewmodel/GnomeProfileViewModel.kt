package com.saulgargar.epicrew.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulgargar.domain.Failure
import com.saulgargar.domain.State
import com.saulgargar.gnomedata.domain.model.Gnome
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.toDomain
import com.saulgargar.gnomedata.domain.usecase.RecoverGnomeByIdUseCase

class GnomeProfileViewModel(private val recoverGnomeByIdUseCase: RecoverGnomeByIdUseCase): ViewModel() {

    var gnome = Gnome()

    private val _stateRecoverGnome = MutableLiveData<State>()
    val stateRecoverGnome: LiveData<State>
        get() = _stateRecoverGnome

    fun recoverGnomeById(){
        val params = RecoverGnomeByIdUseCase.Params(gnome.id)
        recoverGnomeByIdUseCase.invoke(viewModelScope, params){
            it.either(::handleRecoverGnomeFailure, ::handleRecoverGnomeSuccess)
        }
    }

    private fun handleRecoverGnomeSuccess(gnome: GnomeUser) {
        this.gnome = gnome.toDomain()
        _stateRecoverGnome.value = State.Success(this.gnome)
    }

    private fun handleRecoverGnomeFailure(failure: Failure) {
        _stateRecoverGnome.value = State.Failed(failure)
    }
}