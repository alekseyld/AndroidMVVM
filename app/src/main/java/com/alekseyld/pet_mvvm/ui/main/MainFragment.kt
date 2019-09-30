package com.alekseyld.pet_mvvm.ui.main

import androidx.lifecycle.LifecycleOwner
import com.alekseyld.pet_mvvm.R
import com.alekseyld.pet_mvvm.databinding.MainFragmentBinding
import com.alekseyld.pet_mvvm.ui.base.BaseFragment
import org.koin.android.scope.currentScope

class MainFragment : BaseFragment<MainViewModel, MainFragmentBinding>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getLayoutId(): Int = R.layout.main_fragment
    override fun currentScope() = currentScope
    override fun lifecycleOwner() = this

    override fun bindViewModel() {

        binding.viewModel = viewModel

        subscribeUi()

    }

    private fun subscribeUi() {

    }

}
