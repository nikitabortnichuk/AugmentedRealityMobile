package com.bortnichuk.lab3

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorAccelerometer: Sensor
    private lateinit var sensorMagnetometer: Sensor

    private var accelerometerData = FloatArray(3)
    private var magnetometerData = FloatArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        loadPage()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun loadPage() {
        val encodedHtml = Base64.encodeToString(htmlString.toByteArray(), Base64.NO_PADDING)

        myWebView.settings.javaScriptEnabled = true
        myWebView.loadData(encodedHtml, "text/html", "base64")
    }

    override fun onStart() {
        super.onStart()
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, sensorMagnetometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onSensorChanged(event: SensorEvent?) {
        when(event?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> filterLow(accelerometerData, event.values.clone())
            Sensor.TYPE_MAGNETIC_FIELD -> filterLow(magnetometerData, event.values.clone())
        }

        val rotationMatrix = FloatArray(9)

        val isRotated = SensorManager.getRotationMatrix(
            rotationMatrix,
            null, accelerometerData, magnetometerData
        )
        val orientationValues = FloatArray(3)
        if (isRotated) {
            SensorManager.getOrientation(rotationMatrix, orientationValues)
        }

        val (x, y, z) = orientationValues
        myWebView.loadUrl("javascript:setAxis($x, $y, $z)")
    }

    private fun filterLow(gravity: FloatArray, eventValues: FloatArray) {
        val alpha = 0.000000001f
        gravity[0] = alpha * gravity[0] + (1 - alpha) * eventValues[0]
        gravity[1] = alpha * gravity[1] + (1 - alpha) * eventValues[1]
        gravity[2] = alpha * gravity[2] + (1 - alpha) * eventValues[2]
    }
}