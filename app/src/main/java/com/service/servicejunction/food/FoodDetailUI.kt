package com.service.servicejunction.food
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.service.servicejunction.R


@Composable
fun FoodDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Food Image
        Image(
            painter = painterResource(id = R.drawable.foodall), // Replace with actual image resource
            contentDescription = "Food Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Food Title and Price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,

        ) {
            Text(text = "Special Pizza", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Price : $55", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colorResource(R.color.black))
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Restaurant and Location
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.LocationOn, contentDescription = "Location", tint = Color.Gray)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text = "Lorem Restaurant, Vegas", color = Color.Gray, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Food Information
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "500gm", color = Color.Gray)
            Text(text = "2 person", color = Color.Gray)
            Text(text = "200 Calories", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add-ons (Checkbox options)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OptionCheckbox(text = "Extra Cheese")
            OptionCheckbox(text = "Non-veg")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OptionCheckbox(text = "Extra Toppings")
            OptionCheckbox(text = "Fresh Pan")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Time to prepare
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Time to prepare", fontSize = 14.sp)
            Text(text = "35 Minutes", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ratings
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Ratings (50k reviews)", fontSize = 14.sp)
            Row {
                repeat(5) {
                    Icon(Icons.Filled.Star, contentDescription = "Star", tint = Color(0xFFFFA726))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add to Cart Button
        Button(
            onClick = {    navController.navigate("foodsubscription")  },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.black)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "CHOOSE PRIMIUM ", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

    }
}

@Composable
fun OptionCheckbox(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
            .clickable { /* Handle checkbox click */ }
    ) {
        Checkbox(
            checked = false, // Bind to state if needed
            onCheckedChange = { /* Handle checkbox state change */ }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, fontSize = 14.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFoodDetailScreenScreen() {

    FoodDetailScreen(navController = rememberNavController())

}


@Composable
fun SearchBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F1F1))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Search your favorite food",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu Icon",
            modifier = Modifier.padding(8.dp)
        )
    }
}
