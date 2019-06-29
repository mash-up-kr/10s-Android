package com.mashup.tenSecond.util

import android.content.Context
import android.widget.Toast

//SharedPreference
fun Context.getStringPreference(context: Context, key: String): String {
    return context.getSharedPreferences("10s", Context.MODE_PRIVATE)?.getString(key, "") ?: ""
}

fun Context.setStringPreference(context: Context, key: String, value: String) {
    context.getSharedPreferences("10s", Context.MODE_PRIVATE).edit().apply() {
        putString(key, value)
        commit()
    }
}

fun Context.getIntegerPreference(context: Context, key: String): Int {
    return context.getSharedPreferences("10s", Context.MODE_PRIVATE).getInt(key, 0)
}


fun Context.setIntegerPreference(context: Context, key: String, value: Int) {
    context.getSharedPreferences("10s", Context.MODE_PRIVATE).edit().apply() {
        putInt(key, value)
        commit()
    }
}

fun Context.getBooleanPreference(context: Context, key: String): Boolean {
    return context.getSharedPreferences("10s", Context.MODE_PRIVATE)?.getBoolean(key, false)
            ?: false
}

fun Context.setBooleanPreference(context: Context, key: String, value: Boolean) {
    context.getSharedPreferences("10s", Context.MODE_PRIVATE).edit().apply() {
        putBoolean(key, value)
        commit()
    }
}


fun Context.getLongPreference(context: Context, key: String): Long {
    return context.getSharedPreferences("10s", Context.MODE_PRIVATE)
            ?.getLong(key, 0L) ?: 0L
}

fun Context.setLongPreference(context: Context, key: String, value: Long) {
    context.getSharedPreferences("10s", Context.MODE_PRIVATE).edit().apply() {
        putLong(key, value)
        commit()
    }
}


//Toast
fun Context.toastMakeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}



