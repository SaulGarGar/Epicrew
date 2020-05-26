package com.saulgargar.epicrew.presentation.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.saulgargar.androidext.showError
import com.saulgargar.androidext.showNeutral
import com.saulgargar.androidext.showWarning
import com.saulgargar.androidext.snackbar
import com.saulgargar.domain.Failure
import com.saulgargar.epicrew.R
import timber.log.Timber

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideToolbar()
    }

    private fun hideToolbar() {
        supportActionBar?.hide()
    }

    abstract fun showProgress()

    abstract fun hideProgress()

    fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetworkConnection -> snackbar(window.decorView.rootView, R.string.failure_network_connection).showError()
            is Failure.CustomError -> snackbar(window.decorView.rootView, R.string.failure_unavailable).showWarning()
            is Failure.GenericError -> snackbar(window.decorView.rootView, R.string.failure_unavailable).showError()
            else -> snackbar(window.decorView.rootView, R.string.failure_unavailable).showNeutral()
        }

        Timber.d("HandleFailure: ${window.decorView.rootView} == Failure: $failure")
    }
}