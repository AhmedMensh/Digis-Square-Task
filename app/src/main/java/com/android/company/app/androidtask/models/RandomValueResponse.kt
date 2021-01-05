package com.android.company.app.androidtask.models


import com.squareup.moshi.Json

data class RandomValueResponse(
    @field:Json(name = "RSRP")
    var rSRP: Int? = null,
    @field:Json(name = "RSRQ")
    var rSRQ: Int? = null,
    @field:Json(name = "SINR")
    var sINR: Int? = null
)