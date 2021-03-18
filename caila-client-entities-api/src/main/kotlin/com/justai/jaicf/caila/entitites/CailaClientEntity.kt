package com.justai.jaicf.caila.entitites

import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.caila.entitites.ktor.CailaEntitiesKtorApi
import com.justai.jaicf.caila.entitites.model.NamedEntityRecordData
import com.justai.jaicf.caila.entitites.model.NamedEntityRecordType

interface ICailaClientEntity {
    val name: String

    fun checkIfExists(): Boolean
}

@Suppress("unused")
open class CailaClientEntity(
    override val name: String,
    private val api: ClientEntitiesApi,
    private val clientId: String
) : ICailaClientEntity {

    /**
     * Appends new synonym records to client entity
     * */
    fun addSynonyms(synonyms: List<String>, value: String) =
        api.addEntityRecord(clientId, name, NamedEntityRecordType.synonyms, synonyms, value)

    /**
     * Appends new pattern records to client entity
     * */
    fun addPattern(pattern: String, value: String) =
        api.addEntityRecord(clientId, name, NamedEntityRecordType.pattern, listOf(pattern), value)

    /**
     * Delete client records with specified ids
     * */
    fun deleteRecords(recordIds: List<String>) =
        api.deleteRecords(clientId, name, recordIds)

    /**
     * Get all entity records
     * */
    fun getEntityRecords(): List<NamedEntityRecordData> =
        api.getEntityRecords(clientId, name)

    /**
     * Replaces all entity records with following single synonyms record
     * */
    fun setSynonyms(synonyms: List<String>, value: String) =
        api.setEntityRecords(clientId, name, NamedEntityRecordType.synonyms, synonyms, value)

    /**
     * Replaces all entity records with following single pattern record
     * */
    fun setPattern(synonyms: List<String>, value: String) =
        api.setEntityRecords(clientId, name, NamedEntityRecordType.pattern, synonyms, value)

    /**
     * Adds new entity record to client entity
     * */
    fun addRecords(records: List<NamedEntityRecordData>) =
        api.addEntityRecords(clientId, name, records)

    /**
     * Replaces all entity records with new provided records
     * */
    fun setRecords(records: List<NamedEntityRecordData>) =
        api.addEntityRecords(clientId, name, records)

    override fun checkIfExists(): Boolean = true
}

class ClientEntityFactory(
    private val api: ClientEntitiesApi
) {

    constructor(settings: CailaNLUSettings) : this(CailaEntitiesKtorApi(settings.accessToken, settings.cailaUrl))

    fun getEntity(name: String, clientId: String) = CailaClientEntity(name, api, clientId).apply {
        checkIfExists()
    }
}

