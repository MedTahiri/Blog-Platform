package com.tahiri.database

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object blogdb : Table<Nothing>("blog") {
    val id = int("id").primaryKey()
    val title = varchar("title")
    val content = varchar("content")
    val view = int("nview")
    val like = int("nlike")
}