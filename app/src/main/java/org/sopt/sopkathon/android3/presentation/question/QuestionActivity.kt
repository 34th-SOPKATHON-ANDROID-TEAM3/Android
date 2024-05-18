package org.sopt.sopkathon.android3.presentation.question

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import org.sopt.sopkathon.android3.R
import org.sopt.sopkathon.android3.databinding.ActivityQuestionBinding
import org.sopt.sopkathon.android3.presentation.shake.ShakeActivity
import org.sopt.sopkathon.android3.util.base.BindingActivity
import org.sopt.sopkathon.android3.util.context.toast

class QuestionActivity :
    BindingActivity<ActivityQuestionBinding>({ ActivityQuestionBinding.inflate(it) }) {
    private val viewModel by viewModels<QuestionViewModel>()
    private var clickable = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvQuestion.text = intent.getStringExtra("question")
        initSaveButton()
        observeQuestionState()
        binding.etAnswer.doAfterTextChanged {
            viewModel.updateClickable(it.toString())
        }
        viewModel.isClickable.observe(this){
            when(it){
                true -> {
                    clickable = true
                    binding.tvSave.setTextColor(getColor(R.color.neutral100))
                    binding.tvSave.setBackgroundResource(R.drawable.shape_green_fill_10_rect)
                }
                false -> {
                    clickable = false
                    binding.tvSave.setTextColor(getColor(R.color.neutral60))
                    binding.tvSave.setBackgroundResource(R.drawable.shape_gray_fill_10_rect)
                }
            }
        }
    }

    private fun initSaveButton() {
        binding.tvSave.setOnClickListener {
            if(clickable){
                val answerText = binding.etAnswer.text.toString()
                if (answerText.isNotEmpty()) {
                    viewModel.patchAnswer(answerText)
                }
            }
        }
    }

    private fun observeQuestionState() {
        viewModel.questionState.observe(this) { state ->
            when (state) {
                is QuestionState.Success -> {
                    val intent = Intent(this, ShakeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }

                is QuestionState.Failure -> {
                    toast("오류 발생 : ${state.msg}")
                }
            }
        }
    }

}