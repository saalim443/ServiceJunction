package com.service.servicejunction

import NormalWashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.service.servicejunction.bottomsheets.BottomSheetDetailSubCategory
import com.service.servicejunction.common.CenteredText
import com.service.servicejunction.home.laundry.presentation.LaundryScreen
import com.service.servicejunction.home.washing.car.WashingCarScreen
import com.service.servicejunction.home.washing.cloths.branded.presentation.BrandWashClothScreen
import com.service.servicejunction.map.LocationPickerScreen
import com.service.servicejunction.ui.theme.ServiceJunctionTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryActivity : ComponentActivity() {
    companion object{
        var services=""
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ServiceJunctionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Top, // Aligns content vertically at the top
                        horizontalAlignment = Alignment.CenterHorizontally // Center aligns horizontally
                    ) {
                        val navController = rememberNavController()
                        NavHost(navController, startDestination = getStartDestination()) {
//
//                    composable("introscreen") {
//                        IntroScreen(onNavigateToSignin = { navController.navigate("signin") })
//                    }
//
//                    composable("signin") {
//                        Signin(
//                            onLoginClick = { navController.navigate("otp") },
//                            onForgotPasswordClick = { navController.navigate("forgot") } // Navigate to ForgotScreen
//                        )
//                    }
//
//
//                    composable("otp") {
//                        OtpScreen(
//                            onSelectLanguageClick = { navController.navigate("selectlanguage") }
//
//                        )
//                    }
//                    composable("forgot") {
//                        ForgotScreen(
//                            onRememberClick = { navController.navigate("signin") },
//                            onNextClick = { navController.navigate("otp") },
//                        ) // Define ForgotScreen composable separately
//                    }
//
//                    composable("selectlanguage") {
//                        LanguageSelectionScreen(
//                            onSelectBottomBarClick = { navController.navigate("mainscreen") }
//                        )
//                    }

//                            if (services == "Cleaning") {
//                                navController.navigate("washingcarscreen")
//                            }


                            composable("foodwashcleanCategory") {
                                GridScreen()
                            }


                            composable("washingcarscreen") {
                                WashingCarScreen()
                            }



                            composable("normalwashscreen") {
                                NormalWashScreen()
                            }

                            composable("brandwashscreen") {

                                BrandWashClothScreen()
                            }

                            composable("locationpickerscreen") {
                                LocationPickerScreen { navController.navigate("mainscreen") }
                            }

                            composable("laundry_screen"){
                                LaundryScreen()
                            }













                        }


                    }
                }
            }
        }
    }

    private fun getStartDestination(): String {
        return when (services) {
            "Fooding" -> "foodwashcleanCategory"
            "Cleaning" -> "washingcarscreen"
            "Normal Clothes" -> "normalwashscreen"
            "Branded Clothes" -> "brandwashscreen"
            "Laundry" -> "laundry_screen"
            else -> "brandwashscreen"
        }
    }
}


@Composable
fun GridScreen(modifier: Modifier = Modifier) {
    val categories = listOf(
        Category(
            name = "Fooding",
            items = listOf(
                GridItem("Home Cooking Services", R.drawable.foodall),
                GridItem("Meal Preparation", R.drawable.food_1),
                GridItem("Catering Services", R.drawable.food2),
                GridItem("Food Delivery", R.drawable.food_2),
                GridItem("Nutrition Consulting", R.drawable.food),
                GridItem("Grocery Shopping \n   Assistance", R.drawable.food6),
                GridItem("Personal Chef Services", R.drawable.food5),
                GridItem("Meal Plan Subscriptions", R.drawable.food4),
                GridItem("Event Food Services", R.drawable.ic_foodicon),
                GridItem("Specialty Diet Preparation", R.drawable.food3)
            )
        ),
        Category(
            name = "Cleaning",
            items = listOf(
                GridItem("Home Cleaning", R.drawable.cloth_1),
                GridItem("Office Cleaning", R.drawable.wash_3),
                GridItem("Deep Cleaning", R.drawable.wash_3),
                GridItem("Carpet Cleaning", R.drawable.wash_3),
                GridItem("Window Cleaning", R.drawable.wash_3),
                GridItem("Bathroom Cleaning", R.drawable.wash_3),
                GridItem("Kitchen Cleaning", R.drawable.wash_3),
                GridItem("Post-Renovation Cleaning", R.drawable.wash_3),
                GridItem("Move-In/Move-Out Cleaning", R.drawable.wash_3),
                GridItem("Industrial Cleaning", R.drawable.wash_3)
            )
        ),
        Category(
            name = "Washing",
            items = listOf(
                GridItem("Laundry Services", R.drawable.wash_winter),
                GridItem("Dry Cleaning", R.drawable.wash_winter),
                GridItem("Car Washing", R.drawable.wash_winter),
                GridItem("Pressure Washing", R.drawable.wash_winter),
                GridItem("Upholstery Cleaning", R.drawable.wash_winter),
                GridItem("Shoe Cleaning and \n   Polishing", R.drawable.wash_winter),
                GridItem("Curtain and Drapery \n   Cleaning", R.drawable.wash_winter),
                GridItem("Mattress Cleaning", R.drawable.wash_winter),
                GridItem("Roof and Gutter \n   Washing", R.drawable.wash_winter),
                GridItem("Pet Grooming and \n   Washing", R.drawable.wash_winter)
            )
        )
    )


    Column(modifier = modifier.fillMaxSize()) {
        // Add the SearchBar at the top
        //SearchBar()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            categories.forEach { category ->

                if( category.name.equals(CategoryActivity.services))
                {
                    item {
                        Text(
                            text = category.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                        )
                    }
                    item {
                        val itemCount = category.items.size
                        val rows = (itemCount + 6) / 4 // Calculate rows dynamically (4 items per row)

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((rows * 100).dp) // Assuming each row is 100.dp high
                        ) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(category.items) { item ->
                                    GridItemView(item)
                                }
                            }
                        }
                    }
                }

            }
        }



    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GridItemView(item: GridItem) {
    var showSheet by remember { mutableStateOf(false) } // Track sheet visibility

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(50.dp)
                .clickable { showSheet = true }
                .clip(RoundedCornerShape(5)), // Circular Image
            contentScale = Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        CenteredText(item.name)
    }


    // Show the BottomSheet when showSheet is true
    if (showSheet) {
        BottomSheetDetailSubCategory(onDismiss = { showSheet = false } // Hide the sheet when dismissed
        )
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewGridScreen() {
    ServiceJunctionTheme {
        GridScreen()
    }
}


data class GridItem(val name: String, val imageRes: Int)
data class Category(val name: String, val items: List<GridItem>)
