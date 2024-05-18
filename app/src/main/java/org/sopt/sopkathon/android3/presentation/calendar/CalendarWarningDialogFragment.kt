package org.sopt.sopkathon.android3.presentation.calendar

import android.os.Bundle
import android.view.View
import org.sopt.sopkathon.android3.databinding.FragmentCalendarWarningDialogBinding
import org.sopt.sopkathon.android3.util.base.BindingDialogFragment
import org.sopt.sopkathon.android3.util.context.dialogFragmentResize

class CalendarWarningDialogFragment : BindingDialogFragment<FragmentCalendarWarningDialogBinding>({
    FragmentCalendarWarningDialogBinding.inflate(it)
}) {
    override fun onStart() {
        super.onStart()
        requireActivity().dialogFragmentResize(this, 50f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmBtnClick()
    }

    private fun confirmBtnClick() {
        binding.btnCalendarDialogConfirm.setOnClickListener {
            dismiss()
        }
    }
}