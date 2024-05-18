package org.sopt.sopkathon.android3.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import coil.load
import org.sopt.sopkathon.android3.data.model.response.ResponseGetStoneDetailDto
import org.sopt.sopkathon.android3.databinding.ActivityDetailBinding
import org.sopt.sopkathon.android3.util.base.BindingActivity

class DetailActivity :
    BindingActivity<ActivityDetailBinding>({ ActivityDetailBinding.inflate(it) }) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stoneId = intent.getIntExtra("rockId", -1)
        if (stoneId != -1) {
            viewModel.fetchDetailData(stoneId)
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.detailData.observe(this, Observer { data ->
            bindData(data)
        })
    }

    private fun bindData(data: ResponseGetStoneDetailDto) {
        binding.textViewNumber.text = "#${data.id}"
        binding.textViewQuestion.text = data.question
        binding.textViewAnswer.text = data.answer
        binding.imgRock.load(data.stoneImage)
    }

}