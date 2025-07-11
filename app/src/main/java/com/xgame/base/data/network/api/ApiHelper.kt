package com.xgame.base.data.network.api


import com.xgame.base.data.model.ConfigResponse
import com.xgame.base.data.model.Notice
import com.xgame.base.data.model.Setting
import com.xgame.base.data.response.BaseDataNoticeResponse
import com.xgame.base.data.response.BaseDataResponse
import com.xgame.base.data.response.BaseResponseNotice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by ElChuanmen on 1/16/2025.
 * Telegram : elchuanmen
 * Phone :0949514503-0773209008
 * Mail :doanvanquang146@gmail.com
 */
interface ApiHelper {
    fun getSetting(): Flow<BaseDataResponse<MutableList<Setting>>>
    fun getListNotices(
        page: Int
    ): Flow<BaseDataResponse<BaseResponseNotice<BaseDataNoticeResponse<MutableList<Notice>>>>>
    fun configApp() : Flow<ConfigResponse>
}