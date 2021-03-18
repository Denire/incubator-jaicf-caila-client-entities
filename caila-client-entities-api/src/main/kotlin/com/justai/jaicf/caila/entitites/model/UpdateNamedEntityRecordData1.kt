package com.justai.jaicf.caila.entitites.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateNamedEntityRecordData(
    val data: List<NamedEntityRecordData>,
    val clientId: String
)
