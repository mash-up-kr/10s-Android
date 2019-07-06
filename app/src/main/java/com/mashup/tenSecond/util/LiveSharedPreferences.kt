package com.mashup.tenSecond.util

import android.content.SharedPreferences
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

class LiveSharedPreferences constructor(private val preferences: SharedPreferences) {

    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener
    private val updates: Observable<String>

    init {
        updates = Observable.create(ObservableOnSubscribe<String> { emitter ->
            listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key -> emitter.onNext(key) }

            emitter.setCancellable { preferences.unregisterOnSharedPreferenceChangeListener(listener) }

            preferences.registerOnSharedPreferenceChangeListener(listener)
        }).share()
    }

    public fun getString(key: String, defaultValue: String): LivePreference<String> {
        checkNotNull(key)
        checkNotNull(defaultValue)
        return LivePreference(updates, preferences, key, defaultValue)
    }

    public fun getInt(key: String, defaultValue: Int): LivePreference<Int> {
        checkNotNull(key)
        checkNotNull(defaultValue)
        return LivePreference(updates, preferences, key, defaultValue)
    }

    public fun getBoolean(key: String, defaultValue: Boolean): LivePreference<Boolean> {
        checkNotNull(key)
        checkNotNull(defaultValue)
        return LivePreference(updates, preferences, key, defaultValue)
    }

    public fun getFloat(key: String, defaultValue: Float): LivePreference<Float> {
        checkNotNull(key)
        checkNotNull(defaultValue)
        return LivePreference(updates, preferences, key, defaultValue)
    }

    public fun getLong(key: String, defaultValue: Long): LivePreference<Long> {
        checkNotNull(key)
        checkNotNull(defaultValue)
        return LivePreference(updates, preferences, key, defaultValue)
    }

    public fun getStringSet(key: String, defaultValue: Set<String>): LivePreference<Set<String>> {
        checkNotNull(key)
        checkNotNull(defaultValue)
        return LivePreference(updates, preferences, key, defaultValue)
    }
}