package com.service.servicejunction.home.washing.cloths.branded.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WashClothDetailItem(
    item: String,
    count: Int,
    viewModel: BrandWashClothViewModel,
    detailId: Int,
    brandId: Int,
    price: Double,
    iconRes: Int
) {
    var currentCount  = count

    Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
    .padding(8.dp)
    .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = item,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E7FF))
        )

        Text(
            text = item,
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterVertically)

        )

        Text(
            text = "-",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
                .clickable {
                    if (currentCount > 0) {
                        currentCount--
                        onCountChanged(detailId,currentCount, viewModel, brandId, item, price, iconRes)
                    }
                }
        )


        Text(
            text = count.toString(),
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Text(
            text = "+",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
                .clickable {
                    currentCount++
                    onCountChanged(
                        detailId, currentCount, viewModel,
                        brandId, item, price, iconRes
                    )
                }
        )
    }
}

fun onCountChanged(detailId:Int, currentCount: Int, viewModel: BrandWashClothViewModel, companyId: Int, itemName: String, price: Double,
                   iconRes: Int) {
    viewModel.updateDetailCount(detailId, currentCount, companyId, itemName, price, iconRes)
}

