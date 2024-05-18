package org.sopt.sopkathon.android3.presentation.calendar

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sopkathon.android3.databinding.ActivityCalendarBinding
import org.sopt.sopkathon.android3.presentation.calendar.adapter.CalendarAdapter
import org.sopt.sopkathon.android3.presentation.detail.DetailActivity
import org.sopt.sopkathon.android3.util.base.BindingActivity

class CalendarActivity :
    BindingActivity<ActivityCalendarBinding>({ ActivityCalendarBinding.inflate(it) }) {
    private lateinit var calendarAdapter: CalendarAdapter
    private val viewModel by viewModels<CalendarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initCalendarAdapter()
        observeRockList()
        viewModel.fetchRockInfo()
    }

    private fun observeRockList() {
        viewModel.rockList.observe(this) {
            calendarAdapter.submitList(it)
        }
    }

    private fun initCalendarAdapter() {
        calendarAdapter = CalendarAdapter {
            if (!it.isPretty) {
                val calendarWarningDialog = CalendarWarningDialogFragment()
                calendarWarningDialog.show(supportFragmentManager, calendarWarningDialog.tag)
            } else {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(ROCK_ID, it.id)
                startActivity(intent)
            }
        }
        binding.rvCalendar.run {
            adapter = calendarAdapter
        }
    }

    private fun initView() {
        binding.tvCalendarYear.text = "2024"
        binding.tvCalendarMonthNumber.text = "5"
    }

    companion object {
        private const val ROCK_ID = "rockId"
    }
}