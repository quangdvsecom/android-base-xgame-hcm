package com.xgame.base.data.local.database

import com.xgame.base.data.local.database.AppDatabase
import com.xgame.base.data.local.database.DatabaseHelper
import com.xgame.base.data.model.TestUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatabaseHelperImpl @Inject constructor(private val appDatabase: AppDatabase) :
    DatabaseHelper {
    override fun getUsers(): Flow<List<TestUser>> = flow { emit(appDatabase.testUserDao().getAll()) }
}