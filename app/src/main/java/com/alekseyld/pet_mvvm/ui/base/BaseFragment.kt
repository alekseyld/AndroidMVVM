package com.alekseyld.pet_mvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<TViewModel : BaseViewModel, TViewBinding : ViewDataBinding> : Fragment() {

    protected val viewModel: TViewModel
            by getCurrentScope().viewModel(getLivecycleOwner(), getViewModelType())

    protected lateinit var binding: TViewBinding

    protected open val fragmentHolder: FragmentHolder?
        get() {
            return if (activity is FragmentHolder) activity as FragmentHolder
            else null
        }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelType() : KClass<TViewModel> {

        val typeName = ((this::class.java.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<*>).name

        return Class.forName(typeName).kotlin as KClass<TViewModel>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindBaseViewModel()

        bindViewModel()
    }

    private fun bindBaseViewModel() = (viewModel as BaseViewModel).apply {

        isLoading.observe(viewLifecycleOwner, Observer { loading ->
            fragmentHolder?.apply {

                if (loading) {
                    showProgressBar()
                } else {
                    hideProgressBar()
                }
            }
        })

        errorText.observe(viewLifecycleOwner, Observer {
            fragmentHolder?.setErrorText(it)
        })

        toastMessage.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })

        navigationCommands.observe(viewLifecycleOwner, Observer { command ->

            when (command) {
                is NavigationCommand.To ->
                    findNavController().navigate(command.directions)
            }
        })
    }

    private fun getCurrentScope() : Scope = currentScope()
    private fun getLivecycleOwner() : LifecycleOwner = lifecycleOwner()

    protected fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    protected abstract fun bindViewModel()

    protected abstract fun currentScope() : Scope

    protected abstract fun lifecycleOwner() : LifecycleOwner

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    fun onBackKeyPressed() {}

}