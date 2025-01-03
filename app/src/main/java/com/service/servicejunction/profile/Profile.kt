package com.service.servicejunction.profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R


@Composable
fun ProfileScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
//        item { TopAppBar() }
        item { ProfileSection() }
        item { HistorySection() }
        item { UserProfileScreen() }
        // You can add more items as needed
    }
}



@Composable
fun ProfileSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Avatar
//        Box(
//            modifier = Modifier
//                .size(80.dp)
//                .clip(CircleShape)
//                .background(Color(0xFF000000))
//        ) {
//            Text(
//                text = "S",
//                color = Color.White,
//                fontSize = 32.sp,
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }

        Column(
            modifier = Modifier
                .padding(start = 0.dp)
        ) {
            Text(
                text = "saalim malik",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "saalimmalik.official@gmail.com",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun HistorySection() {
    Column(
        modifier = Modifier.padding(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recenct History Booking ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { }) {
                Text("View all", color = Color.Black)
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(3) { index ->
                recentHistory()
            }
        }
    }
}

@Composable
fun recentHistory() {
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(180.dp)
            .padding(8.dp),  // Add padding around the card
        shape = RoundedCornerShape(16.dp)  // Rounded corners
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.kitech_1), // Replace with your image resource
                    contentDescription = "title",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Add spacing between the image and the title

            Text(
                text = "Plumber Service", // Title text
                fontSize = 16.sp, // Larger font size for the title
                fontWeight = FontWeight.Bold, // Make the title bold
                color = Color.Black, // Dark text for better readability
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp)) // Extra spacing for clarity

            Text(
                text = "Looking for a plumber to fix the leaking pipe.", // Description text
                fontSize = 12.sp,
                color = Color.Gray, // Lighter color for the description
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 8.dp) // Padding for the bottom text
            )
        }
    }
}





@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        // Quick Actions Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickActionItem(
                icon = Icons.Default.Info,
                text = "My\nbookings"
            )
            QuickActionItem(
                icon = Icons.Default.Refresh,
                text = "Native\ndevices"
            )
            QuickActionItem(
                icon = Icons.Default.KeyboardArrowUp,
                text = "Help &\nsupport"
            )
        }

        // Menu Items
        MenuListItem(icon = Icons.Default.List, text = "My Plans")
        MenuListItem(icon = Icons.Default.AccountBox, text = "Wallet")
        MenuListItem(icon = Icons.Default.Star, text = "Plus membership")
        MenuListItem(icon = Icons.Default.Close, text = "My rating")
        MenuListItem(icon = Icons.Default.LocationOn, text = "Manage addresses")
        MenuListItem(icon = Icons.Default.Refresh, text = "Manage payment methods")
        MenuListItem(icon = Icons.Default.Settings, text = "Settings")
        MenuListItem(icon = Icons.Default.Info, text = "About UC")

        // Referral Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    androidx.compose.material3.Text(
                        text = "Refer & earn ₹100",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    androidx.compose.material3.Text(
                        text = "Get ₹100 when your friend completes\ntheir first booking",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    Icons.Default.AccountBox,
                    contentDescription = "Gift",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

@Composable
private fun QuickActionItem(
    icon: ImageVector,
    text: String
) {
    Card(
        modifier = Modifier.width(108.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            androidx.compose.material3.Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
private fun MenuListItem(
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            androidx.compose.material3.Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Icon(
            Icons.Default.KeyboardArrowUp,
            contentDescription = "Navigate",
            modifier = Modifier.size(24.dp)
        )
    }
}




@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PreviewProfileScreen() {
    MaterialTheme {
        ProfileScreen()
    }
}