package com.service.servicejunction.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.service.servicejunction.R


//odersummmury
@Composable
fun OrderSummaryScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Order Summary", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Help", color = colorResource(R.color.black), fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Restaurant Info
        Column {
            Text(text = "Punjabi Rasoi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "Deals in Food", color = Color.Gray)
            Text(text = "Court More, Opposite Ordental Hospital, Asansol", color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Download Invoice",
                color = colorResource(R.color.black),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // Action to perform on click, such as navigating or downloading
                }
            )
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Delivery Address
        Column {
            Text(text = "Delivered to", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Home, contentDescription = "Home")
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "Saumik Sarkar, 8250292291", fontSize = 14.sp)
                    Text(text = "Tirupati Complex Court More, Asansol", fontSize = 14.sp, color = Color.Gray)
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Delivery Status
        Column {
            Text(
                text = "Delivered Successfully",
                color = colorResource(R.color.black),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Your order has been delivered on June 23  02:50 PM by Mandeep Singh",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Bill Details
        Column {
            Text(
                text = "Bill Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .background(colorResource(R.color.black))
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            BillItemRow(name = "Chicken Biryani", quantity = "x1", price = "₹2550.00")
            BillItemRow(name = "Taxes", quantity = "", price = "₹50.00")
            BillItemRow(name = "Delivery fee", quantity = "", price = "₹20.00")
            BillItemRow(name = "Instant order", quantity = "", price = "₹30.00")

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            BillItemRow(name = "Pay by COD (paid)", quantity = "", price = "₹2630.00", bold = true)
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Reorder Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Reorder", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colorResource(
                R.color.black)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.food2), // Replace with actual image resource
                contentDescription = "Reorder Image",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Keep enjoying your food", fontSize = 20.sp, color = Color.Black)
        }
    }
}

@Composable
fun BillItemRow(name: String, quantity: String, price: String, bold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$name $quantity",
            fontSize = 14.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = price,
            fontSize = 14.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewOrderSummaryScreenScreen() {

    OrderSummaryScreen(  navController = rememberNavController() )// Provide a dummy NavController for preview)

}