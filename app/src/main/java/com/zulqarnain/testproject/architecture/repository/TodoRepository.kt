package com.zulqarnain.testproject.architecture.repository

import com.zulqarnain.testproject.data.local.Todo

interface TodoRepository{
    suspend fun insertTodo(todo: Todo)
}