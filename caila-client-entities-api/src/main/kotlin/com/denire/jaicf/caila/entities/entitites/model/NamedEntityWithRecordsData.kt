package com.denire.jaicf.caila.entities.entitites.model

import kotlinx.serialization.Serializable

@Serializable
data class NamedEntityWithRecordsData(
    val entity: NamedEntityData,
    val records: List<NamedEntityRecordData>
)
