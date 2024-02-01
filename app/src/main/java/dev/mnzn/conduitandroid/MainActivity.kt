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
import androidx.compose.runtime.mutableIntStateOf
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

                Scaffold (
                    bottomBar = { Navigation() },
                    content = { innerPadding ->
                        Text(
                            text = "Body content",
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
fun Navigation() {
    class NavigationItem(val title: String, val icon: ImageVector)

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        NavigationItem("Home", Icons.Filled.Home),
        NavigationItem("Config", Icons.Filled.Settings),
        NavigationItem("Log", Icons.Filled.Info),
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}