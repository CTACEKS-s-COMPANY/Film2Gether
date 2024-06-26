package com.ctaceks.tasks.data.mappers

import com.ctaceks.tasks.data.model.TodoItemDto
import com.ctaceks.tasks.domain.model.TodoItem

/**
 * Domain -> Data layers [TodoItem] mapper
 */
fun TodoItem.toDto() = TodoItemDto(
    id = id,
    description = description,
    deadline = deadline?.toTimestamp(),
    priority = priority.mapToString(),
    isDone = isDone,
    color = color,
    createdAt = createdAt.toTimestamp(),
    editedAt = editedAt.toTimestamp(),
    lastUpdatedBy = lastUpdatedBy
)

/**
 * Data -> Domain layers [TodoItem] mapper
 */
fun TodoItemDto.fromDto() = TodoItem(
    id = id,
    description = description,
    deadline = deadline?.let { localDateTimeFromTimestamp(it) },
    priority = priorityFromString(priority),
    isDone = isDone,
    color = color,
    createdAt = localDateTimeFromTimestamp(createdAt),
    editedAt = localDateTimeFromTimestamp(editedAt),
    lastUpdatedBy = lastUpdatedBy
)
