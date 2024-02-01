package dev.mnzn.conduitandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import dev.mnzn.conduitandroid.ui.theme.ConduitAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConduitAndroidTheme {

                var selectedItem by remember { mutableStateOf(Screen.Home) }
                var text by remember { mutableStateOf(selectedItem.name) }

                Scaffold(
                    bottomBar = {
                        Navigation(selectedItem, onSelectedItemChange = {
                            selectedItem = it
                            text = it.name
                        })
                    },
                    content = { innerPadding ->
                        Text(
                            text = text,
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

enum class Screen { Home, Config, Log }

@Composable
fun Navigation(selectedItem: Screen = Screen.Home, onSelectedItemChange: (Screen) -> Unit) {
    class NavigationItem(val title: String, val icon: ImageVector, val screen: Screen)

    val items = listOf(
        NavigationItem("Home", Icons.Filled.Home, Screen.Home),
        NavigationItem("Config", Icons.Filled.Settings, Screen.Config),
        NavigationItem("Log", Icons.Filled.Info, Screen.Log),
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem.ordinal == index,
                onClick = { onSelectedItemChange(item.screen) }
            )
        }
    }
}