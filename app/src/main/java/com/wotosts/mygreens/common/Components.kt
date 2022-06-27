package com.wotosts.mygreens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wotosts.mygreens.ui.theme.Gray
import com.wotosts.mygreens.ui.theme.TextGreen

@Composable
fun CommonTextField(
    text: String,
    setText: (String) -> Unit,
    placeHolderText: String = "",
    labelText: String = "",
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        value = text,
        onValueChange = setText,
        placeholder = {
            Text(
                text = placeHolderText,
                color = Gray,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center
            )
        },
        textStyle = MaterialTheme.typography.body2,
        label = {
            Text(
                text = labelText,
                color = TextGreen,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center
            )
        },
        singleLine = singleLine,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions
    )
}