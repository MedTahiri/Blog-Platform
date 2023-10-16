package com.tahiri.database

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object commenterdb : Table<Nothing>("commenter") {
    val blogid = int("blogid").primaryKey()
    val commenter = varchar("commenter")
}