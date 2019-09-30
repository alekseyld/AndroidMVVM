package com.alekseyld.pet_mvvm.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    items: List<T> = listOf()
) : RecyclerView.Adapter<BaseBindingHolder<T, *>>() {

    open var items = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun getLayoutId(viewType: Int): Int

    abstract fun wrapViewHolder(binding: ViewDataBinding, viewType: Int): BaseBindingHolder<T, *>

    protected fun <T : ViewDataBinding> getViewBinding(
        parent: ViewGroup,
        @LayoutRes layoutId: Int

    ) =
        DataBindingUtil.inflate<T>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )


    abstract fun getConcreteBinding(
        parent: ViewGroup,
        viewType: Int,
        @LayoutRes layoutId: Int
    ): ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingHolder<T, *> {
        val binding = getConcreteBinding(parent, viewType, getLayoutId(viewType))

        return wrapViewHolder(binding, viewType)
    }

    override fun getItemCount() = items.count()
}