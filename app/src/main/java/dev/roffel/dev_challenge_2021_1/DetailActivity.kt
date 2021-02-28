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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.roffel.dev_challenge_2021_1.ui.theme.Dev_challenge_1Theme
import dev.roffel.dev_challenge_2021_1.utils.NetworkImage

class DetailActivity : AppCompatActivity() {
    private lateinit var selectedPet: Pet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedPet =
            MainActivity.pets[
                intent.extras?.get(MainActivity.SELECTED_BUNDLE_PARAMETER) as Int?
                    ?: 0
            ]
        setContent {
            Dev_challenge_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        TopAppBar(
                            title = { Text(getString(R.string.details_header_pet).format(selectedPet.name)) },
                            navigationIcon = {
                                Icon(
                                    painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                                    "@null"
                                )
                            }
                        )
                        NetworkImage(
                            url = "https://placedog.net/200?id=${selectedPet.id + 2}",
                            contentDescription = "Good boi, ${selectedPet.name}",
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                        )
                        Header(selectedPet.name)
                        Text(
                            text = selectedPet.name + " is a good boy. The dog likes to cuddle and is used to screaming children. ${selectedPet.name} is a happy ${selectedPet.sex} and is ${selectedPet.age} years of age. ",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            modifier = Modifier.padding(16.dp)
                        )
                        Spacer(modifier = Modifier.height(132.dp))
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(16.dp),
                            elevation = ButtonDefaults.elevation(5.dp),
                        ) {
                            Text(
                                text = getString(R.string.adopt_pet).format(selectedPet.name),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(16.dp),
                        ) {
                            Text(
                                text = getString(R.string.call_for_more).format(selectedPet.name),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Header(name: String) {
        Text(
            text = getString(R.string.adopt_pet).format(name),
            style = MaterialTheme.typography.h6,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }
}
