package com.example.dnd.ui.classes

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
class ClassesViewModel @Inject constructor(
    private val dndService: DndService
) : ViewModel() {
    private val _state = MutableStateFlow(ClassesUiState(dndClasses = emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dndClasses = dndService.getClasses()
                _state.update {
                    it.copy(dndClasses = dndClasses.results)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isError = true,
                        errorMessage = e.message ?: "",
                        dndClasses = emptyList()
                    )
                }
            }
        }
    }
}

data class ClassesUiState(
    val isError: Boolean = false,
    val errorMessage: String = "",
    val dndClasses: List<GenericDndClass>
)

