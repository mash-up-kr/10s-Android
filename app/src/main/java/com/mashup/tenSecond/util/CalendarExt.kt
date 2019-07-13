package com.mashup.tenSecond.util

import java.text.SimpleDateFormat
import java.util.*

/*calendar fulldate  format yyyy-mm-dd*/
fun Calendar.getCalanderFulldate(): String {
    val year = this.get(Calendar.YEAR).toString()
    val month = (this.get(Calendar.MONTH) + 1).let { if (it < 10) "0" + it else it }.toString()
    val date = this.get(Calendar.DATE).let { if (it < 10) "0" + it else it }.toString()
    return "${year}-${month}-${date}"
}


fun Calendar.getCurrentDate(): String =
    SimpleDateFormat("yyyyMMdd_kkmmss").format(Calendar.getInstance().time)

