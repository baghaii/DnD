package com.example.dnd.ui.features

import com.example.dnd.networking.DndService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class FeaturesViewModelTest {

    @Test
    fun `GIVEN FeaturesViewModel, WHEN invoke, THEN dndService is called`() {
        runBlocking {
            val mockDndService: DndService = mock()
            FeaturesViewModel(mockDndService)
            verify(mockDndService).getFeatures()
        }
    }
}
