package com.ctaceks.tasks.data.di

import android.content.Context
import androidx.room.Room
import com.ctaceks.core.di.AppScope
import com.ctaceks.tasks.data.TasksRepositoryImpl
import com.ctaceks.tasks.data.local.db.TaskDao
import com.ctaceks.tasks.data.local.db.TasksDatabase
import com.ctaceks.tasks.data.utils.DATABASE_NAME
import com.ctaceks.tasks.domain.repo.TodoItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Dagger module of tasks data layer
 *
 * Provides [TaskDao] and [TodoItemsRepository]
 */
@Module(includes = [TasksDataBindingModule::class])
class TasksDataModule {
    @Provides
    @AppScope
    fun provideDatabase(
        context: Context
    ): TasksDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TasksDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    @Provides
    @AppScope
    fun provideTaskDao(
        db: TasksDatabase
    ): TaskDao {
        return db.taskDao()
    }
}

@Module
interface TasksDataBindingModule {
    @Binds
    @AppScope
    fun provideTodoItemsRepository(
        repo: TasksRepositoryImpl
    ): TodoItemsRepository
}
