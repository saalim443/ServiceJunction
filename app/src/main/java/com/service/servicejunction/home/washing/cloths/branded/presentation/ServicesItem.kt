package com.service.servicejunction.home.washing.cloths.branded.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.service.servicejunction.R

@Composable
fun ServicesItem(item: String) {

    var selectedService by remember { mutableStateOf<String?>(null) }

    val isSelected = selectedService == item
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(
                if (isSelected) Color(0xFF215CF3) else Color.Transparent, RoundedCornerShape(4.dp)
            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.padding(2.dp)
                .clickable {
                    selectedService = if (isSelected) null else item
                }
        ) {

            Column {
                Image(
                    painterResource(R.drawable.ic_shirt),
                    contentDescription = "Image Slider",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                )

                Text(
                    text = item,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}