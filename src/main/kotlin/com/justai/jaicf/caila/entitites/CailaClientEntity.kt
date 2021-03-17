package com.justai.jaicf.caila.entitites

import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.caila.entitites.http.CailaEntitiesKtorApi
import com.justai.jaicf.caila.entitites.http.ClientEntitiesApi
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

    fun addSynonyms(synonyms: List<String>, value: String) =
        api.addEntityRecords(clientId, name, NamedEntityRecordType.SYNONYMS, synonyms, value)

    fun addPattern(pattern: String, value: String) =
        api.addEntityRecords(clientId, name, NamedEntityRecordType.SYNONYMS, listOf(pattern), value)

    fun deleteRecords(recordIds: List<String>) =
        api.deleteRecords(clientId, name, recordIds)

    fun getEntityRecords() =
        api.getEntityRecords(clientId, name)

    fun setEntitySynonyms(synonyms: List<String>, value: String) =
        api.setEntityRecords(clientId, name, NamedEntityRecordType.SYNONYMS, synonyms, value)

    fun setEntityPatterns(synonyms: List<String>, value: String) =
        api.setEntityRecords(clientId, name, NamedEntityRecordType.PATTERN, synonyms, value)

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

