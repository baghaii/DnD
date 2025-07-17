package com.example.dnd.networking

import com.example.dnd.data.ClassesWrapper
import com.example.dnd.data.FeaturesWrapper
import com.example.dnd.data.MonstersWrapper
import com.example.dnd.data.SpellsWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DndRepository(
    private val dndService: DndService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getMonsters() =
        withContext(ioDispatcher) {
            try {
                dndService.getMonsters()
            } catch (exception: Exception) {
                MonstersWrapper(
                    count = 0,
                    results = emptyList()
                )
            }
        }

    suspend fun getFeatures() =
        withContext(ioDispatcher) {
            try {
                dndService.getFeatures()
            } catch (exception: Exception) {
                FeaturesWrapper(
                    count = 0,
                    results = emptyList()
                )
            }
        }

    suspend fun getClasses() =
        withContext(ioDispatcher) {
            try {
                dndService.getClasses()
            } catch (exception: Exception) {
                ClassesWrapper(
                    count = 0,
                    results = emptyList()
                )
            }
        }

    suspend fun getSpells() =
        withContext(ioDispatcher) {
            try {
                dndService.getSpells()
            } catch (exception: Exception) {
                SpellsWrapper(
                    count = 0,
                    results = emptyList()
                )
            }
        }
}
