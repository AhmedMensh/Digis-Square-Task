package com.android.company.app.androidtask.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.company.app.androidtask.models.DataResult
import com.android.company.app.androidtask.models.RandomValueResponse
import com.android.company.app.androidtask.repositories.IHomeRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val iHomeRepo: IHomeRepo) : ViewModel() {

    var error = MutableLiveData<String?>()

    fun getRandomValues(): MutableLiveData<RandomValueResponse> {
        val data = MutableLiveData<RandomValueResponse>()
        viewModelScope.launch {
            when(val result = withContext(IO){iHomeRepo.getRandomValues()}){
                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }
                is DataResult.Error ->{
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }
}