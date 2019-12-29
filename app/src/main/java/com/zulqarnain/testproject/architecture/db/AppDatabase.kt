package com.zulqarnain.testproject.architecture.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zulqarnain.testproject.data.local.Todo

@Database(
    entities = [
        Todo::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun todoDao(): todoDao

}
