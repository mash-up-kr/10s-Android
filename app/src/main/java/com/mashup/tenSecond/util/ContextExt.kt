package com.namget.lottolee.util

import android.content.Context
import android.widget.Toast

//SharedPreference
fun Context.getStringPreference(context: Context, key: String): String {
    return context.getSharedPreferences("lotto", Context.MODE_PRIVATE)?.getString(key, "") ?: ""
}

fun Context.setStringPreference(context: Context, key: String, value: String) {
    context.getSharedPreferences("lotto", Context.MODE_PRIVATE).edit().apply() {
        putString(key, value)
        commit()
    }
}

fun Context.getIntegerPreference(context: Context, key: String): Int {
    return context.getSharedPreferences("lotto", Context.MODE_PRIVATE).getInt(key, 0)
}


fun Context.setIntegerPreference(context: Context, key: String, value: Int) {
    context.getSharedPreferences("lotto", Context.MODE_PRIVATE).edit().apply() {
        putInt(key, value)
        commit()
    }
}

fun Context.getBooleanPreference(context: Context, key: String): Boolean {
    return context.getSharedPreferences("lotto", Context.MODE_PRIVATE)?.getBoolean(key, false)
            ?: false
}

fun Context.setBooleanPreference(context: Context, key: String, value: Boolean) {
    context.getSharedPreferences("lotto", Context.MODE_PRIVATE).edit().apply() {
        putBoolean(key, value)
        commit()
    }
}


fun Context.getLongPreference(context: Context, key: String): Long {
    return context.getSharedPreferences("lotto", Context.MODE_PRIVATE)
            ?.getLong(key, 0L) ?: 0L
}

fun Context.setLongPreference(context: Context, key: String, value: Long) {
    context.getSharedPreferences("lotto", Context.MODE_PRIVATE).edit().apply() {
        putLong(key, value)
        commit()
    }
}


//Toast
fun Context.toastMakeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

/*fun Context.sendEmail() {
    val email = Intent(Intent.ACTION_SENDTO)
    email.data = Uri.parse("mailto:")
    email.putExtra(Intent.EXTRA_EMAIL, arrayOf(this.getString(R.string.contact_email)))
    email.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.setting_send_mail))
    try {
        this.startActivity(email)
    } catch (e: Exception) {
        Crashlytics.logException(e)
        this.toastMakeToast(this.getString(R.string.error_email))
    }
}

fun Context.invite() {
    val intent = AppInviteInvitation.IntentBuilder(this.getString(R.string.shared_title))
            .setMessage(this.getString(R.string.shared_message))
            .setDeepLink(Uri.parse(this.getString(R.string.dynamic_app_link)))
            .setCallToActionText(this.getString(R.string.shared_call_to_action_text))
            .build()
    try {
        (this as Activity).startActivityForResult(intent, 1)
    } catch (e: Exception) {
        LogUtil.e("start error", "error : " + e.toString())
        e.printStackTrace()
    }
}*/

/*fun Context.reviews() {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(this.getString(R.string.app_link)))
        this.startActivity(intent)
    } catch (e: Exception) {
        Crashlytics.logException(e)
    }
}*/


