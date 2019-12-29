package com.zulqarnain.testproject.architecture.repository

import androidx.lifecycle.LiveData
import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.data.local.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepositoryImp @Inject constructor(val todoDao: todoDao) :TodoRepository {
    override fun getToDoList(): LiveData<Todo> {
        return todoDao.getToDoList()
    }

    override suspend fun insertTodo(todo: Todo) {
        return withContext(Dispatchers.IO) { todoDao.insertTodo(todo) }

    }



}