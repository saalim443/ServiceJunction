package com.service.servicejunction.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R

@Composable
fun PaymentSuccessScreen(
    paymentMode: String = "UPI",
    totalAmount: String = "₹749",
    payDate: String = "Apr 10, 2022",
    payTime: String = "10:45 am",
    onDoneClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Main Content
        Column(
            modifier = Modifier
                .weight(1f) // Take up the available space, leaving room for the button
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Card Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(50.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Payment Success Title
                    Text(
                        text = "Payment Success",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Payment Details
                    PaymentDetailRow(label = "Payment Mode", value = paymentMode)
                    PaymentDetailRow(label = "Total Amount", value = totalAmount)
                    PaymentDetailRow(label = "Pay Date", value = payDate)
                    PaymentDetailRow(label = "Pay Time", value = payTime)

                    Spacer(modifier = Modifier.height(160.dp))

                    // Total Pay
                    Text(
                        text = "Total Pay",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        text = totalAmount,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                }
            }

            // Overlay Checkmark Icon
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .offset(y = (-40).dp) // Offset upwards to overlay on the card
                    .background(color = Color(0xFF000000), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val checkIcon: Painter = painterResource(id = R.drawable.baseline_check_24) // Replace with actual icon resource
                androidx.compose.foundation.Image(
                    painter = checkIcon,
                    contentDescription = "Success Icon",
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Done Button at the Bottom
        Button(
            onClick = onDoneClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black // Set the background color to black
            )
        ) {
            Text(
                text = "Done",
                fontSize = 16.sp,
                color = Color.White // Set the text color to white for contrast
            )
        }
    }
}

@Composable
fun PaymentDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentSuccessScreenPreview() {
    PaymentSuccessScreen()
}
