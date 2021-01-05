package com.android.company.app.androidtask.di


import com.android.company.app.androidtask.data.remote.Network
import com.android.company.app.androidtask.data.remote.RemoteDataSource
import com.android.company.app.androidtask.repositories.IHomeRepo
import com.android.company.app.androidtask.repositories.HomeRepo
import com.android.company.app.androidtask.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val network =  module {
    factory { Network.apiService }
}

private val remoteModule = module { factory { RemoteDataSource(get()) } }

private val repositoryModule = module { single<IHomeRepo> {HomeRepo(get())} }

private val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

fun getModules() : List<Module>{

    return  listOf(
        network,
        remoteModule,
        repositoryModule,
        viewModelModule
    )
}