package com.kodebug.mediserveuser.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kodebug.mediserveuser.R
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.ui.theme.brush
import com.kodebug.mediserveuser.ui.theme.darkGreen
import com.kodebug.mediserveuser.ui.theme.darkGreen2
import com.kodebug.mediserveuser.ui.theme.latoFont
import com.kodebug.mediserveuser.ui.theme.madimiOneFont
import com.kodebug.mediserveuser.ui.theme.softGreen2
import com.kodebug.mediserveuser.viewmodel.SplashViewModel
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.CreateUiEvent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.splashEvent.collect { event ->
            when (event) {
                is CreateUiEvent.Navigate -> navController.navigate(event.route) {
                    popUpTo(Routes.SplashScreenRoute) {
                        inclusive = true
                    }
                }
                is CreateUiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(true) {
        delay(2000)
        viewModel.checkUserSession()
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(brush = brush)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_green_element),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(650.dp)
                    .offset((-260).dp, (-320).dp)
                    .rotate(230f)
            )
            Image(
                painter = painterResource(id = R.drawable.back_green_element),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(650.dp)
                    .offset((260).dp, (520).dp)
                    .rotate(0f)
            )
            Image(
                painter = painterResource(id = R.drawable.doctor_explain_coronavirus),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(380.dp)
                    .align(alignment = Alignment.TopCenter)
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 350.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to MediServe",
                fontSize = 38.sp,
                textAlign = TextAlign.Center,
                fontFamily = madimiOneFont,
                color = darkGreen2,
                modifier = modifier.fillMaxWidth()
            )
            Text(
                text = "“Your Health, Our Mission.”",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontFamily = latoFont,
                letterSpacing = 6.sp,
                color = darkGreen,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.height(160.dp))
            Text(
                text = "Your one-stop solution for fast,\n reliable medicine distribution.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = latoFont,
                letterSpacing = 2.sp,
                color = darkGreen,
                modifier = modifier.fillMaxWidth()
            )
            LinearProgressIndicator(
                modifier = modifier.fillMaxWidth(.65f),
                color = softGreen2,
                trackColor = darkGreen2
            )
        }
    }
}