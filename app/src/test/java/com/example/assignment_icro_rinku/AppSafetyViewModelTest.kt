package com.example.assignment_icro_rinku

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.assignment_icro_rinku.model.AppSafety
import com.example.assignment_icro_rinku.model.Data
import com.example.assignment_icro_rinku.network.Services
import com.example.assignment_icro_rinku.repository.AppSafetyRepository
import com.example.assignment_icro_rinku.viewmodel.AppSafetyViewModel
import io.mockk.mockkClass
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AppSafetyViewModelTest {

    @Mock
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var appSafetyRepository: AppSafetyRepository

    @Mock
    lateinit var services: Services

    lateinit var appSafetyViewModel: AppSafetyViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        appSafetyViewModel = AppSafetyViewModel(appSafetyRepository)
    }

    // app safety viewmodel
    @Test
    fun appSafetyViewModel() {
        runBlocking {
            //mock data list mock
            val data = listOf<Data>(Data(id = 0, url_status = "", threat = ""))

            var dataList =
                mutableListOf<Data>(
                    Data(
                        id = 0,
                        url_status = "Online",
                        urlhaus_reference = "http://Rinku",
                    )
                )

            val appSafety = AppSafety(query_status = "", urls = dataList)

            // mock services api
            Mockito.`when`(services.appSafety())
                .thenReturn(appSafety)

            // mock app safety list data
            Mockito.`when`(appSafetyRepository.getSafetyData()).thenReturn(flowOf(data))

            // get data from viewmodel
            appSafetyViewModel = AppSafetyViewModel(appSafetyRepository)
            appSafetyViewModel.appSafetyList()

            val user = appSafetyViewModel.uiState.value
            assertEquals(false, user.toString().isEmpty())
        }
    }
}