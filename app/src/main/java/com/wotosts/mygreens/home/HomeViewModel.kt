package com.wotosts.mygreens.home

import androidx.lifecycle.ViewModel
import com.wotosts.mygreens.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val plants = (1..10).map { Plant(id = it.toLong(), name = "식물 $it") }
    val plantNeedWaters = plants.take(5)
}