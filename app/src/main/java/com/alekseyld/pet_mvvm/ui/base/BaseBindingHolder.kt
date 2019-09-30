package com.alekseyld.pet_mvvm.ui.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseBindingHolder<TItem, TViewBinding : ViewDataBinding>(
    val binding: TViewBinding,
    override val containerView: View = binding.root
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bindItem(item: TItem)

}