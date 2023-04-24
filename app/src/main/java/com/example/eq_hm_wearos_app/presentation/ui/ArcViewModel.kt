package com.example.eq_hm_wearos_app.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.eq_hm_wearos_app.presentation.data.ArcsState
import com.example.eq_hm_wearos_app.presentation.ui.ArcsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.Float.max
import java.lang.Float.min


class ArcsViewModel(
    private val state: ArcsState = ArcsState()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArcsUiState())
    val uiState: StateFlow<ArcsUiState> = _uiState.asStateFlow()

    fun changeArc1Value(progress: Float) { state.arcNo1.progress = progress }
    fun getArc1Progress() : Float { return state.arcNo1.progress }

    fun changeArc2Value(progress: Float) { state.arcNo2.progress = progress }
    fun getArc2Progress() : Float { return state.arcNo2.progress }

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