package com.example.assignment20

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment20.ui.theme.Assignment20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Assignment20Theme {
                MainScreen(
                    onExplicitClick = {
                        val intent = Intent(this, SecondActivity::class.java)
                        startActivity(intent)
                    },
                    onImplicitClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://example.com"))
                        startActivity(intent)
                    }
                )
                }
            }
        }
    }

@Composable
fun MainScreen(onExplicitClick: () -> Unit, onImplicitClick: () -> Unit) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            Column (
                modifier = Modifier.padding(padding).fillMaxSize().padding(13.dp)
            ){
                //Display name and student ID
                Text(text = "Name: Andrew Ambrosier", style = MaterialTheme.typography.headlineSmall)
                Text(text = "Student ID: 1339100", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(13.dp))

                //Explicit button
                Button(onClick = onExplicitClick) {
                    Text(text = "Explicit")
                }
                Spacer(modifier = Modifier.height(16.dp))

                //Implicit button
                Button(onClick = onImplicitClick) {
                    Text(text = "Implicit")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Assignment20Theme {
        MainScreen(onExplicitClick = {}, onImplicitClick = {})
}
}