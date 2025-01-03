package com.service.servicejunction.bottomsheets

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController


@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(navController: NavController, onDismiss: () -> Unit) {
    // Remember the modal bottom sheet state
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded  = false)

    // Modal Bottom Sheet layout
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        // Content of the bottom sheet
        //FoodDetailScreen(navController)
    }

    // Open the bottom sheet when this composable is first launched
    LaunchedEffect(modalBottomSheetState) {
        modalBottomSheetState.show()  // To show the sheet
    }
}



@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDetailSubCategory( onDismiss: () -> Unit) {
    // Remember the modal bottom sheet state
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded  = false)

    // Modal Bottom Sheet layout
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
       // ServiceBookingScreen()
    }

    // Open the bottom sheet when this composable is first launched
    LaunchedEffect(modalBottomSheetState) {
        modalBottomSheetState.show()  // To show the sheet
    }
}







//@Composable
//fun CountryList(navController: NavController) {
//
////    val countries = listOf(
////        Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
////        Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
////        Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),
////        Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
////        Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
////        Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
////        Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
////        Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
////        Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
////        Pair("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
////        Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
////    )
////
////    LazyColumn {
////        items(countries) { (country, flag) ->
////            Row(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(vertical = 10.dp, horizontal = 20.dp)
////            ) {
////                Text(
////                    text = flag,
////                    modifier = Modifier.padding(end = 20.dp)
////                )
////                Text(text = country)
////            }
////        }
////    }
//}