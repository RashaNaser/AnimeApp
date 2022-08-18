package com.rns.animefriendapplication.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer(
    val embed_url: String,
    val url: String,
    val youtube_id: String
) : Parcelable