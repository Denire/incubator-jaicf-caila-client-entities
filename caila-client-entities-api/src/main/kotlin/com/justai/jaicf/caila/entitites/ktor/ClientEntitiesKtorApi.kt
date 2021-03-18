package com.justai.jaicf.caila.entitites.ktor

import com.justai.jaicf.caila.entitites.ClientEntitiesApi
import com.justai.jaicf.caila.entitites.model.CreateNamedEntityRecordData
import com.justai.jaicf.caila.entitites.model.NamedEntityRecordData
import com.justai.jaicf.caila.entitites.model.NamedEntityRecordType
import com.justai.jaicf.caila.entitites.model.NamedEntityWithRecordsData
import com.justai.jaicf.caila.entitites.model.UpdateNamedEntityRecordData
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

@Suppress("EXPERIMENTAL_API_USAGE")
object KtorClient {
    val DEFAULT = HttpClient(CIO) {
        expectSuccess = true
        followRedirects = true
        engine {
            endpoint {
                connectTimeout = 1000
                requestTimeout = 5000
            }
        }
        install(Logging) {
            this.logger = Logger.DEFAULT
            this.level = LogLevel.BODY
        }
        install(JsonFeature) {
            serializer =
                KotlinxSerializer(kotlinx.serialization.json.Json { ignoreUnknownKeys = true; encodeDefaults = true })
        }
    }
}


class CailaEntitiesKtorApi(
    override val accessToken: String,
    override val cailaUrl: String,
    private val client: HttpClient = KtorClient.DEFAULT
) : ClientEntitiesApi, CoroutineScope {

    private val baseByName = "$cailaUrl/$accessToken/entities-by-name"
    private val baseByNames = "$cailaUrl/$accessToken/entities-by-names"

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    companion object {
        private val contentType = ContentType.parse("application/json")
    }

    override fun getEntityWithRecords(clientId: String, entityName: String): NamedEntityWithRecordsData = runBlocking {
        client.get("$baseByName/$entityName") {
            parameter("clientId", clientId)
        }
    }

    override fun addEntityRecord(
        clientId: String,
        entityName: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    ) = runBlocking {
        client.post<NamedEntityRecordData>("$baseByNames/$entityName/records") {
            contentType(contentType)
            body = CreateNamedEntityRecordData(
                NamedEntityRecordData(type, rules, value, clientId = clientId),
                clientId
            )
        }
    }

    override fun deleteRecords(clientId: String, entityName: String, recordIds: List<String>) = runBlocking {
        client.post<Unit>("$baseByNames/$entityName/records/delete-multiple") {
            contentType(contentType)
            parameter("clientId", clientId)
            body = recordIds
        }
    }

    override fun setEntityRecords(
        clientId: String,
        entityName: String,
        type: NamedEntityRecordType,
        rules: List<String>,
        value: String
    ) = runBlocking {
        client.put<List<NamedEntityRecordData>>("$baseByName/$entityName/records") {
            contentType(contentType)
            body = UpdateNamedEntityRecordData(
                listOf(NamedEntityRecordData(type, rules, value, clientId = clientId)),
                clientId
            )
        }
    }
}