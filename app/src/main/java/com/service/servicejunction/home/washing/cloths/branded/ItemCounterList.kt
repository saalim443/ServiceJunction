//package com.service.servicejunction.home.washing.cloths
//

package com.service.servicejunction.home.washing.cloths.branded

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import com.service.servicejunction.R
import com.service.servicejunction.home.washing.cloths.branded.domain.local.Item

@Composable
fun ItemCounterList() {
    val items = listOf(
        Item("Pant", 3.0, R.drawable.ic_shirt),
        Item("T-Shirt", 3.0, R.drawable.ic_shirt),
        Item("Shirt", 2.5, R.drawable.ic_shirt),
        Item("Jeans", 3.5, R.drawable.ic_shirt),
        Item("Short", 4.0, R.drawable.ic_shirt),
        Item("Jacket", 4.5, R.drawable.ic_shirt)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items.forEach { item ->
            ItemRow(item = item)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    var quantity by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.iconRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E7FF)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
//            Text(
//                text = "$${item.price} /pcs",
//                fontSize = 14.sp,
//                color = Color(0xFF7C83FD)
//            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { if (quantity > 0) quantity-- }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Decrease")
            }
            Text(
                text = "$quantity",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            IconButton(onClick = { quantity++ }) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Increase")
            }
        }
    }
}

//data class Item(val name: String, val price: Double, val iconRes: Int)

@Preview(showBackground = true)
@Composable
fun ItemCounterListPreview() {
    MaterialTheme {
        ItemCounterList()
    }
}
