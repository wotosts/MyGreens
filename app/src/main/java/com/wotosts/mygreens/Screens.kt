package com.wotosts.mygreens

import androidx.annotation.DrawableRes

enum class Screens(val displayName: String, @DrawableRes val icon: Int) {
    HOME(displayName = "Home", icon = R.drawable.ic_home),
    ADD(displayName = "New Plant", icon = R.drawable.ic_add),
    GARDEN(displayName = "Garden", icon = R.drawable.ic_potted_plant)
}