package com.tw.custombottommenujetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tw.custombottommenujetpack.ui.theme.CustomBottomMenuJetpackTheme


data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomBottomMenuJetpackTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = Color(
                                    ContextCompat.getColor(
                                        applicationContext,
                                        R.color.purple_200
                                    )
                                )
                            ),
                            title = {
                                Text(
                                    text = "Jetpack Toolbar",
                                    fontSize = 14.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.White
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Menu Clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                   // colors = IconButtonColors(containerColor = Color.White, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.White)
                                    ) {
                                    Icon(
                                        Icons.Outlined.Menu,
                                        tint = Color.White,
                                        contentDescription = "menu",
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Notification Clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        Icons.Filled.Notifications,
                                        tint = Color.White,
                                        contentDescription = "Notifications"
                                    )
                                }

                                IconButton(onClick = {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Search Clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        Icons.Filled.Search,
                                        tint = Color.White,
                                        contentDescription = "Search"
                                    )
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                )
                { innerPadding ->
                    CustomBottomMenuJetpackTheme {
                        Scaffold(
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            // setting up the individual tabs
                            val homeTab = TabBarItem(
                                title = "Home",
                                selectedIcon = Icons.Filled.Home,
                                unselectedIcon = Icons.Outlined.Home,
                                badgeAmount = 2
                            )
                            val alertsTab = TabBarItem(
                                title = "Alerts",
                                selectedIcon = Icons.Filled.Notifications,
                                unselectedIcon = Icons.Outlined.Notifications,
                                badgeAmount = 7
                            )
                            val settingsTab = TabBarItem(
                                title = "Settings",
                                selectedIcon = Icons.Filled.Settings,
                                unselectedIcon = Icons.Outlined.Settings
                            )
                            val moreTab = TabBarItem(
                                title = "More",
                                selectedIcon = Icons.Filled.List,
                                unselectedIcon = Icons.Outlined.List
                            )

                            // creating a list of all the tabs
                            val tabBarItems = listOf(homeTab, alertsTab, settingsTab, moreTab)

                            // creating our navController
                            val navController = rememberNavController()

                            CustomBottomMenuJetpackTheme {
                                // A surface container using the 'background' color from the theme
                                Surface(
                                    modifier = Modifier.fillMaxSize(),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    Scaffold(bottomBar = { TabView(tabBarItems, navController) }) {
                                        NavHost(
                                            navController = navController,
                                            startDestination = homeTab.title
                                        ) {
                                            composable(homeTab.title) {
                                                Column(
                                                    modifier = Modifier.fillMaxSize(),
                                                    verticalArrangement = Arrangement.Center,
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ){
                                                    Text(homeTab.title)
                                                }
                                            }
                                            composable(alertsTab.title) {
                                                Text(alertsTab.title)
                                            }
                                            composable(settingsTab.title) {
                                                Text(settingsTab.title)
                                            }
                                            composable(moreTab.title) {
                                                MoreView()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    // ----------------------------------------
// This is a wrapper view that allows us to easily and cleanly
// reuse this component in any future project
    @Composable
    fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
        var selectedTabIndex by rememberSaveable {
            mutableStateOf(0)
        }

        NavigationBar {
            // looping over each tab to generate the views and navigation for each item
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.title)
                    },
                    icon = {
                        TabBarIconView(
                            isSelected = selectedTabIndex == index,
                            selectedIcon = tabBarItem.selectedIcon,
                            unselectedIcon = tabBarItem.unselectedIcon,
                            title = tabBarItem.title,
                            badgeAmount = tabBarItem.badgeAmount
                        )
                    },
                    label = { Text(tabBarItem.title) })
            }
        }
    }

}

    // This component helps to clean up the API call from our TabView above,
// but could just as easily be added inside the TabView without creating this custom component
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TabBarIconView(
        isSelected: Boolean,
        selectedIcon: ImageVector,
        unselectedIcon: ImageVector,
        title: String,
        badgeAmount: Int? = null
    ) {
        BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
            Icon(
                imageVector = if (isSelected) {selectedIcon} else {unselectedIcon},
                contentDescription = title
            )
        }
    }

    // This component helps to clean up the API call from our TabBarIconView above,
// but could just as easily be added inside the TabBarIconView without creating this custom component
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun TabBarBadgeView(count: Int? = null) {
        if (count != null) {
            Badge {
                Text(count.toString())
            }
        }
    }
// end of the reusable components that can be copied over to any new projects
// ----------------------------------------

    // This was added to demonstrate that we are infact changing views when we click a new tab
    @Composable
    fun MoreView() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Thing 1")
            Text("Thing 2")
            Text("Thing 3")
            Text("Thing 4")
            Text("Thing 5")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CustomBottomMenuJetpackTheme {
            MoreView()
        }
    }