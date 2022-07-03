package com.wotosts.mygreens

import androidx.annotation.DrawableRes

enum class Screens(val displayName: String, @DrawableRes val icon: Int) {
    GARDEN(displayName = "Garden", icon = R.drawable.ic_potted_plant),
    DIARY(displayName = "Diary", icon = R.drawable.ic_diary),
    SETTING(displayName = "Setting", icon = R.drawable.ic_settings)
}