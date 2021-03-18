package com.justai.jaicf.caila.entitites.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateNamedEntityRecordData(
    val data: NamedEntityRecordData,
    val clientId: String
)
