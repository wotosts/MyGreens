package com.wotosts.mygreens.ui.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wotosts.mygreens.common.MyGreensAppBar
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.wotosts.mygreens.R
import com.wotosts.mygreens.ui.theme.*
import java.net.URI
import java.text.SimpleDateFormat

@Composable
fun PlantDetailScreen(viewModel: PlantDetailViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState().value
    val fabClicked = remember { mutableStateOf(false) }

    BackHandler(enabled = fabClicked.value) {
        fabClicked.value = false
    }

    MyGreensTheme {
        Scaffold(floatingActionButton = {
            FloatingButtons(fabClicked)
        }) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                PlantInfo(
                    uiState = uiState,
                    modifier = Modifier
                        .padding(vertical = 22.dp, horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }

            // ToDo add plant diary

            MyGreensAppBar(context = context, title = uiState.plant.name) {
                IconButton(
                    onClick = { /* edit */ },
                    modifier = Modifier
                        .size(56.dp)
                        .padding(8.dp),
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit plant")
                }
            }

            if (fabClicked.value) Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.8f))
                    .clickable(
                        indication = null, // disable ripple effect
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { }
                    )
            )
        }
    }
}


@Composable
fun PlantInfo(modifier: Modifier = Modifier, uiState: PlantDetailUiState) {
    val plant = uiState.plant

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier.size(150.dp),
            shape = Shapes.medium.copy(CornerSize(16.dp)),
            elevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = rememberAsyncImagePainter(model = URI(plant.image)),
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
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        val format = SimpleDateFormat("M월 d일, yyyy년 ~ ")
        val date =
            format.format(plant.firstDate) + if (plant.deathDate != null) format.format(
                plant.deathDate
            ) else ""
        Text(
            text = date,
            color = TextGreen,
            style = MaterialTheme.typography.overline
        )

        Spacer(modifier = Modifier.height(22.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            TopIconText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_water_drop),
                text = uiState.plant.waterLevel.toString(),
                iconColor = Water
            )
            TopIconText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_wb_sunny),
                text = uiState.plant.solarLevel.toString(),
                iconColor = Solar
            )
            TopIconText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_pot),
                text = "분갈이 +n일",
                iconColor = Pot
            )
            TopIconText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_nutrient),
                text = "영양제 +n일",
                iconColor = Nutrient
            )
        }

        if (uiState.plant.description.isNotBlank()) {
            Spacer(modifier = Modifier.height(28.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(
                        width = 1.dp,
                        color = SecondaryDark,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(
                    text = plant.description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .defaultMinSize(minHeight = 130.dp)
                )
            }
        }
    }
}

@Composable
fun FloatingButtons(fabClicked: MutableState<Boolean>) {
    Column(horizontalAlignment = Alignment.End) {
        if (fabClicked.value) {
            FloatingActionButtonWithText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_nutrient),
                text = "오늘 영양분을 주었어요",
                iconDescription = "Fertilizing",
                onClick = { /*TODO*/ },
                color = Nutrient
            )
            Spacer(modifier = Modifier.height(14.dp))
            FloatingActionButtonWithText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_pot),
                text = "오늘 분갈이를 했어요",
                iconDescription = "Transplanting",
                onClick = { /*TODO*/ },
                color = Pot
            )
            Spacer(modifier = Modifier.height(14.dp))
            FloatingActionButtonWithText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_water_drop),
                text = "오늘 물을 주었어요",
                iconDescription = "Watering",
                onClick = { /*TODO*/ },
                color = Water
            )
            Spacer(modifier = Modifier.height(14.dp))
            FloatingActionButtonWithText(
                icon = ImageVector.vectorResource(id = R.drawable.ic_diary_add),
                text = "기록 남기기",
                iconDescription = "Write Diary",
                onClick = { /*TODO*/ },
                color = Color.Black,
                strokeColor = SecondaryDark
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        FloatingActionButton(
            onClick = {
                fabClicked.value = !fabClicked.value
            },
            backgroundColor = SecondaryDark,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Open activities",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun FloatingActionButtonWithText(
    icon: ImageVector,
    text: String,
    iconDescription: String,
    onClick: () -> Unit,
    color: Color,
    strokeColor: Color? = null
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 14.dp)
        )

        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(end = 4.dp)
                .size(40.dp)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = Shapes.large.copy(CornerSize(50)),
                elevation = 4.dp,
                color = Color.White,
                border = BorderStroke(2.dp, strokeColor ?: color)
            ) {}

            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun TopIconText(icon: ImageVector, text: String, iconColor: Color) {
    Column(modifier = Modifier.width(66.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = iconColor,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(text = text, style = MaterialTheme.typography.caption.copy(fontSize = 10.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlantDetailScreen() {
    PlantDetailScreen()
}