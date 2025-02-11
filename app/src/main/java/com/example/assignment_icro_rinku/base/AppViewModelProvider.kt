package com.example.assignment_icro_rinku.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_icro_rinku.repository.AppSafetyRepository
import com.example.assignment_icro_rinku.viewmodel.AppSafetyViewModel
import javax.inject.Inject


class AppViewModelProvider @Inject constructor(private val repository: AppSafetyRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppSafetyViewModel(repository) as T
    }
}

