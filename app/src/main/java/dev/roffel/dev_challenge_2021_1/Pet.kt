/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
