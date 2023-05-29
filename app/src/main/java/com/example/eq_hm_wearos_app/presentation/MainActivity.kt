/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

@file:OptIn(ExperimentalWearMaterialApi::class)

package com.example.eq_hm_wearos_app.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.eq_hm_wearos_app.presentation.screens.*
import com.example.eq_hm_wearos_app.presentation.theme.EQ_HM_WearOS_AppTheme
import com.example.eq_hm_wearos_app.presentation.vstSend.ProgressDescription
import com.example.eq_hm_wearos_app.presentation.vstSend.Singleton
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

enum class MacroScreen() {
    Macro1,
    Macro2,
    Macro3,
    Macro4,
    Macro5,
    Macro6,
    Macro7,
    Macro8,
}

@Composable
fun WearApp() {
    val navController = rememberSwipeDismissableNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Singleton stuff start
    // init and set list of params to send
    var progressDescriptions: MutableList<ProgressDescription?> = ArrayList()
    progressDescriptions = createProgressList(progressDescriptions)

    Singleton.getInstance().setProgressDescriptionList(progressDescriptions)
    //Singleton stuff end

    EQ_HM_WearOS_AppTheme {
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

                SwipeDismissableNavHost(
                    navController = navController,
                    startDestination = MacroScreen.Macro1.name
                ) {
                    composable(route = MacroScreen.Macro1.name) {
                        MacroNo1Screen()
                    }
                    composable(route = MacroScreen.Macro2.name) {
                        MacroNo2Screen()
                    }
                    composable(route = MacroScreen.Macro3.name) {
                        MacroNo3Screen()
                    }
                    composable(route = MacroScreen.Macro4.name) {
                        MacroNo4Screen()
                    }
                    composable(route = MacroScreen.Macro5.name) {
                        MacroNo5Screen()
                    }
                    composable(route = MacroScreen.Macro6.name) {
                        MacroNo6Screen()
                    }
                    composable(route = MacroScreen.Macro7.name) {
                        MacroNo7Screen()
                    }
                    composable(route = MacroScreen.Macro8.name) {
                        MacroNo8Screen()
                    }
                }

                //MacroNo1Screen()
                //MacroNo2Screen()
                PagerHorizontal(navController)
            }
        }
    }
}

@Composable
fun PagerHorizontal(navController: NavHostController) {
    val maxPages = 8
    var selectedPage by remember { mutableStateOf(1) }
    var finalValue by remember { mutableStateOf(1) }

    val animatedSelectedPage by animateFloatAsState(
        targetValue = selectedPage.toFloat(),
    ) {
        finalValue = it.toInt()
    }

    val pageIndicatorState: PageIndicatorState = remember {
        object : PageIndicatorState {
            override val pageOffset: Float
                get() = animatedSelectedPage - finalValue - 1
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
                    onClick = {
                        navigateToPervPage(selectedPage, navController = navController)
                        selectedPage--
                    },
                    enabled = (selectedPage != 1),
                    modifier = Modifier.size(ButtonDefaults.ExtraSmallButtonSize),
                    colors = ButtonDefaults.secondaryButtonColors()
                ) {
                    Icon(Icons.Rounded.KeyboardArrowLeft, "PrevPage")
                }
                Spacer(modifier = Modifier.width(100.dp))
                Button(
                    onClick = {
                        navigateToNextPage(selectedPage, navController = navController)
                        selectedPage++
                    },
                    enabled = (selectedPage != maxPages),
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

fun navigateToNextPage(currentPage: Int, navController: NavHostController) {
    when (currentPage) {
        1 -> navController.navigate(MacroScreen.Macro2.name) { popUpTo(MacroScreen.Macro1.name) }
        2 -> navController.navigate(MacroScreen.Macro3.name) { popUpTo(MacroScreen.Macro1.name) }
        3 -> navController.navigate(MacroScreen.Macro4.name) { popUpTo(MacroScreen.Macro1.name) }
        4 -> navController.navigate(MacroScreen.Macro5.name) { popUpTo(MacroScreen.Macro1.name) }
        5 -> navController.navigate(MacroScreen.Macro6.name) { popUpTo(MacroScreen.Macro1.name) }
        6 -> navController.navigate(MacroScreen.Macro7.name) { popUpTo(MacroScreen.Macro1.name) }
        7 -> navController.navigate(MacroScreen.Macro8.name) { popUpTo(MacroScreen.Macro1.name) }
        8 -> {}
        else -> {}
    }
    Log.d("X", (currentPage+1).toString())
}
fun navigateToPervPage(currentPage: Int, navController: NavHostController) {
    when (currentPage) {
        8 -> navController.navigate(MacroScreen.Macro7.name) { popUpTo(MacroScreen.Macro1.name) }
        7 -> navController.navigate(MacroScreen.Macro6.name) { popUpTo(MacroScreen.Macro1.name) }
        6 -> navController.navigate(MacroScreen.Macro5.name) { popUpTo(MacroScreen.Macro1.name) }
        5 -> navController.navigate(MacroScreen.Macro4.name) { popUpTo(MacroScreen.Macro1.name) }
        4 -> navController.navigate(MacroScreen.Macro3.name) { popUpTo(MacroScreen.Macro1.name) }
        3 -> navController.navigate(MacroScreen.Macro2.name) { popUpTo(MacroScreen.Macro1.name) }
        2 -> navController.navigate(MacroScreen.Macro1.name) { popUpTo(MacroScreen.Macro1.name) }
        1 -> {}
        else -> {}
    }
    Log.d("X", (currentPage-1).toString())
}

//default values
fun createProgressList(progressList: MutableList<ProgressDescription?>): MutableList<ProgressDescription?> {
    progressList.add(ProgressDescription("m1p", 0.2f)) //0-19980        //index 0
    progressList.add(ProgressDescription("m2p", 0.2f)) //0-3           //index 1
    progressList.add(ProgressDescription("m3p", 0.2f)) //0-19980      //index 2
    progressList.add(ProgressDescription("m4p", 0.2f)) //0-489       //index 3
    progressList.add(ProgressDescription("m5p", 0.2f)) //0-99         //index 4
    progressList.add(ProgressDescription("m6p", 0.2f)) //0-19980      //index 5
    progressList.add(ProgressDescription("m7p", 0.2f)) //0-489       //index 6
    progressList.add(ProgressDescription("m8p", 0.2f)) //0-99         //index 7
    return progressList
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}