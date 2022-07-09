package com.wotosts.mygreens.ui.detail

import androidx.lifecycle.ViewModel
import com.wotosts.mygreens.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(
        PlantDetailUiState(
            plant = Plant(id = 0L, name = "test 식물", description = "test")
        )
    )
    val uiState: StateFlow<PlantDetailUiState> = _uiState
}

data class PlantDetailUiState(
    val plant: Plant,
)