package com.ctaceks.tasks.data.utils

import com.ctaceks.tasks.data.model.TodoItemDto

/**
 * Merge old tasks with new tasks
 *
 * Can be used for merging local tasks with remote tasks
 */
internal class TasksMerger {
    fun mergeNewTasksWithOld(
        old: List<TodoItemDto>,
        new: List<TodoItemDto>
    ): List<TodoItemDto> {
        val newMapped = new.associateBy(TodoItemDto::id).toMutableMap()
        old.forEach { oldValue ->
            val newValue = newMapped[oldValue.id]
            if (newValue != null && oldValue.editedAt > newValue.editedAt)
                newMapped[newValue.id] = oldValue
        }
        return newMapped.values.toList()
    }

    fun mergeOldTasksWithNew(
        old: List<TodoItemDto>,
        new: List<TodoItemDto>,
        recentlyDeleted: Set<String>
    ): List<TodoItemDto> {
        val oldMapped = old.associateBy(TodoItemDto::id).toMutableMap()
        val diff = mutableListOf<TodoItemDto>()

        new.forEach { newValue ->
            val oldValue = oldMapped[newValue.id]

            if (oldValue != null) {
                if (newValue.editedAt > oldValue.editedAt)
                    oldMapped[newValue.id] = newValue
            } else {
                diff.add(newValue)
            }
        }

        val newTasks = diff.filter { !recentlyDeleted.contains(it.id) }

        return oldMapped.values + newTasks
    }
}
