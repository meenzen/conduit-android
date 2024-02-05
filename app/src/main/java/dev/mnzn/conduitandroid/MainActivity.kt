package dev.mnzn.conduitandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import dev.mnzn.conduitandroid.ui.theme.ConduitAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConduitAndroidTheme {

                val items = listOf(
                    NavigationItem("Home", Icons.Filled.Home) { HomeScreen() },
                    NavigationItem("Config", Icons.Filled.Settings) { ConfigScreen() },
                    NavigationItem("Log", Icons.Filled.Info) { LogScreen() }
                )

                var selectedItem by remember { mutableStateOf(items.first()) }

                Scaffold(
                    bottomBar = {
                        Navigation(items, onSelectedItemChange = {
                            selectedItem = it
                        })
                    },
                    content = { innerPadding ->
                        Surface(
                            content = selectedItem.content,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                                .wrapContentSize()
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = { /*TODO*/ }) {
                        Text("Start Conduit")
                    }
                }
            }
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = { /*TODO*/ }) {
                        Text("Start Cloudflared")
                    }
                }

            }
        })
}

@Composable
fun ConfigScreen() {
    Text("Config")
}

@Composable
fun LogScreen() {
    Text("Log")
}

class NavigationItem(val title: String, val icon: ImageVector, val content: @Composable () -> Unit)

@Composable
fun Navigation(items: List<NavigationItem>, onSelectedItemChange: (NavigationItem) -> Unit) {
    NavigationBar {
        var selectedItem by remember { mutableStateOf(items.first()) }
        items.forEachIndexed { _, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == item,
                onClick = {
                    selectedItem = item
                    onSelectedItemChange(item)
                }
            )
        }
    }
}