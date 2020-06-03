package com.saulgargar.epicrew.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulgargar.androidext.liveDataObserve
import com.saulgargar.domain.State

import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.presentation.adapter.gnomes.GnomesRecyclerAdapter
import com.saulgargar.epicrew.presentation.viewmodel.CreatureFinderViewModel
import com.saulgargar.gnomedata.domain.model.GnomeUser
import kotlinx.android.synthetic.main.fragment_creature_finder_results.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatureFinderResultsFragment : BaseFragment() {

    private val creatureFinderViewModel: CreatureFinderViewModel by viewModel()
    private val args: CreatureFinderResultsFragmentArgs by navArgs()

    private lateinit var gnomeAdapter: GnomesRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_creature_finder_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivityContext().loader.show()
        initArgs()
        creatureFinderViewModel.recoverGnomes()
        initObservers()
        initAdapter()
        initListeners()
        initRecyclerView()
    }

    private fun initArgs(){
        creatureFinderViewModel.physicalFeaturesQuery = args.physicalFeaturesQuery
    }

    private fun initObservers() {
        liveDataObserve(creatureFinderViewModel.stateRecoverGnomes, ::onRecoverGnomesStateChange)
    }

    private fun onRecoverGnomesStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    val result = noNullState.responseTo<List<GnomeUser>>()
                    showGnomeList(result)
                    showNotGnomesLy(result)
                    getActivityContext().loader.dismiss()
                }
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
            }
        }
    }

    private fun initAdapter() {
        gnomeAdapter =
            GnomesRecyclerAdapter()
    }

    private fun initRecyclerView(){
        recyclerView.run {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            this.adapter = gnomeAdapter
        }
    }

    private fun initListeners(){
        backBt.setOnClickListener {
            findNavController().navigateUp()
        }

        gnomeAdapter.onGnomeItemListener = {
            val action = CreatureFinderResultsFragmentDirections.actionCreatureFinderResultsFragmentToGnomeProfileFragment(it.id)
            findNavController().navigate(action)
        }
    }

    private fun showGnomeList(gnomes: List<GnomeUser>){
        gnomeAdapter.setGnomeList(gnomes)
    }

    private fun showNotGnomesLy(gnomes: List<GnomeUser>){
        if(gnomes.isEmpty()){
            notFoundLy.visibility = View.VISIBLE
        }
        else{
            notFoundLy.visibility = View.GONE
        }
    }
}
