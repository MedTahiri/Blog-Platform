package com.tahiri.data

import kotlinx.serialization.Serializable

@Serializable
data class Commenter(val id : Int , val commenter: String)
