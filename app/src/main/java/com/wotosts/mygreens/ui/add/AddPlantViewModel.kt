package com.wotosts.mygreens.ui.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddPlantViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AddPlantUiState())
    val uiState: StateFlow<AddPlantUiState> = _uiState

    fun expandAdditionalInfo() {
        _uiState.value = _uiState.value.copy(additionalInfoExpanded = true)
    }

    fun closeAdditionalInfo() {
        _uiState.value = _uiState.value.copy(additionalInfoExpanded = false)
    }

    fun updateName(name: String) {
        _uiState.value = uiState.value.copy(name = name)
    }

    fun updateDescription(des: String) {
        _uiState.value = uiState.value.copy(description = des)
    }

    fun updateWaterLevel(level: Int) {
        _uiState.value = uiState.value.copy(waterLevel = level)
    }

    fun updateSolarLevel(level: Int) {
        _uiState.value = uiState.value.copy(solarLevel = level)
    }

    fun updateTempRange(range: List<Float>) {
        _uiState.value = uiState.value.copy(tempRange = range)
    }
}

data class AddPlantUiState(
    val imageUrl: String = "",
    val additionalInfoExpanded: Boolean = false,
    val name: String = "",
    val date: Date = Date(),
    val waterLevel: Int = 3,
    val solarLevel: Int = 3,
    val tempRange: List<Float> = listOf(15f, 26f),
    val description: String = "",
)