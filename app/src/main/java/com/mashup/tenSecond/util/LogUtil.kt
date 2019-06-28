package com.mashup.tenSecond.util

import android.util.Log
import com.mashup.tenSecond.BuildConfig


public class LogUtil {

    companion object {
        public fun e(TAG: String, message: String) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, message)
            }
        }

        public fun d(TAG: String, message: String) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, message)
            }
        }

    }

}