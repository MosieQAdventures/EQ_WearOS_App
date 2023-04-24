/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

@file:OptIn(ExperimentalWearMaterialApi::class)

package com.example.eq_hm_wearos_app.presentation

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import com.example.eq_hm_wearos_app.presentation.screens.MacroNo1Screen
import com.example.eq_hm_wearos_app.presentation.screens.MacroNo2Screen
import com.example.eq_hm_wearos_app.presentation.theme.EQ_HM_WearOS_AppTheme

import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    EQ_HM_WearOS_AppTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            /*Scaffold {
                TextOnTop()
                TitleAndValueText(title = "Macro", parameterName = "no 1", value = (progress*100), precision = 0, suffix = "%")
                Arc()
                PagerHorizontal()
            }*/
            Scaffold {
                MacroNo1Screen()
                //MacroNo2Screen()
                PagerHorizontal()
            }
        }
    }
}

@Composable
fun PagerHorizontal() {
    val maxPages = 8
    var selectedPage by remember { mutableStateOf(0) }
    var finalValue by remember { mutableStateOf(0) }

    val animatedSelectedPage by animateFloatAsState(
        targetValue = selectedPage.toFloat(),
    ) {
        finalValue = it.toInt()
    }

    val pageIndicatorState: PageIndicatorState = remember {
        object : PageIndicatorState {
            override val pageOffset: Float
                get() = animatedSelectedPage - finalValue
            override val selectedPage: Int
                get() = finalValue
            override val pageCount: Int
                get() = maxPages
        }
    }

    Box(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                Button(
                    onClick = { selectedPage-- },
                    enabled = (selectedPage != 0),
                    modifier = Modifier.size(ButtonDefaults.ExtraSmallButtonSize),
                    colors = ButtonDefaults.secondaryButtonColors()
                ) {
                    Icon(Icons.Rounded.KeyboardArrowLeft, "PrevPage")
                }
                Spacer(modifier = Modifier.width(116.dp))
                Button(
                    onClick = { selectedPage++ },
                    enabled = (selectedPage != maxPages-1),
                    modifier = Modifier.size(ButtonDefaults.ExtraSmallButtonSize),
                    colors = ButtonDefaults.secondaryButtonColors()
                ) {
                    Icon(Icons.Rounded.KeyboardArrowRight, "NextPage")
                }
            }
        }
        HorizontalPageIndicator(
            pageIndicatorState = pageIndicatorState
        )
    }
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}