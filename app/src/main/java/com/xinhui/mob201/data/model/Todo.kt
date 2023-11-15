package com.xinhui.mob201.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,
    val id: String = "",
    val title: String = "",
    val desc: String = "",
    val addedBy: String = "khayrul",
    val createdAt: String = "",
    val isFinish: Boolean = false
) {
    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "title" to title,
            "desc" to desc,
            "addedBy" to addedBy,
            "createdAt" to createdAt,
            "isFinish" to isFinish
        )
    }

    companion object {
        fun fromHashMap(hash: Map<String, Any>): Todo {
            return Todo(
                id = hash["id"].toString(),
                title = hash["title"].toString(),
                desc = hash["desc"].toString(),
                addedBy = hash["addedBy"].toString(),
                createdAt = hash["createdAt"].toString(),
                isFinish = hash["isFinish"] as Boolean,
            )
        }
    }
}