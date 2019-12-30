package com.zulqarnain.testproject.architecture.repository

import androidx.lifecycle.LiveData
import com.zulqarnain.testproject.data.local.Todo

interface TodoRepository{
    suspend fun insertTodo(todo: Todo)
    fun getToDoList(): LiveData<List<Todo>>

}