package com.ctaceks.other.work.workers

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.ctaceks.auth.domain.AuthInfoProvider
import com.ctaceks.other.work.R
import com.ctaceks.other.work.utils.SYNCHRONIZATION_CHANNEL_ID
import com.ctaceks.tasks.domain.repo.TodoItemsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlin.random.Random

/**
 * Periodically synchronize local data with remote data when
 * internet connection is available
 *
 * Can work independent of app state
 */
class ScheduledSynchronizationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val repo: TodoItemsRepository,
    private val authProvider: AuthInfoProvider
): CoroutineWorker(context, params) {
    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            Random.nextInt(),
            NotificationCompat.Builder(context, SYNCHRONIZATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_sync_24dp)
                .setContentText(context.getString(R.string.synchronizing))
                .setContentTitle(context.getString(R.string.synchronization_in_progress))
                .build()
        )
    }

    override suspend fun doWork(): Result {
        if (authProvider.authInfo().accessToken.isNotBlank() && !repo.refreshTodoItems())
            return Result.retry()
        return Result.success()
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): SynchronizationWorker
    }
}
