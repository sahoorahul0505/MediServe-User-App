package com.kodebug.mediserveuser.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier.fillMaxSize()
//    ){
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            bottomBar = {
                BottomAppBar {

                }
            }
        ) { paddingValues ->
            Box(modifier = modifier.fillMaxSize().padding(paddingValues)){

            }
        }
//    }
}