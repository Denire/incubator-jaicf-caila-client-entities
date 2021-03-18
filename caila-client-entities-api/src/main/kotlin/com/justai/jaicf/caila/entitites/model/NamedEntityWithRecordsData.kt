package com.justai.jaicf.caila.entitites.model

import kotlinx.serialization.Serializable

@Serializable
data class NamedEntityWithRecordsData(
    val entity: NamedEntityData,
    val records: List<NamedEntityRecordData>
)
