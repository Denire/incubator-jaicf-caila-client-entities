rootProject.name = "caila-client-entities"

include("examples:hello-entities-world")
findProject(":examples:hello-entities-world")?.name = "hello-entities-world"

include("caila-client-entities-api")

