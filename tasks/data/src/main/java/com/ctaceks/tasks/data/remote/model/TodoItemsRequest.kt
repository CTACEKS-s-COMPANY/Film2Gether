package com.ctaceks.tasks.data.remote.model

import com.ctaceks.tasks.data.model.TodoItemDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Http request model
 */
@Serializable
data class TodoItemsRequest(
    @SerialName("list") val tasks: List<TodoItemDto>
)
