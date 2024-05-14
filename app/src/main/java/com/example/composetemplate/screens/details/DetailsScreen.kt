package com.example.composetemplate.screens.details

import android.annotation.SuppressLint
import android.telecom.Call.Details
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composetemplate.data.Resource
import com.example.composetemplate.screens.main.MainList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    id: Int,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {

    LaunchedEffect(id) {
        viewModel.getDetail(id)
    }

    val entity by viewModel.detail.collectAsState()

    Scaffold(
        bottomBar = {},
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.DarkGray
        ) {
            when(entity){
                is Resource.Error -> {
                    Text(text = "Error: ${(entity as Resource.Error).message}")
                }
                is Resource.Loading -> CircularProgressIndicator()
                is Resource.Success ->{
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Card(
                            modifier = modifier
                                .size(width = 300.dp, height = 250.dp)
                                .padding(10.dp),
                            shape = RoundedCornerShape(size = 20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Black),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                        ) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Column (modifier = modifier.padding(
                                    start = 16.dp,
                                    top = 10.dp,
                                    bottom = 16.dp,
                                    end = 10.dp
                                )){
                                    Text(text = "UserId: ${entity.data?.userId}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Title: ${entity.data?.title}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Completed: ${entity.data?.completed}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge)

                                }
                            }
                        }
                    }

                }
            }
        }
    }

}