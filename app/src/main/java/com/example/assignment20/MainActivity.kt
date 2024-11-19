package com.example.assignment20

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.assignment20.ui.theme.Assignment20Theme


class MainActivity : ComponentActivity() {


    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted
                launchSecondActivity()
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "Permission is required to access this feature.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Assignment20Theme {
                MainScreen(
                    onExplicitClick = {
                        checkAndRequestPermission()
                    },
                    onImplicitClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://example.com"))
                        startActivity(intent)
                    },
                    onViewImageClick = {
                        val intent = Intent(this, ViewImageActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }

    private fun checkAndRequestPermission() {
        val permission = "com.example.assignment20.MSE412"

        when {
            // Check if the permission is already granted
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                // Permission is already granted, proceed with launching SecondActivity
                launchSecondActivity()
            }
            // If permission is denied previously, explain why you need it
            ActivityCompat.shouldShowRequestPermissionRationale(this, permission) -> {
                // Show a rationale to the user (e.g., via a dialog) before requesting the permission again
                showPermissionRationale {
                    requestPermissionLauncher.launch(permission)
                }
            }
            // Request the permission
            else -> {
                requestPermissionLauncher.launch(permission)
            }
        }
    }

    private fun launchSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun showPermissionRationale(onRationaleAccepted: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Permission Required")
            .setMessage("The app needs permission to access the Mobile Software Engineering challenges.")
            .setPositiveButton("OK") { _, _ -> onRationaleAccepted() }
            .setNegativeButton("Cancel", null)
            .show()
    }


}



@Composable
fun MainScreen(onExplicitClick: () -> Unit, onImplicitClick: () -> Unit, onViewImageClick: () -> Unit) {
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
                Button(onClick = onExplicitClick,
                modifier = Modifier.semantics{ contentDescription = "button_explicit"})
                {
                    Text(text = "Explicit")
                }
                Spacer(modifier = Modifier.height(16.dp))

                //Implicit button
                Button(onClick = onImplicitClick) {
                    Text(text = "Implicit")
                }
                Spacer(modifier = Modifier.height(16.dp))

                //View Image button
                Button(onClick = onViewImageClick) {
                    Text(text = "View Image")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Assignment20Theme {
        MainScreen(onExplicitClick = {}, onImplicitClick = {}, onViewImageClick = {})
}
}