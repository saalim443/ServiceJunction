 package com.service.servicejunction.slot


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R
import com.service.servicejunction.ui.theme.ServiceJunctionTheme


 @Composable
fun ServiceBookingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.white), RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            // Main Text
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Home")
                    }
                    append(" - Noida Sector 63, Near SJM Hospital")
                },
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterStart) // Align text to the start
            )

            // Edit Button in the top-right corner
            IconButton(
                onClick = { /* Handle edit action */ },
                modifier = Modifier.align(Alignment.TopEnd) // Align button to the top-right corner
            ) {
                Icon(
                    imageVector = Icons.Default.Edit, // Material Icon for Edit
                    contentDescription = "Edit Address",
                    tint = MaterialTheme.colorScheme.primary // Icon color
                )
            }
        }


        Spacer(modifier = Modifier.height(0.dp))
        // Select date of service
        Text(
            text = "Select date of service",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Your service will take approx. 2 hrs 20 mins",
            style = MaterialTheme.typography.bodySmall
        )

        // Date Picker Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp) // Adds spacing between items
        ) {
            items(listOf(
                "Sat" to "16",
                "Sun" to "17",
                "Mon" to "18",
                "Tue" to "19",
                "Wed" to "20",
                "Thu" to "21",
                "Fri" to "22"
            )) { (day, date) ->
                DateButton(day = day, date = date, isSelected = date == "17") // Example logic for isSelected
            }
        }


        // Cancellation Policy
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = "Free cancellation till 2 hrs before the booked slot, post that ₹50 chargeable",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Time selection header
        Text(
            text = "Time to start the service",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        // Time slots grid


        TimeSlotsGrid()

        Spacer(modifier = Modifier.height(16.dp))

        // Proceed to Checkout Button
        Button(
            onClick = { /* Handle checkout action */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Proceed to checkout")
        }
    }
}

@Composable
fun DateButton(day: String, date: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Text(text = day, color = if (isSelected) Color.White else Color.Black)
        Text(text = date, color = if (isSelected) Color.White else Color.Black)
    }
}

@Composable
fun TimeSlotsGrid() {
    val times = listOf(
        "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM",
        "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM",
        "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM"
    )

    // High-demand indices
    val highDemandIndices = listOf(2, 5, 8) // Indices for 08:00 AM, 09:30 AM, and 11:00 AM

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(times.size) { index ->
            TimeSlot(
                time = times[index],
                isHighDemand = index in highDemandIndices // Check if index is in the high-demand list
            )
        }
    }
}


@Composable
fun TimeSlot(time: String, isHighDemand: Boolean) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(100.dp) // Fixed width for consistent layout
            .height(60.dp), // Fixed height for the card
        contentAlignment = Alignment.Center
    ) {
        // Card background with border

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center,

        ) {
            // Time text inside the card
            Text(
                text = time,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

        // High demand badge
        if (isHighDemand) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter) // Position at the top center
                    .offset(y = (-5).dp) // Overlap by offsetting upwards
                    .background(
                        color = MaterialTheme.colorScheme.primary, // Amber shade
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp), // Padding for badge
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "High demand",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ServiceBookingScreenPreview() {
    ServiceJunctionTheme {
        ServiceBookingScreen()
    }
}
