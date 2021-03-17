package example

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.caila.entitites.CailaClientEntity
import com.justai.jaicf.caila.entitites.ClientEntityFactory
import com.justai.jaicf.caila.entitites.FactoryContext
import com.justai.jaicf.channel.ConsoleChannel

// create settings to use CAILA
val cailaSettings = CailaNLUSettings("token")

// declare client entities factory, which should create client entities objects
val entitiesFactory = ClientEntityFactory(cailaSettings)

// create shortcut to use Contact entity in scenario action block
val FactoryContext.Contact: CailaClientEntity
    get() = entitiesFactory.getEntity("Contact", request.clientId)

val ClientEntitiesBot = BotEngine(
    ExampleScenario,
    activators = arrayOf(
        CailaIntentActivator.Factory(cailaSettings),
        RegexActivator
    )
)

fun main() {
    ConsoleChannel(ClientEntitiesBot).run()
}