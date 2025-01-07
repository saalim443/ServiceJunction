package com.service.theservicejunction.ui.UI.intro

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.service.servicejunction.R

import kotlinx.coroutines.launch
import kotlin.math.abs

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen(
//    onNavigateToSignin: () -> Unit
    onLoginClick: () -> Unit ,
    onForgotPasswordClick: () -> Unit

) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
//                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Horizontal Pager with animations
            HorizontalPager(
                count = 4, // 4 screens
                state = pagerState,
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                itemSpacing = 16.dp // Add spacing between pages
            ) { page ->
                // Page offset for animation
                val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                val scale = 1f - 0.15f * abs(pageOffset) // Scale down adjacent pages
                val alpha = 1f - 0.5f * abs(pageOffset) // Fade effect for adjacent pages

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        },
                    contentAlignment = Alignment.Center
                ) {
                    when (page) {
                        0 -> IntroPage(
                            title = "Delicious Food at Your Doorstep",
                            description = "Order fresh and delicious meals, delivered right to your door.",
                            imageRes = R.drawable.img_into_1
                        )

                        1 -> IntroPage(
                            title = "Professional Cleaning Services",
                            description = "Let us make your home or office spotless and shining.",
                            imageRes = R.drawable.img_into_2
                        )

                        2 -> IntroPage(
                            title = "Hassle-Free Washing Services",
                            description = "Get your laundry cleaned and delivered fresh and neat.",
                            imageRes = R.drawable.img_into_3
                        )

                        3 -> IntroPage(
                            title = "24x7 Service Available",
                            description = "We’re here for you anytime, ensuring services are available whenever you need them.",
                            imageRes = R.drawable.img_into_1
                        )
                    }
                }
            }

            // Pager Indicator
//            HorizontalPagerIndicator(
//                pagerState = pagerState,
//                activeColor = colorResource(R.color.black),
//                inactiveColor = Color.Gray,
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(16.dp)
//            )

//            // Navigation Buttons
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                TextButton(onClick = { /* Handle skip action */ }) {
//                    Text(
//                        text = "Skip",
//                        color = colorResource(R.color.black),
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//                Button(
//                    onClick = {
//                        if (pagerState.currentPage < 3) {
//                            coroutineScope.launch {
//                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                            }
//                        } else {
//                            // Trigger navigation to Signin screen
//                            onNavigateToSignin()
//                        }
//                    },
//                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.black)),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(
//                        text = if (pagerState.currentPage == 3) "Get Started" else "Next",
//                        color = Color.White
//                    )
//                }
//            }

            LoginSection(onLoginClick, onForgotPasswordClick)
        }

    }
}

@Composable
fun IntroPage(title: String, description: String, imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}



// Extension to calculate page offset
@ExperimentalPagerApi
private fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffset
}

@Composable
fun LoginSection(onLoginClick: () -> Unit , onForgotPasswordClick: () -> Unit){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isFocused by remember { mutableStateOf(false) }


    // Animated height for the screen
    val animatedHeight by animateDpAsState(
        targetValue = if (isFocused) 800.dp else 200.dp, // Adjust as needed
        animationSpec = tween(durationMillis = 4000), // Smooth transition
        label = "ExpandingAnimation"
    )

    // Animated width for the screen
    val animatedWidth by animateDpAsState(
        targetValue = if (isFocused) 400.dp else 280.dp, // Adjust as needed
        animationSpec = tween(durationMillis = 4000), // Smooth transition
        label = "ExpandingAnimation"
    )

    Column(
        modifier = Modifier
            .height(animatedHeight)
            .width(animatedWidth)
            .background(Color(0xFFEEEBEB), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Email/Username TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Mobile number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 10.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
            , // Set the height explicitly for a smaller input field
            shape = RoundedCornerShape(16.dp), // Rounded corners
            singleLine = true
        )

        if (isFocused){
            // Forgot Password
            Text(
                text = "Forgot Password?",
                color = Color.Gray,
                fontSize = 12.sp, // Slightly smaller font size
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .clickable { onForgotPasswordClick() }
            )

            // Login Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(40.dp), // Slightly reduced height
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
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 10.dp), // Smaller vertical padding
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
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp), // Reduced bottom padding
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

        }


        // Create Account Link
        Row(
            modifier = Modifier.
            fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account yet? ",
                color = Color.Gray,
                fontSize = 10.sp
            )
            Text(
                text = "Create an account",
                color = colorResource(id = R.color.black),
                fontSize = 10.sp
            )
        }
    }
}
