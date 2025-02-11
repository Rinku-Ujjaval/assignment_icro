package com.example.assignment_icro_rinku.di

import com.example.assignment_icro_rinku.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity) // for field inject property inside the MainActivity
}