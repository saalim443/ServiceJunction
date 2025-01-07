package com.service.servicejunction.home.laundry.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R

@Composable
fun LaundryGridItem (
    item: String,
    icon: Int,
    selectedService: String?,
    onSelectService: (String) -> Unit
){


    val isSelected = selectedService == item
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(
                if (isSelected)
                    Color.Black
                else
                    Color.Transparent,
                RoundedCornerShape(4.dp)
            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.padding(2.dp)
                .clickable {
//                    selectedService = if (isSelected) null else item
                    onSelectService(item)
                }
                .size(110.dp)
        ) {

            Column {
                Image(
                    painterResource(id = icon),
                    contentDescription = "Image Slider",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(height = 80.dp, width = 110.dp)
//                        .clip(CircleShape)
                )

                Text(
                    text = item,
                    fontSize = 12.sp,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(4.dp)
                )
            }
        }
    }
}