package com.zulqarnain.testproject.architecture.repository

import androidx.lifecycle.LiveData
import com.zulqarnain.testproject.data.local.Todo
import com.zulqarnain.testproject.data.remote.StoreCategoryResponse
import retrofit2.Response

interface TodoRepository{
    suspend fun insertTodo(todo: Todo)
    fun getToDoList(): LiveData<List<Todo>>
    fun getrM(): LiveData<StoreCategoryResponse>
    suspend fun getCategory(): Response<StoreCategoryResponse>

}