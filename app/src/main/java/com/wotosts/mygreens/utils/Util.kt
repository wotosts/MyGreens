package com.wotosts.mygreens.utils

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.startActivity() {
    startActivity(
        Intent(
            this,
            T::class.java
        )
    )
}