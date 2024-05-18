package org.sopt.sopkathon.android3.presentation.question

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import org.sopt.sopkathon.android3.databinding.ActivityQuestionBinding
import org.sopt.sopkathon.android3.util.base.BindingActivity
import org.sopt.sopkathon.android3.util.context.toast

class QuestionActivity : BindingActivity<ActivityQuestionBinding>({ ActivityQuestionBinding.inflate(it)}) {

    private val viewModel by viewModels<QuestionViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSaveButton()
        observeQuestionState()
    }

    private fun initSaveButton() {
        binding.ivBtnSave.setOnClickListener {
            val answerText = binding.etAnswer.text.toString()
            if (answerText.isNotEmpty()) {
                viewModel.patchAnswer(answerText)
            } else {
                Toast.makeText(this, "답변을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeQuestionState() {
        viewModel.questionState.observe(this) { state ->
            when (state) {
                is QuestionState.Success -> {
                    toast("답변 저장에 성공하였습니다.")
                    //binding.etAnswer.text.clear()
                }
                is QuestionState.Failure -> {
                    toast("오류 발생 : ${state.msg}")
                }
            }
        }
    }

}