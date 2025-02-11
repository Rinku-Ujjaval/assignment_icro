package com.example.assignment_icro_rinku

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

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
        MockitoAnnotations.openMocks(this)
        appSafetyRepository = AppSafetyRepository(appAPIs)
    }

    // app safety data API call
    @Test
    fun userAppSafetyRepository() {
        runBlocking {
            //mock model prepend
            var dataList =
                mutableListOf<Data>(
                    Data(
                        id = 0,
                        url_status = "Online",
                        urlhaus_reference = "http://Rinku",
                    )
                )

            val appSafety = AppSafety(query_status = "", urls = dataList)

            Mockito.`when`(appAPIs.appSafety())
                .thenReturn(appSafety)


            //return result from API
            var result = appSafetyRepository.getSafetyData()

            assertEquals(true, result.first().isNotEmpty())

        }
    }

}