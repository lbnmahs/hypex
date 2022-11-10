package com.example.hypex.dialog

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.hypex.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.setupBottomDialogSheet(
    onSendClick : (String) -> Unit
){
    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
    val view = layoutInflater.inflate(R.layout.reset_password_dialog, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val edEmail = view.findViewById<EditText>(R.id.edResetPassword)
    val btnSend = view.findViewById<EditText>(R.id.btnSendResetPassword)
    val btnCancel = view.findViewById<EditText>(R.id.btnCancelResetPassword)

    btnSend.setOnClickListener {
        val email = edEmail.text.toString()
        onSendClick(email)
        dialog.dismiss()
    }
    btnCancel.setOnClickListener {
        dialog.dismiss()
    }
}