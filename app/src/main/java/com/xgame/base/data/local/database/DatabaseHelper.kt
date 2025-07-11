package com.xgame.base.data.local.database



import com.xgame.base.data.model.TestUser
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {
    fun getUsers(): Flow<List<TestUser>>

}