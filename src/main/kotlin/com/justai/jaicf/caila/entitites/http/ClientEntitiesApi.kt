package com.justai.jaicf.caila.entitites.http

import com.justai.jaicf.caila.entitites.model.NamedEntityRecordData
import com.justai.jaicf.caila.entitites.model.NamedEntityRecordType

interface ClientEntitiesApi {
    val accessToken: String
    val cailaUrl: String

    fun addEntityRecords(
        clientId: String,
        name: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    )

    fun deleteRecords(
        clientId: String,
        name: String,
        recordIds: List<String>
    )

    fun getEntityRecords(
        clientId: String,
        name: String
    ): List<NamedEntityRecordData>

    fun setEntityRecords(
        clientId: String,
        name: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    )
}

class CailaEntitiesKtorApi(
    override val accessToken: String,
    override val cailaUrl: String
) : ClientEntitiesApi {

    override fun addEntityRecords(
        clientId: String,
        name: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteRecords(clientId: String, name: String, recordIds: List<String>) {
        TODO("Not yet implemented")
    }

    override fun getEntityRecords(clientId: String, name: String): List<NamedEntityRecordData> {
        TODO("Not yet implemented")
    }

    override fun setEntityRecords(
        clientId: String,
        name: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    ) {
        TODO("Not yet implemented")
    }
}