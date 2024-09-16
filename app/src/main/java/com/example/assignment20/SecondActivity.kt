package com.example.assignment20

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment20.ui.theme.Assignment20Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            Assignment20Theme {
                SecondScreen(
                    //Back to main activity
                    onMainClick = {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun SecondScreen(onMainClick: () -> Unit){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            Column (
                modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)
            ) {
                //Displaying the Challenges
                Text(text = "Mobile Software Engineering Challenges:", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "1. Battery efficiency")
                Text(text = "2. Network latency")
                Text(text = "3. UI/UX consistency across devices")
                Text(text = "4. Security concerns")
                Text(text = "5. Handling multiple screen sizes")

                Spacer(modifier = Modifier.height(16.dp))

                // Button back to MainActivity
                Button(onClick = onMainClick) {
                    Text(text = "Main Activity")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview(){
    Assignment20Theme {
        SecondScreen(onMainClick = {})
    }
}