package com.wotosts.mygreens.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources

inline fun <reified T> Context.startActivity() {
    startActivity(
        Intent(
            this,
            T::class.java
        )
    )
}

fun dp(dp: Int): Int = (Resources.getSystem().displayMetrics.density * dp).toInt()

fun dp(dp: Float): Int = (Resources.getSystem().displayMetrics.density * dp).toInt()