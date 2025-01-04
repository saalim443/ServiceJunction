package com.service.servicejunction.home.washing.cloths.branded.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.service.servicejunction.R
import com.service.servicejunction.home.washing.cloths.branded.domain.local.BrandWithDetails
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

//data class Brand(val name: String, var isSelected: Boolean = false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandWashClothScreen(
    viewModel: BrandWashClothViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val scope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderSection()

        BrandSection(state.companyListWithDetails, viewModel, state.companyDetail)

        FooterSection(
            onProcessClick = {
                viewModel.getCompanyListWithDetails()
                scope.launch {
                    showBottomSheet = true
                }
            }
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxHeight(),
            //.height(600.dp),
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            BottomSheetContent(state, viewModel)
        }
    }
}


@Composable
fun HeaderSection() {

    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }

        Text(
            text = "Brand Wash Cloth",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun BrandSection(
    list: List<BrandWithDetails>,
    viewModel: BrandWashClothViewModel,
    companyDetails: List<BrandWithDetails>
) {

    var selectedCompanyId by remember { mutableStateOf<Int?>(null) }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {

            Text(
                text = "Brands",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(
                modifier = Modifier.height(600.dp)
            ) {
                items(list.size) { i ->
                    val companyName = list[i].brand.bandName
                    val companyId = list[i].brand.brandId

                    val isSelected = selectedCompanyId == companyId
                    Row(
                        modifier = Modifier
                            .background(
                                if (isSelected) Color.LightGray else Color.Transparent,
                                RoundedCornerShape(4.dp)
                            )
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                selectedCompanyId = companyId
                                viewModel.getCompanyDetails(companyId = companyId)
                            }
                    ) {
                        Text(
                            text = companyName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(2f)
        ) {
            Text(
                text = "Detains",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn {
                items(companyDetails.size) { i ->
                    companyDetails[i].details.forEach { details ->
                        WashClothDetailItem(
                            details.item,
                            details.count,
                            viewModel,
                            details.detailId,
                            details.brandId,
                            details.price,
                            details.iconRes
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FooterSection(onProcessClick: () -> Unit) {
    Row {
        Button(
            onClick = onProcessClick,
            colors = ButtonDefaults.buttonColors(if (isSystemInDarkTheme()) Color.White else Color.Black),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 16.dp,
                pressedElevation = 4.dp
            ),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(8.dp)
                .height(32.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Proceed",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = if (isSystemInDarkTheme()) Color.Black else Color.White
            )
        }
    }
}

@Composable
fun BottomSheetContent(
    state: BrandWashClothState,
    viewModel: BrandWashClothViewModel
) {
    Box {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxHeight()
        ) {
            item { SelectedSection(state, viewModel) }

            item { DescriptionSection() }

            item { ServicesSection() }
        }
        CameraButton()
    }
}

@Composable
fun SelectedSection(
    state: BrandWashClothState,
    viewModel: BrandWashClothViewModel
) {

    val companyName = state.companyListWithDetails.first().details

    val totalItemCount = remember(companyName) {
        companyName.sumOf { company -> viewModel.getTotalCount(company.item) }
    }

    Column {
        LazyColumn(
            modifier = Modifier.height(350.dp)
        ) {
            items(companyName.size) { i ->
                val itemName = companyName[i].item
                val itemPrice = companyName[i].price
                val itemIcon = companyName[i].iconRes
                val totalCount = viewModel.getTotalCount(itemName)
                WashCheckOutItem(itemName, totalCount, itemPrice, itemIcon)
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Total Clothes",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray
            )

            Text(
                text = totalItemCount.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Blue
            )
        }
    }
}

@Composable
fun DescriptionSection() {
    Column {
        Text(
            text = "About",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp
        )

        Text(
            text = "Read Labels and Tags:\n" +
                    "Inspect Clothes:\n" +
                    "Empty Pockets:\n" +
                    "Separate by Fabric Type\n" +
                    "Group Similar Items:\n" +
                    "Remove Accessories:",
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun ServicesSection() {
    var remarkText by remember { mutableStateOf(TextFieldValue("")) }


    val services = listOf(
        "Cleaning", "Ironing", "Drying"
    )

    Column {
        Text(
            text = "Services",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .height(136.dp)
                .fillMaxWidth()
        ) {
            items(services) { item ->
                ServicesItem(item)
            }
        }

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


        //Book Button
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(if (isSystemInDarkTheme()) Color.White else Color.Black),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 16.dp,
                pressedElevation = 4.dp
            ),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .padding(8.dp, vertical = 8.dp)
                .height(32.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Book",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = if (isSystemInDarkTheme()) Color.Black else Color.White
            )
        }
    }

}

@Composable
fun CameraButton() {
    var showCamera by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        // Floating Action Button
        FloatingActionButton(
            onClick = { showCamera = true },
            containerColor = Color.White,
            contentColor = Color.Black,
            shape = CircleShape,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.camera_icon) ,
                contentDescription = "Open Camera",
                Modifier.size(32.dp)
            )
        }

        // Show Dialog when FAB is clicked
        if (showCamera) {
            CameraDialog(onDismiss = { showCamera = false })
        }
    }

}

@Composable
fun CameraDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageCaptureFromCamera()
            }
        }
    }
}

@Composable
fun ImageCaptureFromCamera() {

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }) {
            Text(text = "Capture Image From Camera")
        }
    }

    if (capturedImageUri.path?.isNotEmpty() == true) {
        Image(
            modifier = Modifier
                .padding(16.dp, 8.dp),
            painter = rememberAsyncImagePainter(capturedImageUri),
            contentDescription = null
        )
    }
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, ".jpg", externalCacheDir
    )

    return image
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BrandWashClothScreenPreview() {
    BrandWashClothScreen()
}
