package com.wotosts.mygreens.ui

import androidx.annotation.DrawableRes
import com.wotosts.mygreens.R

enum class Screens(val displayName: String, @DrawableRes val icon: Int) {
    GARDEN(displayName = "Garden", icon = R.drawable.ic_potted_plant),
    DIARY(displayName = "Diary", icon = R.drawable.ic_diary),
    SETTING(displayName = "Setting", icon = R.drawable.ic_settings)
}