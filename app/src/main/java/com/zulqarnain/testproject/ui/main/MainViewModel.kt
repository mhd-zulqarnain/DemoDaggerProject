package com.zulqarnain.testproject.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.architecture.repository.TodoRepository
import com.zulqarnain.testproject.data.local.Todo
import com.zulqarnain.testproject.data.remote.StoreCategoryResponse
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var retrofitService: MyService,
    var todoRepository: TodoRepository
) : ViewModel() {

    var _getlistdata = MutableLiveData("")
    var _insertResponse = MutableLiveData("")
    var _liveData = MutableLiveData(StoreCategoryResponse())

    var job: Job = Job()
    val vieModelScope = CoroutineScope(Dispatchers.Main + job)

    var liveResponse: LiveData<StoreCategoryResponse> = _liveData

    init {
        getDatafrom()
    }

    fun getDatafrom() {
        vieModelScope.launch {
            withContext(Dispatchers.IO) {
                val apiResponse = todoRepository.getCategory()
                if (apiResponse.body() != null && apiResponse.body().toString() != "[]") {
                    apiResponse.let { response ->
                        if (response.isSuccessful) {
                            withContext(Dispatchers.Main) {
                                _liveData.value = response.body()
                            }
                        }
                    }
                }
            }
        }
    }

    fun getLiveApiResponse(): LiveData<StoreCategoryResponse> {
        return liveResponse

    }

    fun insertData(des: String) {
        val todo = Todo()
        todo.decription = des
        vieModelScope.launch {
            todoRepository.insertTodo(todo)
            _insertResponse.value = "inserted"
        }
    }

    val todoLiveData: LiveData<List<Todo>> = Transformations.switchMap(_getlistdata) { param ->
        todoRepository.getToDoList()
    }


    fun getLatestList() {
        _getlistdata.value = "1"
    }


    fun getStoreCategories() {

        retrofitService.getCategory("goshoppi777", "22")
            .enqueue(object : Callback<StoreCategoryResponse> {
                override fun onFailure(call: Call<StoreCategoryResponse>, t: Throwable) {
                    Log.e("DummyFragment", "response is null, ErrorBody:${t}")
                }

                override fun onResponse(
                    call: Call<StoreCategoryResponse>,
                    response: Response<StoreCategoryResponse>
                ) {
                    Log.e("DummyFragment", "response ")
//                    _logResponse.value = response.toString()
                }
            })

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}