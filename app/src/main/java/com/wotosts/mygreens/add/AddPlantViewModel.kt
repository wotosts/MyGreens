package com.wotosts.mygreens.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddPlantViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AddPlantUiState())
    val uiState: StateFlow<AddPlantUiState> = _uiState
}

data class AddPlantUiState(val imageUrl: String = "", val additionalInfoExpanded: Boolean = false) {
}