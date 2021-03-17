## JAICF Incubator Projects - **Caila Client Entities**

**Client entities** are entities that can be personalized by the client during a conversation with the bot. The contents
of such an entity are accessible to the client only.

> Client entities are used when personalization is required to identify intents. For example, they can be used to process a client contact list.

Client entities can be used in a scenario, intents and slot filling.

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

```kotlin
// 1. create settings to use CAILA
val cailaSettings = CailaNLUSettings("token")

// 2. declare client entities factory, which should create client entities objects
val entitiesFactory = ClientEntityFactory(cailaSettings)

// 3. create shortcut to use Contact entity in scenario action block
val FactoryContext.Contact: CailaClientEntity
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
```kotlin
TODO()
```

### Conversation example
```kotlin
TODO()
```

