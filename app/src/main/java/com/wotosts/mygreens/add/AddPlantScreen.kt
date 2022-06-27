package com.wotosts.mygreens.add

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.net.URI
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wotosts.mygreens.R
import com.wotosts.mygreens.common.CommonTextField
import com.wotosts.mygreens.ui.theme.*

@Composable
fun AddPlantScreen(viewModel: AddPlantViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState().value
    val scrollState = rememberScrollState()

    MyGreensTheme {
        Scaffold {
            Column(
                Modifier
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
            ) {
                AddPlantAppbar(context = context)
                BasicPlantInfo(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 22.dp),
                    uiState = uiState
                )
                AdditionalPlantInfo(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /* add plant */ },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = Shapes().small,
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Text("저장하기", color = TextGreen, style = MaterialTheme.typography.button)
                }
            }
        }
    }
}

@Composable
fun AddPlantAppbar(context: Context) {
    Row(modifier = Modifier.height(56.dp), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { (context as? AppCompatActivity)?.finish() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp),
                tint = Color.Black
            )
        }
        Text(
            text = "식물 추가하기",
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 48.dp)
        )
    }
}

@Composable
fun BasicPlantInfo(modifier: Modifier = Modifier, uiState: AddPlantUiState) {
    val (name, setName) = remember { mutableStateOf("") }
    val (date, setDate) = remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier.size(150.dp),
            shape = Shapes.medium.copy(CornerSize(16.dp)),
            elevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = rememberAsyncImagePainter(model = URI(uiState.imageUrl)),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = LightGray)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.ic_potted_plant
                    ),
                    contentDescription = "Empty image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    tint = Gray
                )
                IconButton(onClick = { /* open camera or gallery */ }) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Secondary)
                    )
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add picture")
                }
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        CommonTextField(
            text = name,
            setText = setName,
            labelText = "식물 이름",
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.height(12.dp))
        CommonTextField(
            text = date,
            setText = setDate,
            labelText = "처음 만난 날",
            trailingIcon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date picker")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            )
        )
    }
}

@Composable
fun AdditionalPlantInfo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "추가 정보 입력", style = MaterialTheme.typography.body2)
        IconButton(
            onClick = { /* expand or collapse additional info */ },
            modifier = Modifier.size(
                24.dp
            )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Open/Close additional info"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPlantScreen() {
    AddPlantScreen()
}