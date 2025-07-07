package com.kodebug.mediserveuser.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodebug.mediserveuser.data.model.userModel.SpecificUserData

@Composable
fun ProfileCard(modifier: Modifier = Modifier, userData: SpecificUserData) {
    Box(modifier = modifier.wrapContentSize()) {
        ElevatedCard(
            modifier = modifier
                .wrapContentSize()
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "user Detail")
                Spacer(modifier = modifier.height(30.dp))
                Text(
                    text = "👤 Name: ${userData.name}",
                    style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)
                )
                Text("📧 Email: ${userData.email}")
                Text("📞 Phone: ${userData.phoneNumber}")
                Text("🏠 Address: ${userData.address}")
                Text("🔐 Password: ${userData.password}")
                Text("🔒 Blocked: ${if (userData.blocked == 1) "Yes" else "No"}")
                Text("🕒 Joined: ${userData.createdAt}")
                Text("🔑 User ID: ${userData.userId}")
            }
        }
    }
}