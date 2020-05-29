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
import com.saulgargar.gnomedata.domain.usecase.RecoverGnomesUseCase
import com.saulgargar.gnomedata.domain.usecase.RecoverProfessionsUseCase

class ExpertFinderViewModel(private val recoverProfessionsUseCase: RecoverProfessionsUseCase,
                            private val recoverGnomesUseCase: RecoverGnomesUseCase): ViewModel() {

    private val _stateGetProfessions = MutableLiveData<State>()
    val stateGetProfessions: LiveData<State>
        get() = _stateGetProfessions

    private val _stateRecoverGnomes = MutableLiveData<State>()
    val stateRecoverGnomes: LiveData<State>
        get() = _stateRecoverGnomes

    private val _stateFilteredGnomes = MutableLiveData<State>()
    val stateFilteredGnomes: LiveData<State>
        get() = _stateFilteredGnomes

    private var professions = listOf<Profession>()
    private var gnomes = listOf<GnomeUser>()
    private var filteredGnomes = mutableListOf<GnomeUser>()

    fun getProfessions() {
        recoverProfessionsUseCase.invoke(viewModelScope, UseCase.None()) {
            it.either(::handleRecoverProfessionsFailure, ::handleRecoverProfessionsSuccess)
        }
    }

    private fun handleRecoverProfessionsSuccess(professions: List<Profession>) {
        this.professions = professions
        _stateGetProfessions.value = State.Success(professions)
    }

    private fun handleRecoverProfessionsFailure(failure: Failure) {
        _stateGetProfessions.value = State.Failed(failure)
    }

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
        _stateGetProfessions.value = State.Failed(failure)
    }

    fun filterGnomes(id: Int){
        val professionSelected = professions[id]
        filteredGnomes.removeAll { true }

        for (gnome in gnomes){
            for (profession in gnome.professions){
                if (professionSelected.profession == profession){
                    filteredGnomes.add(gnome)
                }
            }
        }

        _stateFilteredGnomes.value = State.Success(filteredGnomes)
    }
}