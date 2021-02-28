package dev.roffel.dev_challenge_2021_1

val petNames = listOf(
    "Rex",
    "Sem",
    "Sam",
    "Tina",
    "Root",
    "Barko",
    "Fluffiboi",
    "Lina",
    "Fly",
    "Doggo",
    "Jean",
    "Trixy",
    "Mario",
    "Connor",
    "Henk",
    "Bear",
    "Scrubs"
)

data class Pet(
    val id: Int = 0,
    val name: String = petNames.shuffled().first(),
    val breed: Breed = Breed(),
    val sex: Sex = listOf(Sex.MALE, Sex.FEMALE).shuffled().first(),
    val age: Int = (1..15).random()
)

enum class Sex {
    MALE,
    FEMALE
}

class Breed(val name: String = "Unknown")
