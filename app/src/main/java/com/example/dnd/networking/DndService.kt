package com.example.dnd.networking

import com.example.dnd.data.ClassesWrapper
import com.example.dnd.data.FeaturesWrapper
import com.example.dnd.data.MonstersWrapper
import com.example.dnd.data.SpellsWrapper
import retrofit2.http.GET

interface DndService {
    @GET("classes")
    suspend fun getClasses(): ClassesWrapper

    @GET("features")
    suspend fun getFeatures(): FeaturesWrapper

    @GET("monsters")
    suspend fun getMonsters(): MonstersWrapper

    @GET("spells")
    suspend fun getSpells(): SpellsWrapper
}
