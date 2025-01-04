package com.service.servicejunction.searchBar


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R


@Composable
fun SearchBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp), // Padding around the card
        elevation = CardDefaults.cardElevation(4.dp), // Corrected elevation
        shape = RoundedCornerShape(8.dp) // Rounded corners
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp))
                .padding(3.dp), // Inner padding for content spacing
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Search action */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Black)
            }
            Text(
                text = "Search product",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Cart action */ }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color.Black)
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(text = "Search here...", color = Color.Gray)
        },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClearQuery) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear Icon")
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),


        )
}

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { query ->
                    // Handle search action here
                    println("Searching for $query")
                },
                onClearQuery = { searchQuery = "" }
            )
        }
    ) { contentPadding ->
        // Properly handle the content padding passed by the Scaffold
        Box(modifier = Modifier.padding(contentPadding)) {
            // Your actual content goes here
            Text(text = "Search results will appear here", modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}