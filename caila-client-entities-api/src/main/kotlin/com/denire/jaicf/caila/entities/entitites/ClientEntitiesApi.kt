package com.denire.jaicf.caila.entities.entitites

import com.denire.jaicf.caila.entities.entitites.model.NamedEntityRecordData
import com.denire.jaicf.caila.entities.entitites.model.NamedEntityRecordType
import com.denire.jaicf.caila.entities.entitites.model.NamedEntityWithRecordsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

interface ClientEntitiesApi : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    val accessToken: String
    val cailaUrl: String

    fun getEntityWithRecords(
        clientId: String,
        entityName: String
    ): NamedEntityWithRecordsData

    fun getEntityRecords(
        clientId: String,
        entityName: String
    ): List<NamedEntityRecordData> = getEntityWithRecords(clientId, entityName).records

    fun addEntityRecords(
        clientId: String,
        entityName: String,
        records: List<NamedEntityRecordData>
    ) = runBlocking {
        records.map { async { addEntityRecord(clientId, entityName, it.type, it.rule, it.value) } }.joinAll()
    }

    fun addEntityRecord(
        clientId: String,
        entityName: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    ): NamedEntityRecordData

    fun deleteRecords(
        clientId: String,
        entityName: String,
        recordIds: List<String>
    )

    fun setEntityRecords(
        clientId: String,
        entityName: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    ): List<NamedEntityRecordData>
}