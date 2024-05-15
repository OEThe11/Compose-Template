package com.example.composetemplate.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composetemplate.data.Resource
import com.example.composetemplate.database.StandardEntity
import com.example.composetemplate.navigation.StandardScreens
import com.example.composetemplate.widgets.StandardCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {

    val standardItem by viewModel.standardStatus.collectAsState()

    LaunchedEffect(true) {
        viewModel.getStandardList()
    }

    Scaffold(
        bottomBar = {}
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.DarkGray
        ) {
          when(standardItem){
              is Resource.Error -> {
                  Text(text = "Error: ${(standardItem as Resource.Error).message}")
              }
              is Resource.Loading -> CircularProgressIndicator()
              is Resource.Success ->{
                  MainList(list = standardItem, navController = navController)
              }
          }
        }
    }

}



@Composable
fun MainList(list: Resource<List<StandardEntity>>, navController: NavController){
    LazyColumn(modifier = Modifier.padding(top = 30.dp)){
        items(list.data!!.size){item ->
            StandardCard(
                standardItem = list.data[item],
                modifier = Modifier.clickable {
                    navController.navigate("${StandardScreens.DetailsScreen.name}/${list.data[item].id}")
                }
            )
        }
    }
}