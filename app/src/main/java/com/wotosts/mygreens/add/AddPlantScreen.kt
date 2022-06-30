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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import java.net.URI
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.material.slider.RangeSlider
import com.wotosts.mygreens.R
import com.wotosts.mygreens.common.CommonTextField
import com.wotosts.mygreens.common.DashLine
import com.wotosts.mygreens.common.LevelChecker
import com.wotosts.mygreens.ui.theme.*
import com.wotosts.mygreens.utils.dp

@Composable
fun AddPlantScreen(viewModel: AddPlantViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState().value
    val scrollState = rememberScrollState()

    MyGreensTheme {
        Scaffold {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
                    .verticalScroll(scrollState)
            ) {
                BasicPlantInfo(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 22.dp),
                    uiState = uiState,
                    viewModel = viewModel
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 22.dp),
                ) {
                    DashLine(color = SecondaryDark)
                }
                AdditionalPlantInfo(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    uiState = uiState,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(18.dp))

                Box(
                    modifier = Modifier
                        .height(96.dp)
                )
            }

            AddPlantAppbar(context = context)

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.White.copy(0.8f),
                                    Color.White
                                )
                            )
                        )
                ) { }

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
    Row(
        modifier = Modifier
            .height(56.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
fun BasicPlantInfo(
    modifier: Modifier = Modifier,
    uiState: AddPlantUiState,
    viewModel: AddPlantViewModel
) {
    // todo date picker
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
            text = uiState.name,
            setText = { viewModel.updateName(it) },
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdditionalPlantInfo(
    modifier: Modifier = Modifier,
    uiState: AddPlantUiState,
    viewModel: AddPlantViewModel
) {
    val (description, setDescription) = remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "추가 정보 입력", style = MaterialTheme.typography.body2)
        IconButton(
            onClick = {
                if (uiState.additionalInfoExpanded) viewModel.closeAdditionalInfo()
                else viewModel.expandAdditionalInfo()
            },
            modifier = Modifier.size(
                24.dp
            )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Open/Close additional info",
                modifier = Modifier.rotate(if (uiState.additionalInfoExpanded) 180f else 0f)
            )
        }
    }

    if (uiState.additionalInfoExpanded) {
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(18.dp))
            AdditionalInfoItem(title = "물") {
                LevelChecker(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_water_drop),
                    checkedColor = Water,
                    startText = "가끔",
                    endText = "매일",
                    level = uiState.waterLevel,
                    onLevelChanged = { viewModel.updateWaterLevel(it) }
                )
            }

            AdditionalInfoItem(title = "햇빛") {
                LevelChecker(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_wb_sunny),
                    checkedColor = Solar,
                    startText = "음지",
                    endText = "양지",
                    level = uiState.solarLevel,
                    onLevelChanged = { viewModel.updateSolarLevel(it) },
                    iconPadding = 5.dp
                )
            }

            AdditionalInfoItem(title = "온도") {
                AndroidView(
                    factory = { context ->
                        RangeSlider(context).apply {
                            setPaddingRelative(
                                paddingStart,
                                paddingTop,
                                16.dp.value.toInt(),
                                paddingBottom
                            )
                            valueFrom = 0f
                            valueTo = 40f
                            stepSize = 1f
                            values = uiState.tempRange
                            trackActiveTintList =
                                ContextCompat.getColorStateList(context, R.color.secondary)!!
                            thumbRadius = dp(8)

                            addOnChangeListener(RangeSlider.OnChangeListener { _, _, fromUser ->
                                if (fromUser) viewModel.updateTempRange(values)
                            })
                        }
                    })
            }

            CommonTextField(
                text = uiState.description,
                setText = { viewModel.updateDescription(it) },
                labelText = "기타 사항",
                minHeight = 180.dp,
                singleLine = false
            )
        }
    }
}

@Composable
fun AdditionalInfoItem(title: String, slot: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier
                .weight(0.8f)
        ) {
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                slot()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddPlantScreen() {
    AddPlantScreen()
}