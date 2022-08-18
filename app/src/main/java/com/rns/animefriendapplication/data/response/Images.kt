package com.rns.animefriendapplication.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val jpg: Jpg,
) : Parcelable