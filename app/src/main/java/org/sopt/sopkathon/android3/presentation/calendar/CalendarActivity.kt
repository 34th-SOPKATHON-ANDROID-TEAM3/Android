package org.sopt.sopkathon.android3.presentation.calendar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sopkathon.android3.R
import org.sopt.sopkathon.android3.databinding.ActivityCalendarBinding
import org.sopt.sopkathon.android3.presentation.calendar.adapter.CalendarAdapter
import org.sopt.sopkathon.android3.presentation.dummy.DummyState
import org.sopt.sopkathon.android3.presentation.dummy.DummyViewModel
import org.sopt.sopkathon.android3.util.base.BindingActivity
import java.util.Calendar

class CalendarActivity : BindingActivity<ActivityCalendarBinding>({ActivityCalendarBinding.inflate(it)}) {
    private lateinit var calendarAdapter: CalendarAdapter
    private val viewModel by viewModels<CalendarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickCloseBtn()
        initView()
        binding.rvCalendar.run {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(this@CalendarActivity, 4)
        }
    }

    private fun initView() {
        binding.tvCalendarMonthNumber.text = Calendar.getInstance().get(Calendar.MONTH).toString()
    }
    private fun clickCloseBtn() {
        binding.ivCalendarClose.setOnClickListener {
            finish()
        }
    }
}