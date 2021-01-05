package com.android.company.app.androidtask.repositories

import com.android.company.app.androidtask.data.remote.RemoteDataSource
import com.android.company.app.androidtask.models.DataResult
import com.android.company.app.androidtask.models.Foods
import com.android.company.app.androidtask.models.RandomValueResponse

class HomeRepo(private val remoteDataSource: RemoteDataSource) : IHomeRepo {


    override suspend fun getRandomValues(): DataResult<RandomValueResponse> {
        return when (val result = remoteDataSource.getRandomValues()) {
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }
}