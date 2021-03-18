## JAICF Incubator Projects - **Caila Client Entities**

**Client entities** are entities that can be personalized by the client during a conversation with the bot. The contents
of such an entity are accessible to the client only.

> Client entities are used when personalization is required to identify intents. For example, they can be used to process a client contact list.

Client entities can be used in a scenario, intents and slot filling. The main documentation (for JAICP) is
located [here](https://help.just-ai.com/#/docs/en/NLU_core/client_entities/client_entities.md).

## Quick start

Here is an example of a
[slot filling](https://help.just-ai.com/#/docs/en/NLU_core/slot_filling) script where client entities are used. The bot
will add client contacts to an address book and schedule meetings. Each of the bot clients will have their own
personalized address book.

### Filling an entity

[Create a `@Contact` entity](https://help.just-ai.com/#/docs/en/platform_ux/nlu_core_caila/entities). Click `client`
under the entity name to make its value unique for each client. The entity will be filled in the course of the
conversation.

### Completion of intents

Then proceed to
[creating intents](https://help.just-ai.com/#/docs/en/platform_ux/nlu_core_caila/intents). Create and fill
the `AddContact` intent that adds a contact to an address book as follows:

![Creating intent](https://help.just-ai.com/docs/en/NLU_core/client_entities/client_entities_image/Screenshot_1.png)

The `AddContact` intent is triggered when the client wants to add a new entry to the address book. We use
[slot filling](https://help.just-ai.com/#/docs/en/NLU_core/slot_filling) here to fill the phone number and contact name
slots.

The `@duckling.phone-number` and `@pymorphy.name`system entities will be used to fill the slots (ensure the entities are
active).

Create and fill the `Meeting` intent that adds a contact to an address book as follows:

![Creating intent](https://help.just-ai.com/docs/en/NLU_core/client_entities/client_entities_image/Screenshot_2.png)

The `Meeting` intent is triggered when the client wants to schedule a meeting with a newly created contact. Please note
that in our training phrases, we add the reference to the
[`@Contact` client entity](#Filling-an-entity) we created before.

## JAICF Scenario

### Configuration

As this is incubator project and it's not hosted on MavenCentral or Jcenter, we provide artifact hosting via Github
Packages.

How to add package to your scenario:

1. [Create GitHub Access Token](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token)
   with permissions `write:packages` and `read:packages`
2. Connect your gradle buildscript with remote GitHub packages server

```kotlin
repositories {
    mavenCentral()
    jcenter()
    // ... your other repositories

    // and repository for caila-client-entities-api module
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/denire/incubator-jaicf-caila-client-entities")
        credentials {
            username = project.findProperty("gpr.user") as String
            password = project.findProperty("gpr.key") as String
        }
    }
}
```
3. Add your `gpr.user` and `gpr.key` to `gradle.properties` file
```
gpr.user=vmetelyagin
gpr.key=<TOKEN>
```
4. Add dependency for this module
```kotlin
dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.justai.jaicf:core:0.13.0")
    implementation("com.justai.jaicf:caila:0.13.0")
    // ... and your other dependencies
    
    implementation("com.denire.jaicf:caila-client-entities-api:0.13.0")
}

```

### BotEngine

```kotlin
// 1. create settings to use CAILA
val cailaSettings = CailaNLUSettings("token")

// 2. declare client entities factory, which should create client entities objects
val entitiesFactory = ClientEntityFactory(cailaSettings)

// 3. create shortcut to use Contact entity in scenario action block
val EntityContext.Contact: CailaClientEntity
    get() = entitiesFactory.getEntity("Contact", request.clientId)

val ClientEntitiesBot = BotEngine(
    ExampleScenario,
    activators = arrayOf(
        CailaIntentActivator.Factory(cailaSettings),
        RegexActivator
    )
)
```

### Scenario

Right after we created extension property with

```kotlin
val EntityContext.Contact: CailaClientEntity
    get() = entitiesFactory.getEntity("Contact", request.clientId)
```

we have a client entity object inside scenario, so we can use it to setRecords, getRecords, etc.

```kotlin
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
```

### Conversation example

```kotlin
TODO()
```


## Licensing

JAICF Incubator modules are distributed under [Apache 2.0](LICENSE) license meaning you are free to use and modify it without the need to open your project source code.
