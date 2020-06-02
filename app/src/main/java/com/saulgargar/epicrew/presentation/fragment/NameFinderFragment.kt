package com.saulgargar.epicrew.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulgargar.androidext.liveDataObserve
import com.saulgargar.domain.State

import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.presentation.adapter.GnomesRecyclerAdapter
import com.saulgargar.epicrew.presentation.viewmodel.NameSearchViewModel
import com.saulgargar.gnomedata.domain.model.GnomeUser
import kotlinx.android.synthetic.main.fragment_expert_finder.*
import kotlinx.android.synthetic.main.fragment_expert_finder.recyclerView
import kotlinx.android.synthetic.main.fragment_name_finder.*
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NameFinderFragment : BaseFragment() {

    private val nameSearchViewModel: NameSearchViewModel by viewModel()

    private lateinit var gnomeAdapter: GnomesRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_name_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivityContext().loader.show()
        nameSearchViewModel.recoverGnomes()
        initObservers()
        initAdapter()
        initListeners()
        initRecyclerView()
    }

    private fun initObservers() {
        liveDataObserve(nameSearchViewModel.stateRecoverGnomes, ::onRecoverGnomesStateChange)
    }

    private fun onRecoverGnomesStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    val result = noNullState.responseTo<List<GnomeUser>>()
                    showGnomeList(result)
                    getActivityContext().loader.dismiss()
                }
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
            }
        }
    }

    private fun initListeners(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                gnomeAdapter.filterByQuery(query = newText.toLowerCase())

                return true
            }

            override fun onQueryTextSubmit(query: String) = false
        })
        gnomeAdapter.onGnomeItemListener = {
            val action = NameFinderFragmentDirections.actionNameFinderFragmentToGnomeProfileFragment(it.id)
            findNavController().navigate(action)
        }
    }

    private fun initAdapter() {
        gnomeAdapter = GnomesRecyclerAdapter()
    }

    private fun initRecyclerView(){
        recyclerView.run {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            this.adapter = gnomeAdapter
        }
    }

    private fun showGnomeList(gnomes: List<GnomeUser>){
        gnomeAdapter.setGnomeList(gnomes)
    }
}
