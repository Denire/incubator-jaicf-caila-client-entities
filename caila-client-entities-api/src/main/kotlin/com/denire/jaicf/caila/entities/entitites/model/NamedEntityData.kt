package com.denire.jaicf.caila.entities.entitites.model

import kotlinx.serialization.Serializable

@Serializable
data class NamedEntityData(
    val name: String,
    val type: NamedEntityType,
    val id: Long? = null,
    val enabled: Boolean? = null,
    val shared: Boolean? = null,
    val priority: Int? = null,
    val noSpelling: Boolean? = null,
    val noMorph: Boolean? = null,
    val itemsCount: Int? = null,
    val phoneticSearch: Boolean? = null,
    val fuzzySearch: Boolean? = null,
    val client: Boolean? = null
)