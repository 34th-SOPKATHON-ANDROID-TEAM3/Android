package org.sopt.sopkathon.android3.presentation.question

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import org.sopt.sopkathon.android3.databinding.ActivityQuestionBinding
import org.sopt.sopkathon.android3.presentation.shake.ShakeActivity
import org.sopt.sopkathon.android3.util.base.BindingActivity
import org.sopt.sopkathon.android3.util.context.toast

class QuestionActivity : BindingActivity<ActivityQuestionBinding>({ ActivityQuestionBinding.inflate(it)}) {

    private val viewModel by viewModels<QuestionViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvQuestion.text = intent.getStringExtra("question")
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