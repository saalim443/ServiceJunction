package com.service.servicejunction.home.laundry.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R
import com.service.servicejunction.profile.ProfileScreen

@Composable
fun LaundryDetailItem(
    categoryName: String,
    count: Int,
    iconRes: Int,
    onCountChanged: (Int) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 2.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(4.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = categoryName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .padding(2.dp)
                .clip(CircleShape)
                .padding(8.dp)
                .background(Color.White)
        )

        Text(
            text = categoryName,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
                .weight(0.6f)

        )

        Text(
            text = "-",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
                .clickable {
                    if (count > 0) onCountChanged(count - 1)
                }
                .weight(0.1f)
        )


        Text(
            text = count.toString(),
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
                .weight(0.1f)
        )

        Text(
            text = "+",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
                .clickable {
                    onCountChanged(count + 1)
                }
                .weight(0.1f)
        )
    }
}