package com.namget.diaryLee.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class LifecycleRecyclerAdapter<L : LifecycleViewHolder> : RecyclerView.Adapter<L>(){

    override fun onViewAttachedToWindow(holder: L) {
        super.onViewAttachedToWindow(holder)
        holder.onAppear()
    }

    override fun onViewDetachedFromWindow(holder: L) {
        super.onViewDetachedFromWindow(holder)
        holder.onDisappear()
    }
}