package com.example.assignment20
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UIAutomator {

    private lateinit var device: UiDevice

    @Before
    fun setUp(){
        // initialize device
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // get to home screen
        device.pressHome()
    }

    @Test
    fun testUIAutomator(){
        // Start app from home screen
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage("com.example.assignment20")?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        // Check if intent is available and start the app
        requireNotNull(intent) { "Could not find launch intent for package: com.example.assignment20" }
        context.startActivity(intent)

        // Wait for the app to appear on the screen
        device.wait(Until.hasObject(By.pkg("com.example.assignment20").depth(0)), 10000)

        // Find and click the explicit button
        val explicitButton: UiObject2? = device.findObject(By.desc("button_explicit"))
        explicitButton?.click() ?: throw AssertionError("Explicit button not found")

        // Wait for second activity to appear
        device.wait(Until.hasObject(By.pkg("com.example.assignment20").depth(0)), 10000)

        // Verify that one of the challenges is displayed on the screen
        val challengeText: UiObject2? = device.findObject(By.text("1. Battery efficiency"))
        assertNotNull("Challenge text '1. Battery efficiency' not found", challengeText)
    }
}