package com.example.assignment_icro_rinku.repository

import com.example.assignment_icro_rinku.model.AppSafety
import com.example.assignment_icro_rinku.model.Data
import com.example.assignment_icro_rinku.network.Services
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class AppSafetyRepository @Inject constructor(private val services: Services) {

    open fun getSafetyData(): Flow<List<Data>> {
        return flow {
            // get safety data from api calling using flow
            emit(services.appSafety())
        }.map { return@map it.urls as List<Data> }
    }
}
