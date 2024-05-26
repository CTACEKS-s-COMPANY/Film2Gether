package com.ctaceks.tasks.data.remote

import com.ctaceks.auth.domain.AuthInfoProvider
import com.ctaceks.auth.domain.model.AuthInfo
import com.ctaceks.core.data.NetworkConstants.AUTHORIZATION
import com.ctaceks.core.data.NetworkConstants.LAST_KNOWN_REVISION
import com.ctaceks.core.data.NetworkConstants.OAuth
import com.ctaceks.tasks.data.model.TodoItemDto
import com.ctaceks.tasks.data.remote.model.RequestResult
import com.ctaceks.tasks.data.remote.model.TodoItemRequest
import com.ctaceks.tasks.data.remote.model.TodoItemResponse
import com.ctaceks.tasks.data.remote.model.TodoItemsRequest
import com.ctaceks.tasks.data.remote.model.TodoItemsResponse
import com.ctaceks.tasks.data.remote.model.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMessageBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Service for making tasks http requests
 *
 * All [HttpResponse]s are wrapped with [RequestResult]
 */
class TasksService @Inject constructor(
    private val client: HttpClient,
    authProvider: AuthInfoProvider
) {
    private val auth = authProvider.authInfoFlow().stateIn(
        CoroutineScope(Dispatchers.IO),
        started = SharingStarted.Eagerly,
        AuthInfo()
    )

    suspend fun getTasks(): RequestResult<TodoItemsResponse> =
        client.safeRequest { get { requestHeaders() } }

    suspend fun mergeTasks(tasks: List<TodoItemDto>): RequestResult<TodoItemsResponse> =
        client.safeRequest { patch { requestHeaders(); setBody(TodoItemsRequest(tasks)) } }

    suspend fun addTask(task: TodoItemDto): RequestResult<TodoItemResponse> =
        client.safeRequest { post { requestHeaders(); setBody(TodoItemRequest(task)) } }

    suspend fun getTask(id: String): RequestResult<TodoItemResponse> =
        client.safeRequest { get(id) { requestHeaders() } }

    suspend fun updateTask(task: TodoItemDto): RequestResult<TodoItemResponse> =
        client.safeRequest { put(task.id) { requestHeaders(); setBody(TodoItemRequest(task)) } }

    suspend fun deleteTask(id: String): RequestResult<TodoItemResponse> =
        client.safeRequest { delete(id) { requestHeaders() } }


    private fun HttpMessageBuilder.requestHeaders() {
        headers {
            append(AUTHORIZATION, "$OAuth ${auth.value.toString()}")
            append(LAST_KNOWN_REVISION, auth.value.toString())
        }
    }
}
