package com.namget.diaryLee.ui.base

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

abstract class LifecycleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) , LifecycleOwner{
    val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
    }

    fun onAppear(){
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }
    fun onDisappear(){
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}