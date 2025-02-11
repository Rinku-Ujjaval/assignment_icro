package com.example.assignment_icro_rinku

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.assignment_icro_rinku.model.Data
import com.example.assignment_icro_rinku.repository.AppSafetyRepository
import com.example.assignment_icro_rinku.viewmodel.AppSafetyViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AppSafetyViewModelTest {

    @Mock
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var appSafetyRepository: AppSafetyRepository

    lateinit var appSafetyViewModel: AppSafetyViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        appSafetyViewModel = AppSafetyViewModel(appSafetyRepository)
    }

    // write test case same as UserList and Post List for viewmodel
    @Test
    fun appSafetyViewModel() {
        runBlocking {
            //mock account mock
            val mockAccount = mutableListOf<Data>()
            mockAccount.add(Data(id = 0, url_status = "", threat = ""))

            // mock user login repository
            Mockito.`when`(appSafetyRepository.getSafetyData()).thenReturn(flowOf(mockAccount))

            // get data from viewmodel
            appSafetyViewModel = AppSafetyViewModel(appSafetyRepository)
            appSafetyViewModel.appSafetyList()

            val user = appSafetyViewModel.uiState.value
            assertEquals(user, user)
        }
    }
}