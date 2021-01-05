package com.android.company.app.androidtask.data.remote



class RemoteDataSource(private val api: ApiService) {

    suspend fun getRandomValues() = safeApiCall { api.getRandomValues() }
}
