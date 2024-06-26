package org.sopt.sopkathon.android3.util.sensor

import android.hardware.SensorManager
import kotlin.math.pow
import kotlin.math.sqrt

class SensorUtil {
    private var shakeTime = 0L
    private var shakeCount = 0

    fun init() {
        shakeTime = 0L
        shakeCount = 0
    }

    fun isShake(axisX: Float, axisY: Float, axisZ: Float): Boolean {
        val gravityX = axisX / SensorManager.GRAVITY_EARTH
        val gravityY = axisY / SensorManager.GRAVITY_EARTH
        val gravityZ = axisZ / SensorManager.GRAVITY_EARTH

        val fow = gravityX.pow(2) + gravityY.pow(2) + gravityZ.pow(2)
        val sqrt = sqrt(fow.toDouble())

        if (sqrt.toFloat() > SHAKE_THRESHOLD_GRAVITY) {
            val currentTime = System.currentTimeMillis()
            if (shakeTime + DELAY_SHAKE > currentTime) {
                shakeCount++
            }
            shakeTime = currentTime
        }

        if (shakeCount >= SHAKE_ACCESS_COUNT) {
            init()
            return true
        }
        return false
    }

    companion object {
        private const val DELAY_SHAKE = 500
        private const val SHAKE_THRESHOLD_GRAVITY = 1.5f
        private const val SHAKE_ACCESS_COUNT = 2
    }
}