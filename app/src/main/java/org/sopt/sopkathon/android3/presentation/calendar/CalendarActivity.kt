package org.sopt.sopkathon.android3.presentation.calendar

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sopkathon.android3.databinding.ActivityCalendarBinding
import org.sopt.sopkathon.android3.presentation.calendar.adapter.CalendarAdapter
import org.sopt.sopkathon.android3.util.base.BindingActivity
import java.util.Calendar

class CalendarActivity :
    BindingActivity<ActivityCalendarBinding>({ ActivityCalendarBinding.inflate(it) }) {
    private lateinit var calendarAdapter: CalendarAdapter
    private val viewModel by viewModels<CalendarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        calendarAdapter = CalendarAdapter {
            if (!it.isPretty) {
                val calendarWarningDialog = CalendarWarningDialogFragment()
                calendarWarningDialog.show(supportFragmentManager, calendarWarningDialog.tag)
            } else {
                // val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(ROCK_ID, it.id)
                startActivity(intent)
            }
        }
        binding.rvCalendar.run {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(this@CalendarActivity, 5)
        }

        viewModel.fetchRockInfo()
        viewModel.rockList.observe(this) {
            calendarAdapter.submitList(it)
        }
    }

    private fun initView() {
        binding.tvCalendarYear.text = Calendar.getInstance().get(Calendar.YEAR).toString()
        binding.tvCalendarMonthNumber.text = Calendar.getInstance().get(Calendar.MONTH).toString()
    }

    companion object {
        private const val ROCK_ID = "rockId"
    }
}