package com.service.servicejunction.history

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderSection()
        SortSection()
        OrderListSection()
    }
}

@Composable
fun HeaderSection() {

    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }

        Text(
            text = "My History",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SortSection() {

    val title = listOf("Ongoing", "Completed", "Canceled")
    var selectedTab by remember { mutableIntStateOf(title.size) }

    TabRow(
        selectedTabIndex = selectedTab,
        containerColor = Color.White, // Background color of TabRow
        contentColor = Color.Transparent, // Prevent default content interference
        divider = {}, // Removes the underline
        indicator = {}, // We don't use the default indicator
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(50))
            .padding(1.dp)
    ) {
        title.forEachIndexed { index: Int, tabTitle: String ->
            val isSelected = selectedTab == index
            Tab(
                selected = isSelected,
                onClick = { selectedTab = index },
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if (isSelected) Color(0xFF1CCDEC) else Color.White) // Indicator as background
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                content = {
                    Text(
                        text = tabTitle,
                        color = if (isSelected) Color.White else Color(0xFF1CCDEC), // Text color based on selection
                    )
                }
            )
        }
    }

    when (selectedTab) {
        0 -> OngoingTab()
        1 -> CompletedTab()
        2 -> CanceledTab()
    }
}

@Composable
fun OngoingTab() {

}

@Composable
fun CompletedTab() {

}

@Composable
fun CanceledTab() {

}

data class order(
    val orderName: String,
    val orderId: String,
    val pickupDate: String,
    val deliveredDate: String,
    val itemCount: Int,
    val price: Int,
    val status: String
)

@Composable
fun OrderListSection() {
    val orderList = listOf(
        order(
            "Washing & Dry",
            "Order #642318443",
            "Pickup: 05 Jan 2024",
            "Delivered: 05 Jan 2024",
            23,
            5490,
            "Completed"
        ),

        order(
            "Washing & Dry",
            "Order #642318443",
            "Pickup: 05 Jan 2024",
            "Delivered: 05 Jan 2024",
            23,
            5490,
            "In Progress"
        ),

        order(
            "Washing & Dry",
            "Order #642318443",
            "Pickup: 05 Jan 2024",
            "Delivered: 05 Jan 2024",
            23,
            5490,
            "Completed"
        ),

        order(
            "Washing & Dry",
            "Order #642318443",
            "Pickup: 05 Jan 2024",
            "Delivered: 05 Jan 2024",
            23,
            5490,
            "Canceled"
        ),

        order(
            "Washing & Dry",
            "Order #642318443",
            "Pickup: 05 Jan 2024",
            "Delivered: 05 Jan 2024",
            23,
            5490,
            "Completed"
        ),

        order(
            "Washing & Dry",
            "Order #642318443",
            "Pickup: 05 Jan 2024",
            "Delivered: 05 Jan 2024",
            23,
            5490,
            "Completed"
        )
    )

    LazyColumn {
        items(orderList.size){ index ->
            val order = orderList[index]
            OrderHistoryItem(
                orderName = order.orderName,
                orderId = order.orderId,
                pickupDate = order.pickupDate,
                deliveredDate = order.deliveredDate,
                itemCount = order.itemCount,
                price = order.price,
                status =  order.status
            )
        }
    }
}
