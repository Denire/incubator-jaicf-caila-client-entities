package com.justai.jaicf.caila.entitites.model

import kotlinx.serialization.Serializable

@Serializable
data class NamedEntityRecordData (
    val type: NamedEntityRecordType,
    val rule: List<String>,
    val value: String,
    val id: Long? = null,
    val clientId: String? = null
)