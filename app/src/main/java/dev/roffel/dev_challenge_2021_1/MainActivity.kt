package dev.roffel.dev_challenge_2021_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import dev.roffel.dev_challenge_2021_1.ui.theme.Dev_challenge_1Theme
import dev.roffel.dev_challenge_2021_1.utils.NetworkImage
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    init {
        pets = ArrayList<Pet>().apply {
            repeat(NUMBER_OF_PETS) {
                this.add(Pet(id = it))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dev_challenge_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        TopAppBar(
                            title = { Text(getString(R.string.app_name)) },
                            navigationIcon = {
                                Icon(painter = painterResource(R.drawable.logo), "@null")
                            }
                        )
                        FeaturedCourses(pets = pets)
                    }
                }
            }
        }
    }


    @Composable
    fun FeaturedCourses(
        pets: List<Pet>,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            StaggeredVerticalGrid(
                maxColumnWidth = 220.dp,
                modifier = Modifier.padding(4.dp)
            ) {
                pets.forEach { course ->
                    FeaturedCourse(course)
                }
            }
        }
    }

    @Composable
    fun FeaturedCourse(
        pet: Pet,
        modifier: Modifier = Modifier
    ) {
        val ctx = LocalContext.current
        Surface(
            modifier = modifier.padding(4.dp),
            color = MaterialTheme.colors.surface,
            shape = MaterialTheme.shapes.medium
        ) {
            Card {
                Column(Modifier.clickable(
                    onClick = {
                        Intent(ctx, DetailActivity::class.java).apply {
                            putExtra(SELECTED_BUNDLE_PARAMETER, pet.id)
                        }.let { intent ->
                            startActivity(ctx, intent, null)
                        }
                    }
                )) {
                    NetworkImage(
                        url = "https://placedog.net/640/${(100..500).random()}?id=${pet.id + 2}",
                        contentDescription = "Good boi, ${pet.name}"
                    )

                    Box(modifier = modifier.padding(4.dp)) {
                        Column {
                            Text(pet.name, style = MaterialTheme.typography.h6)
                            Text(
                                "${pet.sex.name.toLowerCase(Locale.ROOT)} • ${pet.age} y.o.",
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun StaggeredVerticalGrid(
        modifier: Modifier = Modifier,
        maxColumnWidth: Dp,
        content: @Composable () -> Unit
    ) {
        Layout(
            content = content,
            modifier = modifier
        ) { measurables, constraints ->
            check(constraints.hasBoundedWidth) {
                "Unbounded width not supported"
            }
            val columns = ceil(constraints.maxWidth / maxColumnWidth.toPx()).toInt()
            val columnWidth = constraints.maxWidth / columns
            val itemConstraints = constraints.copy(maxWidth = columnWidth)
            val colHeights = IntArray(columns) { 0 } // track each column's height
            val placeables = measurables.map { measurable ->
                val column = shortestColumn(colHeights)
                val placeable = measurable.measure(itemConstraints)
                colHeights[column] += placeable.height
                placeable
            }

            val height =
                colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
                    ?: constraints.minHeight
            layout(
                width = constraints.maxWidth,
                height = height
            ) {
                val colY = IntArray(columns) { 0 }
                placeables.forEach { placeable ->
                    val column = shortestColumn(colY)
                    placeable.place(
                        x = columnWidth * column,
                        y = colY[column]
                    )
                    colY[column] += placeable.height
                }
            }
        }
    }

    private fun shortestColumn(colHeights: IntArray): Int {
        var minHeight = Int.MAX_VALUE
        var column = 0
        colHeights.forEachIndexed { index, height ->
            if (height < minHeight) {
                minHeight = height
                column = index
            }
        }
        return column
    }

    companion object {
        internal var pets: ArrayList<Pet> = arrayListOf()
        private const val NUMBER_OF_PETS = 30
        internal const val SELECTED_BUNDLE_PARAMETER = "selected"
    }
}

