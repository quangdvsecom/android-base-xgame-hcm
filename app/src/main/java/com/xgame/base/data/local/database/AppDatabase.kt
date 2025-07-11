package com.xgame.base.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xgame.base.data.model.TestUser


@Database(entities = [TestUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testUserDao(): TestUserDao
}