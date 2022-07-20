package com.matheusbrandao.productstore.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val CONTENT_TYPE_KEY = "Content-type"
private const val CONTENT_TYPE_VALUE = "application/json"

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(putAuthorizationHeaderInRequest(chain.request()))
    }

    private fun putAuthorizationHeaderInRequest(request: Request): Request {
        val builder = request.newBuilder()

        builder.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
        builder.method(request.method(), request.body())
        return builder.build()
    }
}
