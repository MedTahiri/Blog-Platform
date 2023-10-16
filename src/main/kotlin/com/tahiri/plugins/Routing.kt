package com.tahiri.plugins

import com.tahiri.data.Commenter
import com.tahiri.data.blog
import com.tahiri.data.newblog
import com.tahiri.data.react
import com.tahiri.database.blogdb
import com.tahiri.database.commenterdb
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Application.configureRouting() {
    val database =
        Database.connect("jdbc:mysql://localhost:3306/Blog_Platform", user = "root", password = "password")
    routing {
        get("/") {
            val blogs = database.from(blogdb).select().map { row ->
                val Id = row[blogdb.id] ?: -1
                val title = row[blogdb.title] ?: ""
                val content = row[blogdb.content] ?: ""
                val view = row[blogdb.view] ?: -1
                val like = row[blogdb.like] ?: -1
                val commenters = database.from(commenterdb).select()
                    .map { Commenter(it[commenterdb.blogid]!!.toInt(), it[commenterdb.commenter].toString()) }
                    .filter { it.id == Id }
                blog(Id, title, content, view, like, commenters)
            }
            call.respond(blogs)
        }
        post("/new_blog") {
            val blog = call.receive<newblog>()
            database.insert(blogdb) {
                set(it.title, blog.title)
                set(it.content, blog.content)
                set(it.view, 0)
                set(it.like, 0)
            }
            call.respondText("add blog :${blog.title}")
        }
        get("/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val blog = database.from(blogdb).select().map {
                blog(
                    it[blogdb.id] ?: -1,
                    it[blogdb.title] ?: "",
                    it[blogdb.content] ?: "",
                    it[blogdb.view] ?: -1,
                    it[blogdb.like] ?: -1,
                    database.from(commenterdb).select()
                        .map { Commenter(it[commenterdb.blogid]!!.toInt(), it[commenterdb.commenter].toString()) }
                        .filter { it.id == id }
                )
            }.filter { it.id == id }
            call.respond(blog)
        }
        put("/react:{id}") {
            val id: Int = call.parameters["id"]!!.toInt()
            val react = call.receive<react>()
            database.insert(commenterdb) {
                set(it.blogid, id)
                set(it.commenter, react.commenter)
            }
            database.update(blogdb) {
                set(it.view, it.view + 1)
                if (react.like) {
                    set(it.like, it.like + 1)
                }
                where {
                    it.id eq id
                }
            }
            call.respondText("react blog :$id")
        }
        delete("/delete/{id}") {
            val id = call.parameters["id"]!!.toInt()
            database.delete(blogdb) {
                it.id eq id
            }
            database.delete(commenterdb) {
                it.blogid eq id
            }
            call.respondText("delete blog :$id")
        }
    }
}
