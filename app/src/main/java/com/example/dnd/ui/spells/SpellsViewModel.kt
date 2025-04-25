package com.example.dnd.ui.spells

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dnd.data.GenericDndClass
import com.example.dnd.data.Spells
import com.example.dnd.networking.DndService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellsViewModel @Inject constructor(
    private val dndService: DndService
): ViewModel() {
    private val _state = MutableStateFlow(SpellsUiState(dndSpells = emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dndSpells = dndService.getSpells()
            _state.update {
                it.copy(dndSpells = dndSpells.results)
            }
        }
    }

    data class SpellsUiState(
        val dndSpells: List<Spells>
    )
}
