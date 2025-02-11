package com.example.assignment_icro_rinku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_icro_rinku.base.AppViewModelProvider
import com.example.assignment_icro_rinku.compose.AppSafetyListView
import com.example.assignment_icro_rinku.di.AppSafetyApplication
import com.example.assignment_icro_rinku.ui.theme.Assignment_icroTheme
import com.example.assignment_icro_rinku.ui.theme.appBackGroundColor
import com.example.assignment_icro_rinku.viewmodel.AppSafetyViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var appSafetyViewModel: AppSafetyViewModel

    // Dagger will provide the object to this variable through field injection
    @Inject
    lateinit var appViewModelProvider: AppViewModelProvider

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inject app component
        (application as AppSafetyApplication).applicationComponent.inject(this)

        // what the above code do is, It will check the file for any of the @Inject property and if there are any
        // it will inject the correct object to them. Here it is appViewModelProvider

        appSafetyViewModel =
            ViewModelProvider(this, appViewModelProvider)[AppSafetyViewModel::class.java]

        enableEdgeToEdge()

        setContent {
            Assignment_icroTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(
                                "App Safety", style = TextStyle(fontSize = 22.sp)
                            )
                        }, navigationIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        })
                    }, modifier = Modifier
                        .background(appBackGroundColor)
                        .fillMaxSize()
                ) { innerPadding ->
                    AppSafetyListView(
                        appSafetyViewModel, modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
