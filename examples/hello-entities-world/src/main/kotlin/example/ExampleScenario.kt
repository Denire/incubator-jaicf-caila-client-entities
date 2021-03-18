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

    state("getContact") {
        activators {
            regex("getContact")
        }
        action {
            reactions.say(Contact.getEntityRecords().joinToString())
        }
    }

    state("addContact") {
        activators {
            regex("addContact")
        }
        action {
            Contact.addSynonyms(listOf("denire", "v.metelyagin"), "developer")
            reactions.say("ok")
        }
    }

    state("setContact") {
        activators {
            regex("setContact")
        }
        action {
            Contact.setSynonyms(listOf("denire", "v.metelyagin"), "developer")
            reactions.say("ok")
        }
    }

    fallback {
        reactions.say("You said: ${request.input}")
    }

}