package com.zulqarnain.testproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(
    tableName = "todo"
)
class Todo{

    @PrimaryKey(autoGenerate = true)
    var id:Long=0
    var decription:String=""
}