package com.saulgargar.epicrew.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.GridLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulgargar.androidext.liveDataObserve
import com.saulgargar.domain.State

import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.presentation.adapter.GnomesRecyclerAdapter
import com.saulgargar.epicrew.presentation.interfaces.OnClickInGnome
import com.saulgargar.epicrew.presentation.viewmodel.ExpertFinderViewModel
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.Profession
import kotlinx.android.synthetic.main.fragment_expert_finder.*
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.exp

class ExpertFinderFragment : BaseFragment() {

    private val expertFinderViewModel: ExpertFinderViewModel by viewModel()
    private lateinit var neighborhoodsDd: AutoCompleteTextView

    private lateinit var gnomeAdapter: GnomesRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_expert_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivityContext().loader.show()
        initViews(view)
        initObservers()
        initAdapter()
        initListeners()
        initRecyclerView()
        expertFinderViewModel.getProfessions()
    }

    override fun onResume() {
        super.onResume()
        if (gnomeAdapter.itemCount != 0){
            instructionsLy.visibility = View.GONE
        }
    }

    private fun initObservers() {
        liveDataObserve(expertFinderViewModel.stateGetProfessions, ::onRecoverProfessionsStateChange)
        liveDataObserve(expertFinderViewModel.stateRecoverGnomes, ::onRecoverGnomesStateChange)
        liveDataObserve(expertFinderViewModel.stateFilteredGnomes, ::onFilteredGnomesStateChange)
    }

    private fun initAdapter() {
        gnomeAdapter = GnomesRecyclerAdapter()
    }

    private fun onRecoverProfessionsStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    val result = noNullState.responseTo<List<Profession>>()
                    initProfessionDropDown(professions = result)
                    expertFinderViewModel.recoverGnomes()
                }
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
            }
        }
    }

    private fun onRecoverGnomesStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> getActivityContext().loader.dismiss()
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
            }
        }
    }

    private fun onFilteredGnomesStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    getActivityContext().loader.dismiss()
                    val result = noNullState.responseTo<List<GnomeUser>>()
                    showGnomeList(result)
                }
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
            }
        }
    }

    private fun initRecyclerView(){
        recyclerView.run {
            this.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            this.adapter = gnomeAdapter
        }
    }

    private fun showGnomeList(gnomes: List<GnomeUser>){
        gnomeAdapter.setGnomeList(gnomes)
    }

    private fun initViews(view: View){
        neighborhoodsDd = view.findViewById(R.id.professionDd)
    }

    private fun initProfessionDropDown(professions: List<Profession>){
        val professionAdapter = ArrayAdapter(context!!,R.layout.drop_down_menu_item,professions.map { it.profession })
        professionDd.setAdapter(professionAdapter)
    }

    private fun initListeners(){
        professionDd.setOnItemClickListener { _, _, position, _ ->
            getActivityContext().loader.show()
            instructionsLy.visibility = View.GONE
            expertFinderViewModel.filterGnomes(position)
        }

        gnomeAdapter.onGnomeItemListener = {
            val action = ExpertFinderFragmentDirections.actionExpertFinderFragmentToGnomeProfileFragment(it.id)
            findNavController().navigate(action)
        }
    }
}
