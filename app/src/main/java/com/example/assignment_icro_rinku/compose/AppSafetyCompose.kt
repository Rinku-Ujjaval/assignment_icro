@file:Suppress("DEPRECATION")

package com.example.assignment_icro_rinku.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_icro_rinku.base.UiState
import com.example.assignment_icro_rinku.model.Data
import com.example.assignment_icro_rinku.ui.theme.backGroundCardColor
import com.example.assignment_icro_rinku.ui.theme.buttonColor
import com.example.assignment_icro_rinku.ui.theme.enableColor
import com.example.assignment_icro_rinku.ui.theme.offLineColor
import com.example.assignment_icro_rinku.ui.theme.subTitleColor
import com.example.assignment_icro_rinku.ui.theme.titleColor
import com.example.assignment_icro_rinku.viewmodel.AppSafetyViewModel

@Composable
fun AppSafetyListView(appSafetyViewModel: AppSafetyViewModel, modifier: Modifier = Modifier) {
    AppsSafetyScreen(appSafetyViewModel, modifier)
}

@Composable
fun AppsSafetyScreen(appSafetyViewModel: AppSafetyViewModel, modifier: Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // card view
        DeviceRiskCard()
        Spacer(modifier = Modifier.height(16.dp))
        // status of device view
        AppProtectionStatus()
        Spacer(modifier = Modifier.height(16.dp))
        // display api data in this view
        AppRiskList(appSafetyViewModel)
    }
}

@Composable
fun DeviceRiskCard() {
    Card(
        colors = CardDefaults.cardColors(backGroundCardColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .background(buttonColor, shape = CircleShape)
                    .clickable { }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.padding(5.dp),
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Info",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Device at high risk!",
                color = Color.White,
                fontSize = 28.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Last scan - 23.03.22 14:32",
                color = Color.White,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(buttonColor),
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(text = "Start Scan", color = Color.White)
            }
        }
    }
}

@Composable
fun AppProtectionStatus() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            buildAnnotatedString {
                append("App protection ")
                withStyle(
                    style = SpanStyle(
                        color = enableColor,
                    )
                ) {
                    append("Enabled")
                }
            },
            modifier = Modifier
                .background(
                    color = subTitleColor.copy(alpha = 0.1f), shape = RoundedCornerShape(16.dp)
                )
                .padding(4.dp)
                .padding(horizontal = 2.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 14.sp)
        )
    }
}

@Composable
fun AppRiskList(appSafetyViewModel: AppSafetyViewModel) {
    // collect flow data from viewModel
    val uiState = appSafetyViewModel.uiState.collectAsState().value
    when (uiState) {
        is UiState.Success -> {
            val data = uiState.data.toList()
            Box(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(4.dp)
            ) {
                LazyColumn {
                    items(data) {
                        AppRiskItem(it)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
        // if api getting error
        is UiState.Error -> Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(uiState.message)
        }

        // first time load the data
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }
}

@Composable
fun AppRiskItem(data: Data) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // check with api status wise
        if (data.url_status.toString().isNotEmpty()) {
            var color: Color? = null
            color = if (data.url_status.toString().toLowerCase() == "online") {
                backGroundCardColor
            } else if (data.url_status.toString().toLowerCase() == "offline") {
                offLineColor
            } else {
                return
            }
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(color = color, RoundedCornerShape(50))
                    .wrapContentSize(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = data.threat.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = titleColor
            )
            Text(text = data.urlhaus_reference.toString(), fontSize = 14.sp, color = subTitleColor)
        }
        Spacer(
            Modifier
                .weight(4f)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
