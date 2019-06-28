package com.mashup.tenSecond.util

import android.os.Build
import android.text.Html
import java.util.*

fun String.htmlToString() : String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        return Html.fromHtml(this).toString()
    }
}

// 날짜 + 요일
fun String.convertFulldateToWeekName(): String {
    val list = this.split("-").map { it.toInt() }
    val calendar = Calendar.getInstance()
    calendar.set(list[0], list[1], list[2])
    return ""
}
