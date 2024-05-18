package org.sopt.sopkathon.android3.presentation.shake

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.sopkathon.android3.databinding.ActivityShakeBinding
import org.sopt.sopkathon.android3.presentation.healing.HealingActivity
import org.sopt.sopkathon.android3.util.base.BindingActivity
import org.sopt.sopkathon.android3.util.sensor.SensorUtil

class ShakeActivity: BindingActivity<ActivityShakeBinding>({ActivityShakeBinding.inflate(it)}) {
    private lateinit var mSensorManager: SensorManager
    private var mAccelerometer: Sensor? = null
    private var shakeEnable = true
    private lateinit var sensorUtil: SensorUtil
    private val viewModel by viewModels<ShakeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorUtil = SensorUtil()
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        viewModel.shakeState.flowWithLifecycle(lifecycle).onEach {
            when(it){
                ShakeState.BeforeShaking -> {
                    binding.tvMainTitle.text = "휴대폰을 흔들어 돌을 깎아주세요"
                    binding.tvMainSubtitle.text = "흔들수록 내 고민이 멀리 날아가요"
                }
                ShakeState.CompleteShaking -> {
                    shakeEnable = false
                    binding.tvMainTitle.text = "돌의 모양이 변했어요!"
                    binding.tvMainSubtitle.text = "오늘 하루도 마음의 평온에\n한 발자국 다가갔네요"
                }
                ShakeState.UntilShaking -> {
                    binding.tvMainTitle.text = "돌을 깎는중"
                    binding.tvMainSubtitle.text = "부정적인 마음을 떨쳐내요"
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.count.flowWithLifecycle(lifecycle).onEach {
            when{
                it in 0.. 9 -> {binding.tvMainDate.text = "1"}
                it in 10.. 19 -> {binding.tvMainDate.text = "2"}
                it in 20..29 -> {binding.tvMainDate.text = "3"}
                it in 30..39 -> {binding.tvMainDate.text = "4"}
                it in 40..49 -> viewModel.endShaking()
            }
        }.launchIn(lifecycleScope)

        binding.tvShakeHealingCard.setOnClickListener {
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
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }
    }
}