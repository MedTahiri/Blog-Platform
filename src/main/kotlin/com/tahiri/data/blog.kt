package com.tahiri.data

import kotlinx.serialization.Serializable

@Serializable
data class blog(
    val id: Int, val title: String, val content: String, val view: Int, val like: Int,
    val commenters : List<Commenter>
)
