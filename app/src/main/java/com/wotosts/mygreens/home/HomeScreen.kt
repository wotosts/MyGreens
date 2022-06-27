package com.wotosts.mygreens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.wotosts.mygreens.model.Plant
import com.wotosts.mygreens.ui.theme.MyGreensTheme
import com.wotosts.mygreens.ui.theme.PrimaryDark
import com.wotosts.mygreens.ui.theme.SecondaryDark
import java.net.URI
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wotosts.mygreens.add.AddPlantActivity
import com.wotosts.mygreens.utils.startActivity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = SecondaryDark,
        sheetPeekHeight = 300.dp,
        sheetContent = {
            PlantList(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
                viewModel = viewModel
            )
        }) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            HomeTitle(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            PlantNeedWaterList(viewModel = viewModel)
        }
    }
}

@Composable
fun HomeTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "물을 주셨나용?"
        )
        Text(text = "식물들에게 물이 필요해요!")
    }
}

@Composable
fun PlantNeedWaterList(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(viewModel.plantNeedWaters) {
            PlantNeedWaterItem(plant = it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantList(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "내 식물들~~~")
            IconButton(
                onClick = {
                    context.startActivity<AddPlantActivity>()
                },
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add plant")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))
        // 검색창
        // 구분
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterVertically
            ),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(viewModel.plants) { plant ->
                PlantItem(plant = plant)
            }
        }
    }
}

@Composable
fun PlantNeedWaterItem(modifier: Modifier = Modifier, plant: Plant) {
    Card(
        modifier = modifier
            .width(80.dp),
        backgroundColor = PrimaryDark
    ) {
        Column(modifier = Modifier.background(color = Color.White)) {
            Box(modifier = Modifier.aspectRatio(1f)) {
                Image(
                    painter = rememberAsyncImagePainter(model = URI(plant.image)),
                    contentDescription = "",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .aspectRatio(1f)
                )

                IconButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Blue.copy(alpha = 0.5f)),
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "")
                }
            }

            Text(
                text = plant.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PlantItem(modifier: Modifier = Modifier, plant: Plant) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 0.75f),
        backgroundColor = PrimaryDark
    ) {
        Column(modifier = Modifier.background(color = Color.White)) {
            Box(contentAlignment = Alignment.TopEnd) {
                Image(
                    painter = rememberAsyncImagePainter(model = URI(plant.image)),
                    contentDescription = "",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .aspectRatio(1f)
                )

                IconButton(modifier = Modifier.size(48.dp), onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
                }
            }

            Text(
                text = plant.name, modifier = Modifier
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomScreen() {
    MyGreensTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlantItem() {
    MyGreensTheme {
        PlantItem(
            plant = Plant(
                id = 0,
                name = "식물1"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlantNeedWaterItem() {
    MyGreensTheme {
        PlantNeedWaterItem(
            plant = Plant(
                id = 0,
                name = "식물1"
            )
        )
    }
}