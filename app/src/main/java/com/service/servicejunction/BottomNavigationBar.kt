package com.service.servicejunction

import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.service.servicejunction.food.FoodDetailScreen
import com.service.servicejunction.food.FoodUiScreen
import com.service.servicejunction.food.OrderSummaryScreen
import com.service.servicejunction.food.PaymentOptionScreen
import com.service.servicejunction.history.HistoryScreen
import com.service.servicejunction.home.HomeScreenContent
import com.service.servicejunction.profile.ProfileScreen
import com.service.servicejunction.search.SearchScreen


@RequiresApi(Build.VERSION_CODES.P)
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.wrapContentWidth(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BottomNavigationBar(navigationController: NavHostController) {
//    val items = listOf(
//        Screen.Home,
//        Screen.Search,
//        Screen.Food,
//        Screen.Offer,
//        Screen.Profile
//        )

    val selected = remember {
        mutableIntStateOf(Screen.Home.icon)
    }

    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //For GIF
    val context = LocalContext.current
    val animatedWebp = remember { mutableStateOf<AnimatedImageDrawable?>(null) }


    LaunchedEffect(Unit) {
        val drawable = AppCompatResources.getDrawable(context, R.drawable.giphy)
        if (drawable is AnimatedImageDrawable) {
            animatedWebp.value = drawable
        }
        animatedWebp.value?.start()
    }


    androidx.compose.material3.BottomAppBar(
        containerColor =
//        if(isSystemInDarkTheme())Color(0xFFC91933) else Color(0xFFA4BB26),
        Color.White,
//        contentColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        modifier = Modifier.height(70.dp)
    ) {

        //Home
        IconButton(
            onClick = {
                selected.intValue = Screen.Home.icon
                navigationController.navigate(Screen.Home.route) {
                    popUpTo(navigationController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            BottomBarItem(
                icon = if (currentRoute == Screen.Home.route)R.drawable.baseline_home_24 else Screen.Home.icon,
                "Home",
                color = if (currentRoute == Screen.Home.route) Color.Black else Color.Gray
            )
        }

        //Search
        IconButton(
            onClick = {
                selected.intValue = Screen.Search.icon
                navigationController.navigate(Screen.Search.route) {
                    popUpTo(navigationController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            BottomBarItem(
                icon = if (currentRoute == Screen.Search.route) R.drawable.baseline_search_24 else Screen.Search.icon,
                "Search",
                color = if (currentRoute == Screen.Search.route) Color.Black else Color.Gray
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        //Offer
        IconButton(
            onClick = {
                selected.intValue = Screen.History.icon
                navigationController.navigate(Screen.History.route) {
                    popUpTo(navigationController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            BottomBarItem(
                icon = if (currentRoute == Screen.History.route) R.drawable.baseline_event_note_24 else Screen.History.icon,
                "History",
                color = if (currentRoute == Screen.History.route) Color.Black else Color.Gray
            )
        }

        //Profile
        IconButton(
            onClick = {
                selected.intValue = Screen.Profile.icon
                navigationController.navigate(Screen.Profile.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            BottomBarItem(
                icon = if (currentRoute == Screen.Profile.route) R.drawable.baseline_person_24 else Screen.Profile.icon,
                "Profile",
                color = if (currentRoute == Screen.Profile.route) Color.Black else Color.Gray
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        //Food
        FloatingActionButton(
            onClick = { navigationController.navigate(Screen.Food.route) },
            containerColor = Color.White,
            contentColor = if (currentRoute == Screen.Food.route) Color.Black else Color.Gray,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-30).dp)
                .size(56.dp)
        ) {

            //For GIF
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        animatedWebp.value?.let { setImageDrawable(it) }
                    }
                },
                update = { imageView ->
                    animatedWebp.value?.let { imageView.setImageDrawable(it) }
                }
            )
        }
    }


    //Before
//    Surface(
//        modifier = Modifier.fillMaxWidth(),
//        color = colorResource(id = R.color.white),
//        shape = RoundedCornerShape(16.dp)  // Applying rounded corners with 16.dp radius
//    ) {
//        NavigationBar(
//            containerColor = Color.Transparent,
//            modifier = Modifier.padding(8.dp)
//        ) {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.destination?.route
//            items.forEach { screen ->
//                NavigationBarItem(
//                    label = {
//                        Text(
//                            screen.title,
//                            color = if (currentRoute == screen.route) Color.Black else Color.White,
//                            style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp)
//                        )
//                    },
//                    icon = {
//                        Icon(
//                            painter = painterResource(id = screen.icon),
//                            contentDescription = screen.title,
//                            tint = Color.Unspecified // Preserve original icon colors
//                        )
//                    },
//                    selected = currentRoute == screen.route,
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color.Black,
//                        selectedTextColor = Color.Black,
//                        unselectedIconColor = Color.Gray,
//                        unselectedTextColor = Color.Gray
//                    ),
//                    onClick = {
//                        navController.navigate(screen.route) {
//                            popUpTo(navController.graph.startDestinationId) { saveState = true }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//            }
//        }
//    }
}


@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreenContent() }
        composable(Screen.Search.route) { SearchScreen() }
        composable(Screen.Food.route) { FoodUiScreen(navController) }
        composable(Screen.History.route) { HistoryScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }


        // Route: Login
        composable("fooddetail") {
            FoodDetailScreen(navController)
        }

        composable("foodsummary") {
            OrderSummaryScreen(navController)
        }


        composable("foodsubscription") {
            //PricingScreen(navController)
            OrderSummaryScreen(navController)
        }

        composable("paymentoption") {
            PaymentOptionScreen(navController)
        }


    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreenContent()
    }
}

sealed class Screen(val route: String, val title: String, val icon: Int) {
    data object Home : Screen("home", "Service", R.drawable.outline_home_24)
    data object Search : Screen("search", "Search", R.drawable.baseline_search_24)
    data object Profile : Screen("profile", "Profile", R.drawable.outline_person_24)
    data object History : Screen("history", "History", R.drawable.outline_event_note_24)
    data object Food : Screen("food", "Food", R.drawable.ic_foodicon)

}

//@Composable
//fun BottomBarItem(icon: Int, label: String) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.fillMaxHeight()
//    ) {
//        Icon(
//            painter = painterResource(id = icon),
//            contentDescription = label,
//            tint = Color.White,
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}

@Composable
fun BottomBarItem(icon: Int, label: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Text(
            label,
            color = color,
            style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp)
        )
    }
}