package org.sopt.sopkathon.android3.presentation.main

import android.content.Intent
import android.os.Bundle
import org.sopt.sopkathon.android3.databinding.ActivityMainBinding
import org.sopt.sopkathon.android3.presentation.shake.ShakeActivity
import org.sopt.sopkathon.android3.util.base.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivMainStone.setOnClickListener {
            val intent = Intent(this, ShakeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
