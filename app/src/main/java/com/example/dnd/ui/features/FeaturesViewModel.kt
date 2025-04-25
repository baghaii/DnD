package com.example.dnd.ui.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd.data.GenericDndClass
import com.example.dnd.networking.DndService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeaturesViewModel @Inject constructor(
    private val dndService: DndService
): ViewModel() {
    private val _state = MutableStateFlow(FeaturesUiState(dndFeatures = emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dndFeatures = dndService.getFeatures()

            _state.update {
                it.copy(dndFeatures = dndFeatures.results)
            }
        }
    }

    data class FeaturesUiState(
        val dndFeatures: List<GenericDndClass>
    )
}
