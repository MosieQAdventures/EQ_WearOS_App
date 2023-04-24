package com.example.eq_hm_wearos_app.presentation.data

data class ArcsState(
    var arcNo1: Arc = Arc(),
    var arcNo2: Arc = Arc(),
    var arcNo3: Arc = Arc(),
    var arcNo4: Arc = Arc(),
    var arcNo5: Arc = Arc(),
    var arcNo6: Arc = Arc(),
    var arcNo7: Arc = Arc(),
    var arcNo8: Arc = Arc(),
) {
    data class Arc(
        var progress: Float = 0.2f
    )
}