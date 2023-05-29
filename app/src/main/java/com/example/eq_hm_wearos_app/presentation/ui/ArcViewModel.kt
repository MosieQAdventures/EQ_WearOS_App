package com.example.eq_hm_wearos_app.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.eq_hm_wearos_app.presentation.data.ArcsState
import com.example.eq_hm_wearos_app.presentation.ui.ArcsUiState
import com.example.eq_hm_wearos_app.presentation.vstSend.Singleton
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

        //Log.d("VM","m3 ${_uiState.value.arcStateNo3}")
        //Log.d("VM","m8 ${_uiState.value.arcStateNo8}")

        //Singleton stuff test
        //fill progress list before send
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m1p", 0, state.arcNo1.progress)
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m2p", 1, state.arcNo2.progress)
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m3p", 2, state.arcNo3.progress)
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m4p", 3, state.arcNo4.progress)
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m5p", 4, state.arcNo5.progress)
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m6p", 5, state.arcNo6.progress)
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m7p", 6, state.arcNo7.progress)
        Singleton.getInstance().setProgressDescriptionIndividualListValue("m8p", 7, state.arcNo8.progress)

        Singleton.getInstance().executeAsyncTask()


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