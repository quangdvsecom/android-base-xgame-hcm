package com.xgame.base.data.network.api

import com.google.gson.annotations.SerializedName
import com.xgame.base.data.model.Notice
import com.xgame.base.data.model.Setting
import com.xgame.base.data.response.BaseDataNoticeResponse
import com.xgame.base.data.response.BaseDataResponse
import com.xgame.base.data.response.BaseResponseNotice
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by ElChuanmen on 1/16/2025.
 * Telegram : elchuanmen
 * Phone :0949514503-0773209008
 * Mail :doanvanquang146@gmail.com
 */
interface ApiService {
    //Setting
    @GET("api/v1/setting")
    suspend fun getSetting(): BaseDataResponse<MutableList<Setting>>
    @GET("api/v1/notification/list")
    suspend fun getListNotice(@Query("page") page: Int): BaseDataResponse<BaseResponseNotice<BaseDataNoticeResponse<MutableList<Notice>>>>


}