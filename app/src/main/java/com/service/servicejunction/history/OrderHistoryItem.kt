package com.service.servicejunction.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderHistoryItem(
    orderName: String,
    orderId: String,
    pickupDate: String,
    deliveredDate: String,
    itemCount: Int,
    price: Int,
    status: String
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    text = orderName,
                    fontSize = 18.sp,
                    color = Color(0xFF1CCDEC),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = orderId,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold

                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    text = pickupDate,
                    fontSize = 14.sp
                )

                Text(
                    text = deliveredDate,
                    fontSize = 14.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    text = "$itemCount items",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "$$price",
                    fontSize = 18.sp,
                    color = Color(0xFF1CCDEC),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = status,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .background(
                        when(status){
                            "Completed" -> Color(0xFF29AD30)
                            "In Progress" -> Color(0xFFE5DB0F)

                            else -> Color(0xFFAD1A10)
                        }, RoundedCornerShape(16.dp))
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            )
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun OrderHistoryItemPreview() {
//    OrderHistoryItem()
//}