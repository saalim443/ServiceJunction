package com.service.servicejunction.home.washing.car

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

// Data Class for Brand
data class Brand(val name: String)

// Map of brands and their subcategories
val carSubCategories = mapOf(
    "Toyota" to listOf("SUV", "Sedan", "Hatchback"),
    "Honda" to listOf("Sedan", "Civic", "CR-V"),
    "Ford" to listOf("SUV", "Pickup Truck", "Mustang"),
    "BMW" to listOf("Luxury Sedan", "Coupe", "Convertible"),
    "Mercedes-Benz" to listOf("Luxury SUV", "Luxury Sedan", "Convertible"),
    "Tesla" to listOf("Electric Sedan", "Model 3", "Model Y"),
    "Nissan" to listOf("SUV", "Altima", "Micra")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WashingCarScreen() {
    val brands = listOf(
        Brand("Toyota"), Brand("Honda"), Brand("Ford"),
        Brand("BMW"), Brand("Mercedes-Benz"), Brand("Tesla"),
        Brand("Nissan")
    )

    // State for selected brand and its subcategories
    var selectedBrand by remember { mutableStateOf<Brand?>(null) }
    var subCategories by remember { mutableStateOf(emptyList<String>()) }
    var selectedSubCategory by remember { mutableStateOf("") }

    // Bottom Sheet State
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Brand Car Wash", fontWeight = FontWeight.Bold, fontSize = 14.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    if (selectedBrand != null && selectedSubCategory.isNotEmpty()) {
                        isBottomSheetOpen = true // Open bottom sheet
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Proceed", color = Color.White, fontSize = 16.sp)
            }
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Left Column: Brands List
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Brands",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyColumn {
                    items(brands.size) { index ->
                        val brand = brands[index]
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedBrand = brand
                                    subCategories = carSubCategories[brand.name] ?: emptyList()
                                    selectedSubCategory = "" // Reset selection
                                }
                                .background(
                                    if (selectedBrand == brand) Color.LightGray else Color.Transparent,
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Text(
                                text = brand.name,
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }

            // Right Column: Subcategories with Radio Buttons
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Details",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (selectedBrand != null) {
                    Text(
                        text = "Selected Brand: ${selectedBrand?.name}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Column {
                        subCategories.forEach { category ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedSubCategory = category }
                                    .padding(vertical = 8.dp)
                            ) {
                                RadioButton(
                                    selected = selectedSubCategory == category,
                                    onClick = { selectedSubCategory = category },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color.Black,
                                        unselectedColor = Color.Gray
                                    )
                                )
                                Text(
                                    text = category,
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Please select a brand from the left list.",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }

    // Bottom Sheet Content
    if (isBottomSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetOpen = true },
            sheetState = bottomSheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



//                Text(
//                    text = "Selected Details",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = "Brand: ${selectedBrand?.name}",
//                    fontSize = 16.sp,
//                    color = Color.Black
//                )
//                Text(
//                    text = "Category: $selectedSubCategory",
//                    fontSize = 16.sp,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(16.dp))



                CarServiceScreen(selectedBrand?.name,selectedSubCategory)
                Button(
                    onClick = { isBottomSheetOpen = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Close", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WashingCarScreenPreview() {
    WashingCarScreen()
}

