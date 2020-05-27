package com.saulgargar.epicrew.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.saulgargar.androidext.liveDataObserve
import com.saulgargar.domain.State

import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.presentation.viewmodel.ExpertFinderViewModel
import com.saulgargar.gnomedata.domain.model.Profession
import kotlinx.android.synthetic.main.fragment_expert_finder.*
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ExpertFinderFragment : BaseFragment() {

    private val expertFinderViewModel: ExpertFinderViewModel by viewModel()
    private lateinit var neighborhoodsDd: AutoCompleteTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_expert_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivityContext().loader.show()
        initViews(view)
        initObservers()
        initListeners()
        expertFinderViewModel.getProfessions()
    }

    private fun initObservers() {
        liveDataObserve(expertFinderViewModel.stateGetProfessions, ::onRegisterUserAccountStateChange)
    }

    private fun onRegisterUserAccountStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    getActivityContext().loader.dismiss()
                    val result = noNullState.responseTo<List<Profession>>()
                    initProfessionDropDown(professions = result)
                }
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
                else -> Timber.d("any state in getGnomes")
            }
        }
    }

    private fun initViews(view: View){
        neighborhoodsDd = view.findViewById(R.id.professionDd)
    }

    private fun initProfessionDropDown(professions: List<Profession>){
        val professionAdapter = ArrayAdapter(context!!,R.layout.drop_down_menu_item,professions.map { it.profession }.sorted())
        professionDd.setAdapter(professionAdapter)
    }

    private fun initListeners(){
        professionDd.setOnItemClickListener { _, _, position, _ ->
            instructionsLy.visibility = View.GONE
        }
    }
}
