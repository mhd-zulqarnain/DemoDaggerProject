package com.zulqarnain.testproject.architecture.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zulqarnain.testproject.data.local.Todo

@Dao
interface todoDao {
    @Query("select * from todo")
    fun getToDoList(): LiveData<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo: Todo)

}