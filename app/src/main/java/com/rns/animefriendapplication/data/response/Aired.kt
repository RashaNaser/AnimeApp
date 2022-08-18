package com.rns.animefriendapplication.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Aired(
    val from: String,
    val string: String,
    val to: String
) : Parcelable