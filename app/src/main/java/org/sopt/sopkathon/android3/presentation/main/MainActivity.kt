package org.sopt.sopkathon.android3.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import org.sopt.sopkathon.android3.databinding.ActivityMainBinding
import org.sopt.sopkathon.android3.presentation.calendar.CalendarActivity
import org.sopt.sopkathon.android3.presentation.healing.HealingActivity
import org.sopt.sopkathon.android3.presentation.question.QuestionActivity
import org.sopt.sopkathon.android3.presentation.shake.ShakeActivity
import org.sopt.sopkathon.android3.util.base.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getTodayStone()

        viewModel.stoneData.observe(this){
            if(it.answer == null){
                binding.ivMainStone.load(it.uglyImage)
                binding.tvMainTitle.text = "오늘의 도를 깎아볼까요?"
                binding.tvMainSubtitle.text = "울퉁불퉁 돌을 눌러 도를 깎으러 가보아요!"
                binding.tvMainCheckHealing.isGone = true
            } else {
                binding.ivMainStone.load(it.prettyImage)
                binding.tvMainTitle.text = "오늘 깎은 돌"
                binding.tvMainSubtitle.text = "반질반질 둥근 돌"
                binding.tvMainCheckHealing.isVisible = true
            }
        }

        binding.ivMainStone.setOnClickListener {
            if(viewModel.stoneData.value?.answer == null){
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("question", viewModel.stoneData.value?.question)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.ivMainCalendar.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.tvMainCheckHealing.setOnClickListener {
            val intent = Intent(this, HealingActivity::class.java)
            startActivity(intent)
        }
    }
}
