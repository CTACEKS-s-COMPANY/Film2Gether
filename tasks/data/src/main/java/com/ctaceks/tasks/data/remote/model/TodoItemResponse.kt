package com.ctaceks.tasks.data.remote.model

import com.ctaceks.tasks.data.model.TodoItemDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Http response model
 */
@Serializable
data class TodoItemResponse(
    @SerialName("element") val task: TodoItemDto,
    @SerialName("revision") val revision: String,
    @SerialName("status") val status: String
)
