package com.zulqarnain.testproject.di.module

import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.architecture.repository.TodoRepository
import com.zulqarnain.testproject.architecture.repository.TodoRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule{

    @Singleton
    @Provides
    fun provideRepository(todoDao: todoDao):TodoRepository {return TodoRepositoryImp(todoDao)}
}