package org.sopt.sopkathon.android3.presentation.dummy

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sopkathon.android3.databinding.ActivityDummyBinding
import org.sopt.sopkathon.android3.util.base.BindingActivity

class DummyActivity : BindingActivity<ActivityDummyBinding>({ ActivityDummyBinding.inflate(it)}) {
    private val viewModel by viewModels<DummyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.dummyState.observe(this){
            when(it){
                is DummyState.Failure -> TODO()
                is DummyState.Success -> TODO()
            }
        }
    }
}