package com.wotosts.mygreens.common

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    minHeight: Dp = 56.dp
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = minHeight),
        value = text,
        onValueChange = setText,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxHeight(),
                text = placeHolderText,
                color = Gray,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start
            )
        },
        textStyle = MaterialTheme.typography.body2,
        label = {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Text(
                    text = labelText,
                    color = TextGreen,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center
                )
            }
        },
        singleLine = singleLine,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun DashLine(color: Color, height: Dp = 1.dp) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(height)
    ) {

        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LevelChecker(
    icon: ImageVector,
    checkedColor: Color,
    startText: String,
    endText: String,
    level: Int,
    onLevelChanged: (Int) -> Unit,
    iconPadding: Dp = 6.dp
) {
    Row {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Bottom),
            text = startText,
            style = MaterialTheme.typography.overline
        )
        Spacer(Modifier.width(8.dp))
        (1..5).forEach { l ->
            Icon(
                imageVector = icon, contentDescription = "level",
                modifier = Modifier
                    .size(32.dp)
                    .padding(iconPadding)
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_MOVE,
                            MotionEvent.ACTION_DOWN -> {
                                onLevelChanged(l)
                            }
                        }
                        true
                    },
                tint = if (level >= l) checkedColor else Gray
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.Bottom),
            text = endText,
            style = MaterialTheme.typography.overline
        )
    }
}