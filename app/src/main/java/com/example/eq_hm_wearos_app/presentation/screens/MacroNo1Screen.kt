package com.example.eq_hm_wearos_app.presentation.screens

import android.text.format.DateFormat
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import com.example.eq_hm_wearos_app.presentation.ArcsViewModel
import com.example.eq_hm_wearos_app.presentation.ui.ArcsUiState
import java.util.*


@Composable
fun MacroNo1Screen(modifier: Modifier = Modifier,
                   arcsViewModel: ArcsViewModel = viewModel()) {
    Scaffold() {
        val focusRequester: FocusRequester = remember { FocusRequester() }

        val arcUiState by arcsViewModel.uiState.collectAsState()
        //var progress by remember { mutableStateOf(arc1UiState.arcStateNo1) }

        TextOnTop()
        TitleAndValueText(title = "Macro", parameterName = "no 1", precision = 0, suffix = "%", arcUiState = arcUiState, arcsViewModel = arcsViewModel)
        Arc(arcsViewModel = arcsViewModel, focusRequester = focusRequester, arcUiState = arcUiState)
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
private fun TextOnTop() {
    Column(modifier = Modifier.padding(8.dp)) {
        TimeText(
            modifier = Modifier.fillMaxWidth(),
            timeSource = TimeTextDefaults.timeSource(
                DateFormat.getBestDateTimePattern(Locale.getDefault(), "Hm")
            ),
            //timeTextStyle = TextStyle(background = androidx.compose.ui.graphics.Color.Red)
        )
    }
}

@Composable
private fun TitleAndValueText(title: String, parameterName: String, precision: Int, suffix: String, arcUiState: ArcsUiState, arcsViewModel: ArcsViewModel) {
    var progress = arcsViewModel.getArc1Progress()
    progress *= 100 //convert from 0-1 to %

    var valueStr = progress.toString()
    if (precision == 0) valueStr = String.format("%.0f", progress)
    if (precision == 1) valueStr = String.format("%.1f", progress)
    if (precision == 2) valueStr = String.format("%.2f", progress)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(48.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(title, fontSize = 20.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(18.dp))
                Text(parameterName, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(12.dp))
                Text("$valueStr $suffix", textAlign = TextAlign.Center)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Arc(modifier: Modifier = Modifier, arcsViewModel: ArcsViewModel, focusRequester: FocusRequester, arcUiState: ArcsUiState) {
    Scaffold() {
        //val focusRequester: FocusRequester = remember { FocusRequester() }

        //val arc1UiState by arcsViewModel.uiState.collectAsState()
        var progress by remember { mutableStateOf(arcUiState.arcStateNo1) }

        Column(modifier = Modifier
            .fillMaxSize()
            .onRotaryScrollEvent {
                //handle rotary event
                progress = when {
                    it.verticalScrollPixels > 0 -> java.lang.Float.min (progress + 0.1f, 1f)
                    it.verticalScrollPixels < 0 -> java.lang.Float.max (progress - 0.1f, 0f)
                    else -> { progress }
                }

                arcsViewModel.changeArc1Value(progress)
                arcsViewModel.onArcChangeStateUpdate()

                true
            }
            .focusRequester(focusRequester)
            .focusable(),
        ) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                startAngle = 180f,
                endAngle = 90f,
                strokeWidth = 8.dp
            )
        }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}