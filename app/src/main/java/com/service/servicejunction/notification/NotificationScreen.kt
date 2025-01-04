package com.service.servicejunction.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R

// Data class for Notification Items
data class NotificationItem(
    val icon: Int,
    val title: String,
    val description: String,
    val time: String
)

// Main Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {
    val todayNotifications = listOf(
        NotificationItem(R.drawable.ic_foodicon, "Service Booked Successfully", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1h"),
        NotificationItem(R.drawable.ic_foodicon, "50% Off on Laundry Service", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1h"),
        NotificationItem(R.drawable.ic_foodicon, "Service Review Request", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1h")
    )
    val yesterdayNotifications = listOf(
        NotificationItem(R.drawable.ic_foodicon, "Service Booked Successfully", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1d"),
        NotificationItem(R.drawable.ic_foodicon, "New Paypal Added", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1d"),
        NotificationItem(R.drawable.ic_foodicon, "Service Booked Successfully", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "1d")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification", fontWeight = FontWeight.Bold) },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Gray, MaterialTheme.shapes.medium)
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("2 NEW", color = Color.White, fontSize = 12.sp)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            // Section Header: Today
            item { NotificationHeader(title = "TODAY") }
            items(todayNotifications) { notification ->
                NotificationItemView(notification)
            }

            // Section Header: Yesterday
            item { NotificationHeader(title = "YESTERDAY") }
            items(yesterdayNotifications) { notification ->
                NotificationItemView(notification)
            }
        }
    }
}

// Section Header Composable
@Composable
fun NotificationHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
        Text("Mark all as read", fontSize = 12.sp, color = Color.Gray)
    }
}

// Single Notification Item View
@Composable
fun NotificationItemView(item: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        // Icon
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray, MaterialTheme.shapes.small)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Title and Description
        Column(modifier = Modifier.weight(1f)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(item.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(item.time, fontSize = 12.sp, color = Color.Gray)
            }
            Text(item.description, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

// Preview Function
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}
