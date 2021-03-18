package com.denire.jaicf.caila.entities.entitites.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateNamedEntityRecordData(
    val data: NamedEntityRecordData,
    val clientId: String
)
