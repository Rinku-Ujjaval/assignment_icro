package com.example.assignment_icro_rinku.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_icro_rinku.base.UiState
import com.example.assignment_icro_rinku.model.Data
import com.example.assignment_icro_rinku.repository.AppSafetyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AppSafetyViewModel(val appSafetyRepository: AppSafetyRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Data>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Data>>> = _uiState

    init {
        appSafetyList()
    }
    fun appSafetyList() {
        viewModelScope.launch(Dispatchers.IO) {
            appSafetyRepository.getSafetyData().catch { e ->
                _uiState.value = UiState.Error(e.message.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}