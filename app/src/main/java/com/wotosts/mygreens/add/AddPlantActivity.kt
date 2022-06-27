package com.wotosts.mygreens.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels

class AddPlantActivity : AppCompatActivity() {
    private val viewModel: AddPlantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddPlantScreen(viewModel)
        }
    }
}