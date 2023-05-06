package com.example.eq_hm_wearos_app.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.eq_hm_wearos_app.presentation.data.ArcsState
import com.example.eq_hm_wearos_app.presentation.ui.ArcsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ArcsViewModel(
    private val state: ArcsState = ArcsState()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArcsUiState())
    val uiState: StateFlow<ArcsUiState> = _uiState.asStateFlow()

    fun changeArc1Value(progress: Float) { state.arcNo1.progress = progress }
    fun getArc1Progress() : Float { return state.arcNo1.progress }

    fun changeArc2Value(progress: Float) { state.arcNo2.progress = progress }
    fun getArc2Progress() : Float { return state.arcNo2.progress }

    fun changeArc3Value(progress: Float) { state.arcNo3.progress = progress }
    fun getArc3Progress() : Float { return state.arcNo3.progress }

    fun changeArc4Value(progress: Float) { state.arcNo4.progress = progress }
    fun getArc4Progress() : Float { return state.arcNo4.progress }

    fun changeArc5Value(progress: Float) { state.arcNo5.progress = progress }
    fun getArc5Progress() : Float { return state.arcNo5.progress }

    fun changeArc6Value(progress: Float) { state.arcNo6.progress = progress }
    fun getArc6Progress() : Float { return state.arcNo6.progress }

    fun changeArc7Value(progress: Float) { state.arcNo7.progress = progress }
    fun getArc7Progress() : Float { return state.arcNo7.progress }

    fun changeArc8Value(progress: Float) { state.arcNo8.progress = progress }
    fun getArc8Progress() : Float { return state.arcNo8.progress }

    fun onArcChangeStateUpdate() {
        _uiState.update {
            ArcsUiState(
                arcStateNo1 = state.arcNo1.progress,
                arcStateNo2 = state.arcNo2.progress,
                arcStateNo3 = state.arcNo3.progress,
                arcStateNo4 = state.arcNo4.progress,
                arcStateNo5 = state.arcNo5.progress,
                arcStateNo6 = state.arcNo6.progress,
                arcStateNo7 = state.arcNo7.progress,
                arcStateNo8 = state.arcNo8.progress,
            )
        }

        Log.d("X","m3 ${_uiState.value.arcStateNo3}")
    }

    init {
        /*_uiState.value = ArcsUiState(
            0.3f,0.2f,
            0.2f,0.2f,
            0.2f,0.2f,
            0.2f,0.2f
        )*/
    }
}