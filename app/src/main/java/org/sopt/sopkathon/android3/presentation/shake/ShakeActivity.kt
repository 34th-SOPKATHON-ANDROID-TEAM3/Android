package org.sopt.sopkathon.android3.presentation.shake

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.sopkathon.android3.R
import org.sopt.sopkathon.android3.databinding.ActivityShakeBinding
import org.sopt.sopkathon.android3.presentation.healing.HealingActivity
import org.sopt.sopkathon.android3.presentation.main.MainActivity
import org.sopt.sopkathon.android3.util.base.BindingActivity
import org.sopt.sopkathon.android3.util.sensor.SensorUtil
import org.sopt.sopkathon.android3.util.view.loadGif

class ShakeActivity: BindingActivity<ActivityShakeBinding>({ActivityShakeBinding.inflate(it)}) {
    private lateinit var mSensorManager: SensorManager
    private var mAccelerometer: Sensor? = null
    private var shakeEnable = true
    private lateinit var sensorUtil: SensorUtil
    private lateinit var vibrator: Vibrator
    private val viewModel by viewModels<ShakeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorUtil = SensorUtil()
        vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        viewModel.shakeState.flowWithLifecycle(lifecycle).onEach {
            when(it){
                ShakeState.BeforeShaking -> {
                    binding.tvShakeBackhome.isGone = true
                    binding.tvShakeHealing.isGone = true
                    binding.tvShakeTitle.text = "휴대폰을 흔들어 돌을 깎아주세요"
                    binding.tvShakeSubtitle.text = "흔들수록 내 고민이 멀리 날아가요"
                }
                ShakeState.CompleteShaking -> {
                    binding.tvShakeBackhome.isVisible = true
                    binding.tvShakeHealing.isVisible = true
                    binding.ivShakeStone.loadGif(R.drawable.gif_stone)
                    shakeEnable = false
                    binding.tvShakeTitle.text = "돌의 모양이 변했어요!"
                    binding.tvShakeSubtitle.text = "오늘 하루도 마음의 평온에\n한 발자국 다가갔네요"
                    viewModel.finishAnimating()
                }
                ShakeState.UntilShaking -> {
                    binding.tvShakeBackhome.isGone = true
                    binding.tvShakeHealing.isGone = true
                    binding.tvShakeTitle.text = "돌을 깎는중"
                    binding.tvShakeSubtitle.text = "부정적인 마음을 떨쳐내요"
                }

                ShakeState.FinishShaking -> {
                    delay(5000)
                    shakeEnable = false
                    binding.ivShakeStone.load(R.drawable.img_pretty_rock)
                    binding.tvShakeTitle.text = "돌의 모양이 변했어요!"
                    binding.tvShakeSubtitle.text = "오늘 하루도 마음의 평온에\n한 발자국 다가갔네요"
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.count.flowWithLifecycle(lifecycle).onEach {
            when{
                it > 30 -> viewModel.endShaking()
            }
        }.launchIn(lifecycleScope)

        viewModel.shakeFlag.flowWithLifecycle(lifecycle).onEach {
            if(it){

                binding.tvShakeTitle.text = "돌의 모양이 변했어요!"
                binding.tvShakeSubtitle.text = "오늘 하루도 마음의 평온에\n한 발자국 다가갔네요"
            }
        }

        binding.tvShakeBackhome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        binding.tvShakeHealing.setOnClickListener {
            val intent = Intent(this, HealingActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(
            sensorEventListener,
            mAccelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        mSensorManager.unregisterListener(sensorEventListener)
        super.onPause()
    }

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {

            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val axisX = event.values[0]
                val axisY = event.values[1]
                val axisZ = event.values[2]


                if (sensorUtil.isShake(axisX, axisY, axisZ) && shakeEnable) {
                    if(viewModel.shakeState.value == ShakeState.BeforeShaking)
                        viewModel.startShaking()
                    viewModel.addCount()
                    vibratePhone()
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }
    }

    private fun vibratePhone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(500)
        }
    }
}