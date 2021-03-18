package example

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.caila.entitites.CailaClientEntity
import com.justai.jaicf.caila.entitites.ClientEntityFactory
import com.justai.jaicf.caila.entitites.EntityContext
import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel

// paste your own accessToken
private const val accessToken = "b83de210-b419-4ce7-93a9-b472f5b73e37"

// create settings to use CAILA
val cailaSettings = CailaNLUSettings(accessToken)

// declare client entities factory, which should create client entities objects
val entitiesFactory = ClientEntityFactory(cailaSettings)

// create shortcut to use Contact entity in scenario action block
val EntityContext.Contact: CailaClientEntity
    get() = entitiesFactory.getEntity("Contact", request.clientId)

val ClientEntitiesBot = BotEngine(
    ExampleScenario,
    activators = arrayOf(
        CailaIntentActivator.Factory(cailaSettings),
        RegexActivator
    )
)

fun main() {
    JaicpPollingConnector(ClientEntitiesBot, accessToken, channels = listOf(ChatWidgetChannel)).runBlocking()
}