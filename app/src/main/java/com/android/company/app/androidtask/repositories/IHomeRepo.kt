package com.android.company.app.androidtask.repositories

import com.android.company.app.androidtask.models.DataResult
import com.android.company.app.androidtask.models.Foods
import com.android.company.app.androidtask.models.RandomValueResponse

interface IHomeRepo {

    suspend fun getRandomValues() : DataResult<RandomValueResponse>
}