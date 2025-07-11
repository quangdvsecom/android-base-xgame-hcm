package com.xgame.base.data.network.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by ElChuanmen on 1/16/2025.
 * Telegram : elchuanmen
 * Phone :0949514503-0773209008
 * Mail :doanvanquang146@gmail.com
 */
interface RefreshTokenService {

    @POST("auth/refresh")
    fun refreshToken(@Body refreshToken: String): Call<RefreshTokenResponse>
}
data class RefreshTokenResponse(
    @SerializedName("access_token") val accessToken: String
)