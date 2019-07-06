package com.mashup.tenSecond.util

import android.content.Context
import android.widget.Toast

//SharedPreference
fun Context.getStringPreference(key: String): String {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getString(key, "") ?: ""
}

fun Context.setStringPreference( key: String, value: String) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit().apply() {
        putString(key, value)
        commit()
    }
}

fun Context.getIntegerPreference(key: String): Int {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).getInt(key, 0)
}


fun Context.setIntegerPreference(key: String, value: Int) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit().apply() {
        putInt(key, value)
        commit()
    }
}

fun Context.getBooleanPreference(key: String): Boolean {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getBoolean(key, false)
            ?: false
}

fun Context.setBooleanPreference(key: String, value: Boolean) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit().apply() {
        putBoolean(key, value)
        commit()
    }
}


fun Context.getLongPreference(key: String): Long {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
            ?.getLong(key, 0L) ?: 0L
}

fun Context.setLongPreference(key: String, value: Long) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit().apply() {
        putLong(key, value)
        commit()
    }
}

//Toast
fun Context.toastMakeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}



