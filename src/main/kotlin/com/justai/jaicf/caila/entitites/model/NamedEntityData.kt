package com.justai.jaicf.caila.entitites.model

data class NamedEntityData (
    val name: String,
    val type: NamedEntityType,
    val id: Long,
    val enabled: Boolean,
    val shared: Boolean,
    val priority: Int,
    val noSpelling: Boolean,
    val noMorph: Boolean,
    val itemsCount: Int,
    val phoneticSearch: Boolean,
    val fuzzySearch: Boolean,
    val client: Boolean
)