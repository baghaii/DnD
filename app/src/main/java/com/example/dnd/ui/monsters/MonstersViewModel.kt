package com.example.dnd.ui.monsters

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
class MonstersViewModel @Inject constructor(
    private val dndService: DndService
): ViewModel() {
    private val _state = MutableStateFlow(MonstersUiState(dndMonsters = emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dndMonsters = dndService.getMonsters()
            _state.update {
                it.copy(dndMonsters = dndMonsters.results)
            }
        }
    }

    data class MonstersUiState(
        val dndMonsters: List<GenericDndClass>
    )
}
