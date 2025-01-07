package com.service.servicejunction.home.laundry.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.service.servicejunction.home.laundry.domain.local.LaundryCategory

@Composable
fun LaundryScreen(
    viewModel: LaundryViewModel = hiltViewModel()
) {
    val state = viewModel.state

    var selectedService by remember { mutableStateOf<String?>(null) }
    var selectedLaundryId by remember { mutableStateOf<Int?>(null) }

    Column {

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.laundryListWithCategory.size) { index ->
                val laundry = state.laundryListWithCategory[index].laundry
                LaundryGridItem(
                    item = laundry.laundryName,
                    icon = laundry.iconRes,
                    selectedService = selectedService,
                    onSelectService = { selected ->
                        if (selectedService == selected) {
                            selectedService = null
                            selectedLaundryId = null
                        } else {
                            selectedService = selected
                            selectedLaundryId = laundry.laundryId
                        }
                    }
                )
            }
        }
        if (selectedService != null && selectedLaundryId != null) {
            CategoryScreen(laundryId = selectedLaundryId!!, viewModel)
            viewModel.getLaundryCategory(selectedLaundryId!!)
        } else {
            Text("Select Category", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun CategoryScreen(
    laundryId: Int,
    viewModel: LaundryViewModel,
//    laundry: List<LaundryCategory>,
    //state: LaundryState
) {

//    val state = viewModel.state

//    viewModel.getLaundryCategory(laundryId)

    val laundry = viewModel.state.laundryCategory

    LazyColumn(
        modifier = Modifier.padding(4.dp)
    ) {
        items(laundry.size) { index ->
            val category = laundry[index]
            LaundryDetailItem(
                categoryName = category.categoryName,
                count = category.count,
                iconRes = category.iconRes,
                onCountChanged = { newCount ->
                    viewModel.updateLaundryCategory(
                        categoryId = category.categoryId,
                        laundryId = laundryId,
                        categoryName = category.categoryName,
                        newCount = newCount,
                        iconRes = category.iconRes,
                    )
                })
        }

//        items(
//            itemContent = state.laundryCategory,
//            key = { it.categoryId }) { category ->
//            LaundryDetailItem(
//                categoryName = category.categoryName,
//                count = category.count,
//                iconRes = category.iconRes,
//                onCountChanged = { newCount ->
//                    viewModel.updateLaundryCategory(
//                        categoryId = category.categoryId,
//                        laundryId = selectedLaundryId!!,
//                        categoryName = category.categoryName,
//                        newCount = newCount,
//                        iconRes = category.iconRes
//                    )
//                }
//            )
//        }
    }
}

@Preview
@Composable
private fun PreviewLaundryScreen() {
    LaundryScreen()
}