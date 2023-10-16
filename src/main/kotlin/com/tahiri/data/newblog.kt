package com.tahiri.data

import kotlinx.serialization.Serializable

@Serializable
data class newblog(
    val title: String,
    val content: String
)
