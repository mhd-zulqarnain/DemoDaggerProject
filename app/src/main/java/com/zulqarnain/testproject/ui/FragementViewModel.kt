package com.zulqarnain.testproject.ui

import androidx.lifecycle.ViewModel
import com.zulqarnain.testproject.architecture.repository.TodoRepository
import javax.inject.Inject

class FragementViewModel @Inject constructor(
    var todoRepository: TodoRepository
):ViewModel(){


}