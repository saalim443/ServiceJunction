package com.service.servicejunction.home.washing.car


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarServiceScreen(selectedBrand: String?, selectedSubCategory: String) {
    // State for selected service
    var selectedService by remember { mutableStateOf("Interior Cleaning") }

    Scaffold(
        topBar = {
            // Top App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//                Icon(
//                    imageVector = Icons.Default.ArrowBack,
//                    contentDescription = "Back",
//                    modifier = Modifier
//                        .size(24.dp)
//                        .clickable { /* Handle Back */ }
//                )
//                Icon(
//                    imageVector = Icons.Default.FavoriteBorder,
//                    contentDescription = "Favorite",
//                    modifier = Modifier
//                        .size(24.dp)
//                        .clickable { /* Handle Favorite */ }
//                )
            }
        },
        bottomBar = {
            // Book Now Button
            Button(
                onClick = { /* Handle Booking */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000000)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Book now", color = Color.White, fontSize = 16.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Service Image
            Image(
                painter = painterResource(id = R.drawable.carwash),
                contentDescription = "Service Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Title, Rating, and Location
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Brand: ${selectedBrand}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${selectedSubCategory} 420 Ace charging station, New York, NY 100013, USA",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Rating
                    Text(text = "⭐ 4.5", fontSize = 14.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Handle Direction */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEAF4FE)),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Get direction", color = Color(0xFF0056B3))
                    }
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            // About Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "About",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Lorem ipsum dolor sit amet consectetur. Risus sed ultrices libero in nibh netus justo porttitor. Enim aliquanditellus sed pellentesque sagittis nulla. Mattis lorem nunc vel sem fermentum arcu scelerisque pharetra vitae.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Services Section
            Text(
                text = "Services",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ServiceCard(
                    title = "Car body wash",
                    price = "$50",
                    isSelected = selectedService == "Car body wash",
                    onClick = { selectedService = "Car body wash" }
                )
                ServiceCard(
                    title = "Interior Cleaning",
                    price = "$160",
                    isSelected = selectedService == "Interior Cleaning",
                    onClick = { selectedService = "Interior Cleaning" }
                )
                ServiceCard(
                    title = "Engine Wash",
                    price = "$100",
                    isSelected = selectedService == "Engine Wash",
                    onClick = { selectedService = "Engine Wash" }
                )
            }
        }
    }
}

@Composable
fun ServiceCard(title: String, price: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .clickable { onClick() }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Color(0xFF000000) else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFFEAF4FE), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shirt),
                contentDescription = "Service Icon",
                tint = Color(0xFF000000)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "", fontSize = 14.sp, color = Color.Gray)
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CarServiceScreenPreview() {
//    CarServiceScreen(selectedBrand?.name, selectedSubCategory)
//}
