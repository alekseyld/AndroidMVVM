package com.alekseyld.pet_mvvm.di

import com.alekseyld.pet_mvvm.ui.main.MainFragment
import com.alekseyld.pet_mvvm.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {




    scope(named<MainFragment>()) {
        viewModel { MainViewModel() }
    }
}