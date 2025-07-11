package com.kodebug.mediserveuser.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kodebug.mediserveuser.ui.theme.brush
import com.kodebug.mediserveuser.ui.theme.darkGreen
import com.kodebug.mediserveuser.ui.theme.darkGreen2
import com.kodebug.mediserveuser.ui.theme.softGreen2
import com.kodebug.mediserveuser.ui.theme.whiteGreen

@Composable
fun CustomButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    OutlinedButton(
        onClick = {
            onClick()
        },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = softGreen2.copy(alpha = 0.5f),
            disabledContainerColor = Color.LightGray.copy(alpha = .3f),
            disabledContentColor = darkGreen,
            contentColor = darkGreen2
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = 2.dp,
            brush = Brush.verticalGradient(listOf(whiteGreen, whiteGreen))
        ),
        modifier = Modifier
            .fillMaxWidth(.7f)
            .height(60.dp)
    ) {

    }
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {

}