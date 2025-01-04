package com.service.servicejunction.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.service.servicejunction.home.data.BannerData
import com.service.servicejunction.home.data.GridItem
import com.service.servicejunction.home.ui.CustomerTestimonials
import com.service.servicejunction.home.ui.GridItemView
import com.service.servicejunction.home.ui.GridItemViewII
import com.service.servicejunction.home.ui.ProfileHeader
import com.service.servicejunction.home.ui.SeasonalPromotions
import com.service.servicejunction.ui.theme.ServiceJunctionTheme
import kotlinx.coroutines.delay



@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Add each composable as an item in the LazyColumn
        item { ProfileHeader() }
        item { AutoSlider() }
        item { CardShowData() }
        item { SeasonalPromotions() }
        item { CustomerTestimonials() }
    }
}


@Composable
fun CardShowData() {

    val gridItems = listOf(
        GridItem("Fooding", R.drawable.foodall),
        GridItem("Cleaning", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Laundry", R.drawable.wash_3)
    )

    val gridItemsII = listOf(
        GridItem("Fooding", R.drawable.foodall),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Washing", R.drawable.wash_1),
        GridItem("Cleaning", R.drawable.wash_3)
    )

    // First Grid Section
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), // 3 columns grid
        modifier = Modifier
            .fillMaxWidth() // Ensure it occupies fixed space horizontally
            .height(150.dp) // Provide a fixed height to the grid
            .padding(vertical = 8.dp)
    ) {
        items(gridItems) { item ->
            GridItemView(item = item)
        }
    }

    // Second Grid Section
    LazyVerticalGrid(
        columns = GridCells.Fixed(5), // 5 columns grid
        modifier = Modifier
            .fillMaxWidth() // Ensure it occupies fixed space horizontally
            .height(200.dp) // Provide a fixed height to the grid
            .padding(vertical = 8.dp)
    ) {
        items(gridItemsII) { item ->
            GridItemViewII(item = item)
        }
    }
}




@Composable
fun AutoSlider() {
    val banners = listOf(
        BannerData("Title 1", "Subtitle 1", R.drawable.wash_1),
        BannerData("Title 2", "Subtitle 2", R.drawable.wash_2),
        BannerData("Title 3", "Subtitle 3", R.drawable.wash_3)
    )
    AutoBannerSlider(banners = banners, modifier = Modifier.fillMaxWidth())
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AutoBannerSlider(banners: List<BannerData>, modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableIntStateOf(0) }
    LaunchedEffect(banners.size) {
        while (true) {
            delay(3000)
            currentIndex = (currentIndex + 1) % banners.size
        }
    }
    Box(modifier = modifier) {
        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                fadeIn() with fadeOut()
            }, label = ""
        ) { index ->
            banners[index].let { banner ->
                AddAutoBanner(
                    title = banner.title,
                    subtitle = banner.subtitle,
                    imageRes = banner.imageRes
                )
            }
        }
    }
}

@Composable
fun AddAutoBanner(title: String, subtitle: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .padding(0.dp)
    ) {
        // Background Image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))

        )

        // Content Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "", // Display the title
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp)) // Add space between title and subtitle

            Text(
                text = "", // Display the subtitle
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp)) // Add space before the button

            // "Book Now" Text with padding and background
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .background(Color.Black, RoundedCornerShape(8.dp)) // Black background with rounded corners
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Padding inside the box
            ) {
                Text(
                    text = "Book Now",
                    color = Color.White, // White text
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun AddBanner(title: String, subtitle: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .padding(0.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))

        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ServiceJunctionTheme {
        HomeScreenContent()
    }
}


