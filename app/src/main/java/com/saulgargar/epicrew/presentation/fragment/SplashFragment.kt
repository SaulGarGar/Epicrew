package com.saulgargar.epicrew.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.saulgargar.androidext.liveDataObserve
import com.saulgargar.domain.State

import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.presentation.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SplashFragment : BaseFragment() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        splashViewModel.getGnomes()
    }

    private fun initObservers() {
        liveDataObserve(splashViewModel.stateGetGnomes, ::onRegisterUserAccountStateChange)
    }

    private fun onRegisterUserAccountStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> goToApp()
                is State.Failed -> getActivityContext().handleFailure(failure = noNullState.failure)
                else -> Timber.d("any state in getGnomes")
            }
        }
    }

    private fun goToApp(){
        val action = SplashFragmentDirections.actionSplashFragmentToNameFinderFragment()
        findNavController().navigate(action)
    }
}
