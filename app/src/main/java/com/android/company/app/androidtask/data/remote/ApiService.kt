package com.android.company.app.androidtask.data.remote

import com.android.company.app.androidtask.models.ApiResponse
import com.android.company.app.androidtask.models.Foods
import com.android.company.app.androidtask.models.RandomValueResponse
import retrofit2.http.GET


interface ApiService {


    @GET("random")
    suspend fun getRandomValues() : RandomValueResponse
}







