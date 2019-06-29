package com.mashup.tenSecond.ui.base

interface RecyclerAdapterItem<T> {
    fun addItem(item : T)
    fun setItem(item : T)
    fun clear()
}