package com.local.locallift.api

import com.local.lift.api.RetrofitInstance
import com.local.lift.model.SignInRequest
import com.local.lift.model.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("sign-in")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    companion object {
        fun create(): AuthService {
            return RetrofitInstance.api
        }
    }
}
