package com.alekseyld.pet_mvvm.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

abstract class BaseViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorText: MutableLiveData<String> = MutableLiveData()

    val toastMessage: MutableLiveData<String> = MutableLiveData()

    val navigationCommands: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()

    fun navigate(directions: NavDirections) {
        navigationCommands.postValue(NavigationCommand.To(directions))
    }
}