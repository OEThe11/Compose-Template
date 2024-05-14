package com.example.composetemplate.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composetemplate.database.StandardEntity

@Composable
fun StandardCard(
    standardItem: StandardEntity,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
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
                bottom = 10.dp,
                end = 10.dp
            )){
                Text(text = "UserId: ${standardItem.userId}",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge)
                Text(text = "Title: ${standardItem.title}",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge)
                Text(text = "Completed: ${standardItem.completed}",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge)

            }
        }
    }
}