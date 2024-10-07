package com.example.assignment20

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment20.ui.theme.Assignment20Theme

class ViewImageActivity : ComponentActivity() {

    private var imageBitmap: Bitmap? = null

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            imageBitmap = bitmap
            // Refresh the UI when the image is captured
            setContent {
                Assignment20Theme {
                    ImageCaptureScreen(imageBitmap) { requestCameraPermission() }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment20Theme {
                ImageCaptureScreen(imageBitmap) { requestCameraPermission() }
            }
        }
    }

    private fun requestCameraPermission() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        } else {
            // launch the camera
            cameraLauncher.launch(null)
        }
    }

    companion object {
        const val REQUEST_CAMERA_PERMISSION = 100
    }
}

@Composable
fun ImageCaptureScreen(imageBitmap: Bitmap?, onCaptureClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onCaptureClick) {
            Text(text = "Capture Image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        imageBitmap?.let {
            Image(bitmap = it.asImageBitmap(), contentDescription = "Captured Image", modifier = Modifier.size(300.dp).fillMaxWidth().aspectRatio(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageCaptureScreen() {
    Assignment20Theme {
        ImageCaptureScreen(imageBitmap = null) {}
    }
}
