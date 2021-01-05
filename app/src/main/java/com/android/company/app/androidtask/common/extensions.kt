package com.android.company.app.androidtask.common



fun Int.getSINRColor(): String {

    return when {
        this <= 0 -> "#000000"
        this in 0..5 -> "#F90500"
        this in 5..10 -> "#FD7632"
        this in 10..15 -> "#FBFD00"
        this in 15..20 -> "#00FF06"
        this in 20..25 -> "#027500"
        this in 25..30 -> "#0EFFF8"
        this > 30 -> "#0000F0"
        else -> "#00000"
    }
}


fun Int.getRSRQColor(): String {

    return when {
        this <= -19.5 -> "#000000"
        this in -14.0..-19.5 -> "#ff0000"
        this in -14..-9 -> "#ffee00"
        this in -9..-3 -> "#80ff00"
        this > -3 -> "#3f7806"
        else -> "#00000"
    }
}

fun Int.getRSRPColor(): String {

    return when {
        this <= -110 -> "#000A00"
        this in -110..-100 -> "#E51304"
        this in -100..-90 -> "#FAFD0C"
        this in -90..-80 -> "#02FA0E"
        this in -80..-70 -> "#0B440D"
        this in -70..-60 -> "#0EFFF8"
        this > -60 -> "#0007FF"
        else -> "#00000"
    }
}
