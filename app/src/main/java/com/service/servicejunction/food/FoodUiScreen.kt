package com.service.servicejunction.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.service.servicejunction.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodUiScreen(navController: NavHostController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val  tabs = listOf("Breakfast", "Lunch", "Dinner", "Drinks")

    val foodItems = when (selectedTab) {
        0 -> getFoodItems("Breakfast") // Breakfast
        1 -> getFoodItems("Lunch") // Lunch
        2 -> getFoodItems("Dinner") // Dinner
        3 -> getFoodItems("Drinks") // Drinks
        else -> emptyList()
    }

//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            FoodMenuScreen()
//            Spacer(modifier = Modifier.height(24.dp))
//            TabRow(selectedTab, tabs, onTabSelected = { selectedTab = it })
//            Spacer(modifier = Modifier.height(16.dp))
//            FoodList(foodItems,navController)
//        }
//    }

    FoodMenuScreen(foodItems, tabs, onTabSelected = { selectedTab = it }, navController = navController)
}











@Composable
fun FoodMenuScreen(foodItems: List<FoodItem>, tabs: List<String>, navController: NavController, onTabSelected: (Int) -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .background(Color(0xFFA4BB26), RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
        ) {
            TopBar()

            Slider()

            SearchBar(tabs, onTabSelected)
        }

        PromoSection(foodItems,navController)
    }
}


@Composable
fun TopBar() {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .background(Color(0xFFC91933))
            .fillMaxWidth()
            .padding(8.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row {
            Text(
                text = "Hi,",
                fontWeight = FontWeight.ExtraLight,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                fontSize = 22.sp
            )
            Text(
                text = "Juliana Silva!",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 22.sp
            )
        }
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Top)
                .size(40.dp)
                .padding(4.dp)
        )
    }
}

private val foodItem = listOf(
    PagerItem(R.drawable.food2, "Taste the Authenticity of Indonesia"),
    PagerItem(R.drawable.food3, "Taste the Authenticity of Indonesia"),
    PagerItem(R.drawable.food4, "Taste the Authenticity of U.K.")
)

@Composable
fun Slider() {

    val pagerState = rememberPagerState(
        pageCount = { foodItem.size }
    )
    val SLIDER_INTERVAL = 2000L

    LaunchedEffect(Unit) {
        while (true) {
            delay(SLIDER_INTERVAL)
            coroutineScope {
                val nextPage = (pagerState.currentPage + 1) % foodItem.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC91933), RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
    ) {

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) { index ->

            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(foodItem[index].image),
                        contentDescription = "Image Slider",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                            .clip(CircleShape)
                    )

                    Column {

                        Text(
                            text = foodItem[index].title,
                            color = Color(0xFFF8E027),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            minLines = 3
                        )

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(Color(0xFFA4BB26)),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 16.dp,
                                pressedElevation = 4.dp
                            ),
                            modifier = Modifier
                                .height(24.dp)
                                .widthIn(88.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "ORDER NOW!",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 8.sp,
                                color = Color.White
                            )
                        }
                    }
                }

            }
        }
    }
}

data class PagerItem(
    val image: Int,
    val title: String
)

@Composable
fun SearchBar(tabs: List<String>, onTabSelected: (Int) -> Unit) {

    //val gridItems = listOf("Appetizer", "Main Course", "Snacks", "Desserts", "Beverages")

    Column(
        modifier = Modifier
            .background(Color(0xFFA4BB26), RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Search your favorite food",
                    fontSize = 8.sp,
                    textAlign = TextAlign.Start
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_tune_24),
                    contentDescription = "Search Filter",
                    tint = Color.Black
                )
            },
            maxLines = 1,
            singleLine = true,
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(20.dp)

        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            itemsIndexed(tabs) { index, item ->
                FoodGridItem(item, index, onTabSelected)
            }
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 16.dp,
                pressedElevation = 4.dp
            ),
            modifier = Modifier
                .height(20.dp)
                .widthIn(60.dp)
                .align(Alignment.CenterHorizontally)
                .offset(y = 11.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "M O R E",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 8.sp,
                color = Color(0xFFC91933)
            )
        }
    }
}



@Composable
fun FoodGridItem(text: String, index: Int, onTabSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp)
            .clickable {
                onTabSelected(index)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFFF8E027), RoundedCornerShape(40.dp))

        ){
            Icon(
                Icons.Default.Face,
                contentDescription = "Item Image",
                modifier = Modifier
                    .padding(6.dp)
                    .size(40.dp),
                tint = (Color(0xFFC91933))

            )
        }
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 10.sp,
            maxLines = 1,
            modifier = Modifier.padding(4.dp)
        )
    }
}







@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PromoSection(foodItems: List<FoodItem>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Special Promo!",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFFC91933)
            )

            Box(
                modifier = Modifier
                    .background(Color(0xFFF8E027), RoundedCornerShape(40.dp))
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    text = "DISCOUNT 50%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFFC91933)
                )
            }
        }

//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp)
//        ) {
//            items(foodItem) { item ->
//                FoodPromoCard(
//                    item.image, item.title
//                )
//            }
//        }

        FoodList(foodItems, navController)
    }
}





















@Composable
private fun TabRow(
    selectedTab: Int,
    tabs: List<String>,
    onTabSelected: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
        edgePadding = 0.dp
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = { Text(title) }
            )
        }
    }
}

@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoodList(foodItems: List<FoodItem>,navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns for a span count of 2
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp) // Optional padding
    ) {
        items( foodItems) { foodItem ->
            FoodCard(foodItem,navController)
        }
    }
}
@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoodCard(foodItem: FoodItem, navController: NavController) {
    var showSheet by remember { mutableStateOf(false) } // Track sheet visibility

    // Trigger Bottom Sheet to open on Card Click
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                showSheet = true // Show the bottom sheet when card is clicked
            },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = painterResource(id = foodItem.imageRes),
                contentDescription = foodItem.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Text Overlay at the Bottom
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart) // Align text at the bottom left
                    .background(Color.White.copy(alpha = 0.8f)) // Semi-transparent background for text
                    .padding(16.dp)
            ) {
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${foodItem.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(R.color.black)
                )
            }

            // Floating Action Button overlayed at the bottom-right corner
            FloatingActionButton(
                onClick = { /* Handle add click */ },
                containerColor = colorResource(R.color.black),
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Align FAB at the bottom right corner
                    .padding(16.dp)
                    .size(30.dp) // Customize size of the FAB
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add to cart",
                    tint = Color.White
                )
            }
        }
    }

    // Show the BottomSheet when showSheet is true
    if (showSheet) {
        BottomSheet(navController,onDismiss = { showSheet = false } // Hide the sheet when dismissed
        )
    }
}






data class FoodItem(
    val name: String,
    val price: Double,
    val imageRes: Int,
    val category: String // Add category for breakfast, lunch, etc.
)

private fun getFoodItems(category: String) = when (category) {
    "Breakfast" -> listOf(
        FoodItem("Egg and toast", 10.0, R.drawable.food, "Breakfast"),
        FoodItem("Pancake", 10.0, R.drawable.foodall, "Breakfast"),
        FoodItem("Omelette", 8.0, R.drawable.food, "Breakfast"),
        FoodItem("Cereal with milk", 5.0, R.drawable.foodall, "Breakfast"),
        FoodItem("Bagel with cream cheese", 6.0, R.drawable.food, "Breakfast"),
        FoodItem("French Toast", 12.0, R.drawable.foodall, "Breakfast"),
        FoodItem("Smoothie bowl", 9.0, R.drawable.food, "Breakfast"),
        FoodItem("Avocado toast", 7.0, R.drawable.foodall, "Breakfast"),
        FoodItem("Waffles with syrup", 11.0, R.drawable.food, "Breakfast"),
        FoodItem("Yogurt with granola", 6.0, R.drawable.foodall, "Breakfast")
    )

    "Lunch" -> listOf(
        FoodItem("Grilled chicken", 15.0, R.drawable.foodall, "Lunch"),
        FoodItem("Caesar Salad", 12.0, R.drawable.food, "Lunch"),
        FoodItem("Chicken Burrito", 14.0, R.drawable.foodall, "Lunch"),
        FoodItem("Vegetable Stir Fry", 13.0, R.drawable.food, "Lunch"),
        FoodItem("Club Sandwich", 11.0, R.drawable.foodall, "Lunch"),
        FoodItem("Quinoa Salad", 10.0, R.drawable.food, "Lunch"),
        FoodItem("Fish Tacos", 16.0, R.drawable.foodall, "Lunch")
    )

    "Dinner" -> listOf(
        FoodItem("Steak", 20.0, R.drawable.foodall, "Dinner"),
        FoodItem("Pasta", 18.0, R.drawable.food, "Dinner"),
        FoodItem("Chicken Alfredo", 19.0, R.drawable.foodall, "Dinner"),
        FoodItem("Grilled Salmon", 22.0, R.drawable.food, "Dinner"),
        FoodItem("Beef Wellington", 25.0, R.drawable.foodall, "Dinner"),
        FoodItem("Vegetable Lasagna", 17.0, R.drawable.food, "Dinner"),
        FoodItem("Lamb Chops", 23.0, R.drawable.foodall, "Dinner")
    )

    "Drinks" -> listOf(
        FoodItem("Coffee", 5.0, R.drawable.food, "Drinks"),
        FoodItem("Smoothie", 7.0, R.drawable.foodall, "Drinks"),
        FoodItem("Iced Tea", 4.0, R.drawable.food, "Drinks"),
        FoodItem("Lemonade", 5.0, R.drawable.foodall, "Drinks"),
        FoodItem("Orange Juice", 6.0, R.drawable.food, "Drinks"),
        FoodItem("Cappuccino", 6.0, R.drawable.foodall, "Drinks"),
        FoodItem("Milkshake", 8.0, R.drawable.food, "Drinks")
    )

    else -> emptyList()
}

@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FoodUiScreenPreview() {
    FoodUiScreen(
        navController = rememberNavController() // Provide a dummy NavController for preview
    )
}