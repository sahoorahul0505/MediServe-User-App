package com.kodebug.mediserveuser.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodebug.mediserveuser.ui.theme.darkGreen
import com.kodebug.mediserveuser.ui.theme.darkGreen2
import com.kodebug.mediserveuser.ui.theme.poppinsFont
import com.kodebug.mediserveuser.ui.theme.softGreen2

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    supportingText: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        textStyle = TextStyle(fontFamily = poppinsFont, fontSize = 18.sp),
        value = value,
        onValueChange = onValueChange,
        label = { label?.let { Text(text = it , fontFamily = poppinsFont,fontSize = 16.sp) } },
        placeholder = { placeholder?.let { Text(text = it, fontFamily = poppinsFont, fontSize = 18.sp) } },
        supportingText = supportingText,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = softGreen2,
            unfocusedBorderColor = softGreen2,
            focusedLabelColor = darkGreen2,
            unfocusedLabelColor = darkGreen,
            cursorColor = darkGreen2,
            unfocusedTextColor = darkGreen,
            focusedTextColor = darkGreen2
        ),
        modifier = Modifier.fillMaxWidth()
    )
}