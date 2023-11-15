package com.xinhui.mob201.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xinhui.mob201.data.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        const val DB_NAME = "todo_database"
    }
}