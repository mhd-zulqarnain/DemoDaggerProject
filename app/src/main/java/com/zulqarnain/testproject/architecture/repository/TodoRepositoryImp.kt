package com.zulqarnain.testproject.architecture.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.data.local.Todo
import com.zulqarnain.testproject.data.remote.StoreCategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData


class TodoRepositoryImp @Inject constructor(val todoDao: todoDao, val srvic: MyService) :
    TodoRepository {
    override suspend fun getCategory(): Response<StoreCategoryResponse> {

       return srvic.getCategory("goshoppi777", "22").execute()
    }

    private val todoListLiveData = MutableLiveData<StoreCategoryResponse>()

    override fun getrM(): LiveData<StoreCategoryResponse> {
        srvic.getCategory("goshoppi777", "22")
            .enqueue(object : Callback<StoreCategoryResponse> {
                override fun onFailure(call: Call<StoreCategoryResponse>, t: Throwable) {
                    Log.e("TodoRepositoryImp", "response is null, ErrorBody:${t}")
                }

                override fun onResponse(
                    call: Call<StoreCategoryResponse>,
                    response: Response<StoreCategoryResponse>
                ) {
                    todoListLiveData.value=response.body()!!
                    Log.e("TodoRepositoryImp", "response ")
//                    _logResponse.value = response.toString()
                }
            })
        return todoListLiveData
    }

    override fun getToDoList(): LiveData<List<Todo>> {
        return todoDao.getToDoList()
    }

    override suspend fun insertTodo(todo: Todo) {
        return withContext(Dispatchers.IO) { todoDao.insertTodo(todo) }

    }


}