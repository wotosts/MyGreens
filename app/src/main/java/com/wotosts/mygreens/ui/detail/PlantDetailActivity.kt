package com.wotosts.mygreens.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels

class PlantDetailActivity : AppCompatActivity() {
    private val viewModel: PlantDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlantDetailScreen(viewModel)
        }
    }

    companion object {
        const val PLANT = "plant"
    }
}