package com.service.servicejunction.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card

// Import necessary packages for preview
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.service.servicejunction.R
import com.service.servicejunction.searchBar.SearchBar
import com.service.servicejunction.ui.theme.ServiceJunctionTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Plumbing") } // Initialize with default category

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        SearchBar()

        Text(
            text = "Trending in",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Category Chips
        ScrollableChipGroup(selectedCategory) { category ->
            selectedCategory = category // Update selected category on click
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter content based on selected category
        val filteredContent = getSampleContent().filter { it.category == selectedCategory }
        ContentGrid(filteredContent)
    }
}

@Composable
fun ScrollableChipGroup(selectedCategory: String, onCategorySelected: (String) -> Unit) {
    val categories = listOf(
        "Plumbing",
        "Electrical",
        "Cleaning",
        "Painting",
        "Pest Control",
        "Appliance Repair",
        "Carpentry",
        "Gardening",
        "Moving Services",
        "Home Renovation"
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        categories.forEach { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

@Composable
fun ContentGrid(items: List<ContentItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            ContentCard(item)
        }
    }
}

@Composable
fun ContentCard(item: ContentItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (item.isFree) {
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart),
                    color = Color.Black,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "FREE",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

// Update the ContentItem class to include category property
data class ContentItem(
    val id: Int,
    val title: String,
    val imageRes: Int, // Use Int to reference drawable resources
    val isFree: Boolean = false,
    val category: String // Add category field
)

// Sample data with categories
private fun getSampleContent(): List<ContentItem> {
    return listOf(
        ContentItem(1, "Plumbing Basics", R.drawable.kitech_1, category = "Plumbing"),
        ContentItem(1, "Plumbing Basics", R.drawable.kitech_1, category = "Plumbing"),
        ContentItem(1, "Plumbing Basics", R.drawable.kitech_1, category = "Plumbing"),
        ContentItem(1, "Plumbing Basics", R.drawable.kitech_1, category = "Plumbing"),

        ContentItem(2, "Electrical Wiring", R.drawable.kitech_1, category = "Electrical"),
        ContentItem(2, "Electrical Wiring", R.drawable.kitech_1, category = "Electrical"),
        ContentItem(2, "Electrical Wiring", R.drawable.kitech_1, category = "Electrical"),
        ContentItem(2, "Electrical Wiring", R.drawable.kitech_1, category = "Electrical"),


        ContentItem(2, "Electrical Wiring", R.drawable.kitech_1, category = "Electrical"),
        ContentItem(3, "House Cleaning Tips", R.drawable.kitech_1, category = "Cleaning"),
        ContentItem(4, "Wall Painting Ideas", R.drawable.kitech_1, category = "Painting"),
        ContentItem(5, "Pest Control Techniques", R.drawable.kitech_1, category = "Pest Control"),
        ContentItem(6, "Appliance Repair Guide", R.drawable.kitech_1, category = "Appliance Repair"),
        ContentItem(7, "Basic Carpentry Skills", R.drawable.kitech_1, category = "Carpentry"),
        ContentItem(8, "Gardening Tips", R.drawable.kitech_1, category = "Gardening"),
        ContentItem(9, "Moving Checklist", R.drawable.kitech_1, category = "Moving Services"),
        ContentItem(10, "Home Renovation Ideas", R.drawable.kitech_1, category = "Home Renovation")
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    ServiceJunctionTheme {
        SearchScreen()
    }
}

