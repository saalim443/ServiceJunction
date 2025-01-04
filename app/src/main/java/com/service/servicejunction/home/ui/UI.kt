package com.service.servicejunction.home.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.CategoryActivity
import com.service.servicejunction.R
import com.service.servicejunction.common.CenteredText
import com.service.servicejunction.home.AddBanner
import com.service.servicejunction.home.data.GridItem

@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "Samantha James",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Noida Sector 2, Near Red FM",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(contentAlignment = Alignment.TopEnd) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .offset(x = 4.dp, y = (-2).dp)
            )
        }
    }
}


@Composable
fun GridItemView(item: GridItem) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) } // State to control dialog visibility

    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp) // Reduced padding around the card
    ) {
        Box(
            modifier = Modifier
                .height(90.dp) // Slightly reduced height
                .fillMaxWidth()
                .clickable {
                    if(item.title.equals("Washing")){
                        showDialog = true // Show the dialog
                    }
                    else{
                        CategoryActivity.services = item.title
                        val intent = Intent(context, CategoryActivity::class.java)
                        context.startActivity(intent)
                    }
                    }

                .background(
                    Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ) // Reduced corner radius
        ) {
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = item.imageRes.toString(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)) // Match corner radius
            )
        }
        Spacer(modifier = Modifier.height(4.dp)) // Reduced spacing between image and text

        CenteredText(item.title)
//        Text(
//            text = item.title,
//            fontSize = 13.sp, // Slightly smaller font size
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(vertical = 2.dp) // Added a little padding for better alignment
//        )
//        Text(
//            text = subtitle, // Ensure subtitle is displayed
//            fontSize = 11.sp, // Slightly smaller font size
//            color = Color.Gray,
//            modifier = Modifier.padding(vertical = 2.dp)
//        )
    }

    // Show dialog when "Washing" is clicked
    if (showDialog) {
        WashingDialog(onDismiss = { showDialog = false })
    }
}


@Composable
fun WashingDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Select Type of Clothes") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                SectionItem(title = "Normal Clothes")
                Divider()
                SectionItem(title = "Branded Clothes")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Close")
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun SectionItem(title: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                CategoryActivity.services = title
                val intent = Intent(context, CategoryActivity::class.java)
                context.startActivity(intent)
            }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
fun GridItemViewII(item: GridItem) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp) // Reduced padding around the card
    ) {
        Box(
            modifier = Modifier
                .size(50.dp) // Set both height and width to 50dp
                .clickable {
                    val intent = Intent(context, CategoryActivity::class.java)
                    context.startActivity(intent)
                }
                .background(
                    Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ) // Reduced corner radius
        ) {
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)) // Match corner radius
            )
        }
        Spacer(modifier = Modifier.height(4.dp)) // Reduced spacing between image and text

        CenteredText(item.title)
//        Text(
//            text = item.title,
//            fontSize = 13.sp, // Slightly smaller font size
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(vertical = 2.dp) // Added a little padding for better alignment
//        )
//        Text(
//            text = subtitle, // Ensure subtitle is displayed
//            fontSize = 11.sp, // Slightly smaller font size
//            color = Color.Gray,
//            modifier = Modifier.padding(vertical = 2.dp)
//        )
    }
}



@Composable
fun SeasonalPromotions() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Seasonal Promotions", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(
                listOf(
                    Triple(
                        "Winter Sale",
                        "Up to 50% off on selected items",
                        R.drawable.kitech_1
                    ),
                    Triple("Holiday Specials", "Limited holiday deals", R.drawable.kitech_1)
                )
            ) { promotion ->
                AddBanner(
                    title = promotion.first,
                    subtitle = promotion.second,
                    imageRes = promotion.third
                )
            }
        }
    }
}


@Composable
fun CustomerTestimonials() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "What Our Customers Say", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(
                listOf(
                    "Amazing service and great products!",
                    "Fast excellent customer support.",
                    "High-quality items at great prices!"
                )
            ) { testimonial ->
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(16.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = testimonial,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}