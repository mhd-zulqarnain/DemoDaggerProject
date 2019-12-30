package com.zulqarnain.testproject.di.module

import android.app.Application
import androidx.room.Room
import com.zulqarnain.testproject.architecture.db.AppDatabase
import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {


    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoDoa(db:AppDatabase): todoDao {
        return db.todoDao()
    }

}