package com.smp.musicspeed.library.mediastore

import android.provider.MediaStore
import android.text.TextUtils

/**
 * Created by steve on 10/18/17.
 */
fun parseUnknown(value: String?, convertValue: String): String {
    return if (value == null || value == MediaStore.UNKNOWN_STRING) {
        convertValue
    } else {
        value
    }
}

fun getSectionName(musicMediaTitle: String): String {
    var musicMediaTitle = musicMediaTitle
    if (TextUtils.isEmpty(musicMediaTitle)) return ""
    musicMediaTitle = musicMediaTitle.trim().toLowerCase()
    if (musicMediaTitle.startsWith("the ")) {
        musicMediaTitle = musicMediaTitle.substring(4)
    } else if (musicMediaTitle.startsWith("a ")) {
        musicMediaTitle = musicMediaTitle.substring(2)
    }
    return if (musicMediaTitle.isEmpty()) "" else musicMediaTitle[0].toString().toUpperCase()
}