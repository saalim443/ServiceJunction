import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalWashScreen() {
    val slots = listOf("1-5", "5-10", "10-15", "15-20", "20-25", "25-30", "30-35", "35-40", "40-45", "45-50", "50-55", "55-60")
    val slotPrices = mapOf(
        "1-5" to "100 Rs", "5-10" to "200 Rs", "10-15" to "300 Rs", "15-20" to "400 Rs",
        "20-25" to "500 Rs", "25-30" to "600 Rs", "30-35" to "700 Rs", "35-40" to "800 Rs",
        "40-45" to "900 Rs", "45-50" to "1000 Rs", "50-55" to "1100 Rs", "55-60" to "1200 Rs"
    )

    var selectedSlot by remember { mutableStateOf("") }
    var remarkText by remember { mutableStateOf(TextFieldValue("")) }
    val washCategories = listOf("Dry Cleaning", "Normal Wash", "Steam Wash", "Ironing")
    val selectedCategories = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Quantity Slot", fontWeight = FontWeight.Bold) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header Text
                Text(
                    text = "Choose a quantity for your wash:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Grid Layout
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.wrapContentSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(slots) { slot ->
                        SlotItem(
                            slot = slot,
                            isSelected = selectedSlot == slot,
                            onSelect = { selectedSlot = slot }
                        )
                    }
                }

                // Checkbox Section for Categories
                Spacer(modifier = Modifier.height(16.dp))
                // Checkbox Grid Section for Categories
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Select Wash Categories:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 2 columns for checkboxes
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(washCategories) { category ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (selectedCategories.contains(category)) {
                                        selectedCategories.remove(category)
                                    } else {
                                        selectedCategories.add(category)
                                    }
                                }
                                .padding(4.dp)
                        ) {
                            Checkbox(
                                checked = selectedCategories.contains(category),
                                onCheckedChange = { isChecked ->
                                    if (isChecked) selectedCategories.add(category)
                                    else selectedCategories.remove(category)
                                }
                            )
                            Text(
                                text = category,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

            }

            // Overlapping Bottom Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .offset(y = 32.dp)
                    .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Price Section
                Text(
                    text = "Total Price of Qty : ${if (selectedSlot.isNotEmpty()) slotPrices[selectedSlot] else "0 Rs"}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Remark TextField
                TextField(
                    value = remarkText,
                    onValueChange = { remarkText = it },
                    placeholder = { Text("Add a remark (optional)...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                // Selected Categories Preview
                if (selectedCategories.isNotEmpty()) {
                    Text(
                        text = "Selected Categories: ${selectedCategories.joinToString(", ")}",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                // Proceed Button
                Button(
                    onClick = {
                        // Handle button click: selectedSlot, selectedCategories, remarkText.text
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp)
                        .height(45.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.black)
                    )
                ) {
                    Text("Proceed to checkout", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun SlotItem(slot: String, isSelected: Boolean, onSelect: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                color = if (isSelected) Color.Black else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onSelect() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = slot,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NormalWashScreenPreview() {
    NormalWashScreen()
}
