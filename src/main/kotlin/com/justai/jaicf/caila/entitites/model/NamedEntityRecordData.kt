package com.justai.jaicf.caila.entitites.model

data class NamedEntityRecordData (
    val type: NamedEntityRecordType,
    val rule: List<String>,
    val value: String,
    val id: Long? = null,
    val clientId: String? = null
)