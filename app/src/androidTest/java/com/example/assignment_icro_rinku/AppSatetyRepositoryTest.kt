package com.example.assignment_icro_rinku

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.assignment_icro_rinku.model.AppSafety
import com.example.assignment_icro_rinku.model.Data
import com.example.assignment_icro_rinku.network.Services
import com.example.assignment_icro_rinku.repository.AppSafetyRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class AppSafetyRepositoryTest {
    @Mock
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    lateinit var appSafetyRepository: AppSafetyRepository

    @Mock
    lateinit var appAPIs: Services


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        appSafetyRepository = AppSafetyRepository(appAPIs)
    }

    // same as write test case for login and post list API
    @Test
    fun userAppSafetyRepository() {
        runBlocking {
            //mock model prepend
            var dataList =
                mutableListOf<Data>(
                    Data(
                        id = 0,
                        url_status = "",
                        urlhaus_reference = "http://Rinku",
                    )
                )

            val appSafety = AppSafety(query_status = "", urls = emptyList<Data>())

            // mock Datastore object
            Mockito.`when`(appAPIs.appSafety())
                .thenReturn(appSafety)

            // mock get user list from api

            //return result from API
            var result = appSafetyRepository.getSafetyData()

            assertEquals(true, result.first().isNotEmpty())

        }
    }

}