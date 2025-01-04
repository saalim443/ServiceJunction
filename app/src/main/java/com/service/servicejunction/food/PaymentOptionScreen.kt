package com.service.servicejunction.food

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import com.service.servicejunction.R

@Composable
fun PaymentOptionScreen(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf("Paytm UPI") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Button and Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /* Handle back button click */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Payment Option",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // UPI Options
        Text("UPI", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        PaymentOption(
            optionName = "Paytm UPI",
            icon = painterResource(id = R.drawable.ic_edit), // Replace with actual Paytm icon resource
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )
        PaymentOption(
            optionName = "PhonePe",
            icon = painterResource(id = R.drawable.ic_edit), // Replace with actual PhonePe icon resource
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )
        PaymentOption(
            optionName = "GPay",
            icon = painterResource(id = R.drawable.ic_edit), // Replace with actual GPay icon resource
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Cards
        Text("Cards", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(8.dp))
        PaymentOption(
            optionName = "MasterCard ****2575",
            icon = painterResource(id = R.drawable.ic_edit), // Replace with actual card icon resource
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Cash
        Text("Cash", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(8.dp))
        PaymentOption(
            optionName = "Cash",
            icon = painterResource(id = R.drawable.ic_edit), // Replace with actual cash icon resource
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Proceed Button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp), // Slightly reduced height
            shape = RoundedCornerShape(16.dp), // Rounded corners
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.black)
            )
        ) {
            Text("Continui")
        }
    }
}

@Composable
fun PaymentOption(
    optionName: String,
    icon: Painter,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOptionSelected(optionName) }
            .padding(vertical = 8.dp)
    ) {


                RadioButton(
                    selected = optionName == selectedOption,
                    onClick = { onOptionSelected(optionName) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Black,  // Set the selected color to black
                        unselectedColor = Color.Black // Set the unselected color to black
                    )
                )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = optionName, modifier = Modifier.weight(1f))
        Icon(painter = icon, contentDescription = optionName, modifier = Modifier.size(24.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun PaymentOptionScreenPreview() {
    PaymentOptionScreen(navController = rememberNavController())
}
