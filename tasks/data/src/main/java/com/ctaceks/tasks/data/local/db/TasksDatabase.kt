package com.ctaceks.tasks.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ctaceks.tasks.data.model.TodoItemDto
import com.ctaceks.tasks.domain.model.TodoItem

/**
 * Room database for storing [TodoItem]s
 */
@Database(entities = [TodoItemDto::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
