package com.service.servicejunction.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.servicejunction.R


@Composable
fun Signin(onLoginClick: () -> Unit , onForgotPasswordClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ){
            // Logo

            Image(
                painter = painterResource(id = R.drawable.ic_login), // Add your logo resource
                contentDescription = "MyMalla Logo",
                modifier = Modifier.padding(bottom = 25.dp)
                    .height(200.dp) // Set height to 150 dp
                    .aspectRatio(1f) // Maintain a 1:1 aspect ratio for proportional width
                    .padding(bottom = 16.dp)
            )
            // Sign In Text
            Text(
                text = "Good to see you again \nLog in and act !!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )



            // Email/Username TextField
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Mobile number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp) // Reduced bottom padding for a more compact UI
                  , // Set the height explicitly for a smaller input field
                shape = RoundedCornerShape(16.dp), // Rounded corners
                singleLine = true
            )




            // Forgot Password
            Text(
                text = "Forgot Password?",
                color = Color.Gray,
                fontSize = 12.sp, // Slightly smaller font size
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 18.dp)
                    .clickable { onForgotPasswordClick() }
            )

            // Login Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), // Slightly reduced height
                shape = RoundedCornerShape(16.dp), // Rounded corners
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.black)
                )
            ) {
                Text("Login")
            }

            // OR Divider
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp), // Smaller vertical padding
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "- OR -",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            // Social Media Login Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp), // Reduced bottom padding
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(onClick = { onForgotPasswordClick }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Login",
                        tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
            }

            // Create Account Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account yet? ",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "Create an account",
                    color = colorResource(id = R.color.black),
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Composable
fun ForgotScreen(onRememberClick: () -> Unit, onNextClick: () -> Unit) {
    var phone by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Arrow Icon at the top left
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable { /* Handle back action */ }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_forgot), // Add your logo resource
                contentDescription = "MyMalla Logo",
                modifier = Modifier.padding(bottom = 25.dp)
                    .height(200.dp) // Set height to 150 dp
                    .aspectRatio(1f) // Maintain a 1:1 aspect ratio for proportional width
                    .padding(bottom = 16.dp)
            )
            // Forgot Password Text
            Text(
                text = "Forgot Password",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )

            // Instruction Text
            // Lorem Ipsum Text
            Text(
                text = "Enter your register mobile number !!",
                color = Color.Gray,
                fontSize = 12.sp, // Smaller font size for description
                modifier = Modifier.padding(bottom = 18.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )

            // Phone Number TextField
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Email Mobile number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp), // Rounded corners for better visual appeal
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            // Remember Password Text
            Text(
                text = "Remember Password?",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 24.dp),
                fontSize = 14.sp
            )



            // Login Button
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), // Slightly reduced height
                shape = RoundedCornerShape(16.dp), // Rounded corners
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.black)
                )
            ) {
                Text("Submit")
            }

            // OR Divider
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "- OR -",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            // Create Account Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account yet? ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "Create an account",
                    color = colorResource(id = R.color.black),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Handle create account action */ }
                )
            }
        }
    }
}

@Composable
fun LanguageSelectionScreen(onSelectBottomBarClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English (UK)") }
    val languages = listOf(
        "Dutch (Nederlands)",
        "English (UK)",
        "German (Deutsche)",
        "Spanish (española)"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Arrow Icon at the top left
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable { /* Handle back action */ }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_language), // Add your logo resource
                contentDescription = "MyMalla Logo",
                modifier = Modifier.padding(bottom = 25.dp)
                    .height(200.dp) // Set height to 150 dp
                    .aspectRatio(1f) // Maintain a 1:1 aspect ratio for proportional width
                    .padding(bottom = 16.dp)
            )

            // Title
            Text(
                text = stringResource(R.string.pick_up_your_language),
                style = TextStyle(
                    color = colorResource(id = R.color.black),
                    fontSize = 20.sp,

                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )

            // Description
            Text(
                text = stringResource(R.string.select_your_favourite_language),
                color = Color.Gray,
                fontSize = 12.sp, // Smaller font size for description
                modifier = Modifier.padding(bottom = 18.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )

            // Dropdown for Language Selection
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(8.dp)
                    ) // Off-white color
                    .border(
                        BorderStroke(2.dp, color = colorResource(id = R.color.black)),
                        shape = RoundedCornerShape(8.dp)
                    ) // Green border
                    .padding(16.dp)
                    .clickable { expanded = !expanded }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedLanguage,
                        style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown Arrow",
                        tint = Color.Gray
                    )
                }
            }

            // Language Selection Dropdown
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        onClick = {
                            selectedLanguage = language
                            expanded = false
                        },
                        text = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = language,
                                    color = if (language == selectedLanguage) Color(0xFFFFA500) else Color.Gray,
                                    fontWeight = if (language == selectedLanguage) FontWeight.Bold else FontWeight.Normal
                                )
                                if (language == selectedLanguage) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Selected",
                                        tint = Color(0xFFFFA500)
                                    )
                                }
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Next Button
            Button(
                onClick = { onSelectBottomBarClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), // Slightly reduced height
                shape = RoundedCornerShape(16.dp), // Rounded corners
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.black)
                )
            ) {
                Text(stringResource(R.string.countinue))
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(onSelectLanguageClick: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Arrow Icon at the top left
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable { /* Handle back action */ }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ) {
            // Logo

            Image(
                painter = painterResource(id = R.drawable.ic_otp), // Add your logo resource
                contentDescription = "MyMalla Logo",
                modifier = Modifier.padding(bottom = 25.dp)
                    .height(200.dp) // Set height to 150 dp
                    .aspectRatio(1f) // Maintain a 1:1 aspect ratio for proportional width
                    .padding(bottom = 16.dp)
            )
            // Title and Instruction
            Text(
                text = "One time password",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )
            Text(
                text = "Code has been sent to your mobile number +91 999999*****",
                style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 2.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )
            Text(
                text = "Please enter verify your code ",
                style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 24.dp)
                    .fillMaxWidth() // Ensure text spans the full width
                    .align(Alignment.Start) // Align text to the start
            )

            // OTP Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 1..4) {
                    OtpTextField()
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            // Login Button
            Button(
                onClick = onSelectLanguageClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), // Slightly reduced height
                shape = RoundedCornerShape(16.dp), // Rounded corners
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.black)
                )
            ) {
                Text("Verify")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Resend Text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.didnt_receive_it), color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Resend",
                    color = colorResource(id = R.color.black),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Handle resend action */ }
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpTextField() {
    // Holds OTP digits, initially all empty
    var otpValue by remember { mutableStateOf(List(4) { "" }) }

    // Create FocusRequesters for each OTP input field
    val focusRequesters = List(4) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally), // Adds spacing between fields
        modifier = Modifier
            .fillMaxWidth() // Makes the row fill the full width
            .padding(horizontal = 8.dp) // Optional, if you want to add padding
    ) {
        // Loop over the OTP fields (4 in this case)
        for (i in otpValue.indices) {
            OutlinedTextField(
                value = otpValue[i],
                onValueChange = { newValue ->
                    // Update the OTP value for the current index
                    if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                        // Update the OTP value at the current index
                        otpValue = otpValue.toMutableList().apply { this[i] = newValue }

                        // Move focus to the next field if it's not the last one
                        if (newValue.isNotEmpty() && i < 3) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    }
                },
                textStyle = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp)
                    .focusRequester(focusRequesters[i]), // Set the focus requester
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (i < 3) focusRequesters[i + 1].requestFocus() // Move to next field
                    }
                )
            )
        }
    }
}








@Preview(showBackground = true)
@Composable
fun PreviewSignin() {
    Signin(
        onLoginClick = { /* Handle login click */ },
        onForgotPasswordClick = { /* Handle forgot password click */ }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewForgotScreen() {
    ForgotScreen(
        onRememberClick = { /* Handle remember password click */ },
        onNextClick = { /* Handle next click */ }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewOtpScreen() {
    OtpScreen(
        onSelectLanguageClick = { /* Handle remember password click */ },


        )
}


@Preview(showBackground = true)
@Composable
fun PreviewSelectLanguageScreen() {
    LanguageSelectionScreen(


        onSelectBottomBarClick = { /* Handle remember password click */ },


        )


}

