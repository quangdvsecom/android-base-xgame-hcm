package com.xgame.base.data.network.api

import com.xgame.base.data.model.ConfigResponse
import retrofit2.http.GET

interface ApiConfigService {
    @GET("api_v2/menu/134.html")
    suspend fun configApp(): ConfigResponse
}
