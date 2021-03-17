package example

import com.justai.jaicf.builder.Scenario

val ExampleScenario = Scenario {

    state("start") {
        activators {
            regex("start")
        }
        action {
            reactions.say("Hello there!")
        }
    }

    fallback {

    }

}