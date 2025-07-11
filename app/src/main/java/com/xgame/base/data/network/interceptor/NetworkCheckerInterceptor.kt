package com.xgame.base.data.network.interceptor

import android.content.Context
import com.xgame.base.constant.Constant
import com.xgame.base.extension.hasConnection
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by ElChuanmen on 9/14/2022.
 * Telegram : elchuanmen
 * Phone :0949514503-0773209008
 * Mail :doanvanquang146@gmail.com
 */
class NetworkCheckerInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (hasConnection(context)) {
            chain.proceed(chain.request())
        } else {
            throw NoConnectivityException()
        }
    }

    class NoConnectivityException : IOException() {
        override val message: String
            get() = Constant.NO_INTERNET
    }
}