package com.dhote_chicken.rider.data

import android.content.SharedPreferences
import com.dhote_chicken.rider.ui.util.Constant.Companion.TOKEN

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        val request = chain.request()
        try {

            val token = sharedPreferences.getString(TOKEN, "")
            /*          val schoolId = sharedPreferences.getInt(KEY_SCHOOL_ID, 0)
                      val userId = if (userType == 3) sharedPreferences.getInt(KEY_STUD_UA_ID, 0)
                      else sharedPreferences.getInt(KEY_REG_ID, 0)*/

            val requestBuilder = request.newBuilder()
            if (request.header("No-Authorization") == null) {

/*                requestBuilder.addHeader("userid", sharedPreferences.getString(Constant.USERID, "").toString())
                requestBuilder.addHeader("collegeid",  sharedPreferences.getString(Constant.COLLEGEID, "").toString())
                requestBuilder.addHeader("appUserId",  BuildConfig.APPLICATION_ID)*/
                requestBuilder.addHeader("TenantId",  "Android")
                requestBuilder.addHeader("Authorization",  token.toString())
            }
            response = chain.proceed(requestBuilder.build())
        } catch (excep: SocketTimeoutException) {
            when (excep) {
                is SocketTimeoutException -> {
                    return Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(408)
                        .message("Socket timeout error")
                        .body("{${excep}}".toResponseBody(null)).build()
                }
            }
        }

        return response
    }

}