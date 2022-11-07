package com.happiestminds.my_reminder_app

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dlg: Dialog? = null
        //retrieve bundle
        val message = arguments?.getString("msg")
        //create dialog here
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
            builder.setMessage(message)
            builder.setPositiveButton("Yes") { dialog, i ->                //executed when the button is clicked
                activity?.finish()

            }
            builder.setNeutralButton("Cancel") { dialog, i ->
                dialog.cancel()
            }
            builder.setNegativeButton("No") { dialog, i ->
                dialog.cancel()
            }
            dlg=builder.create()
        }
        return dlg!!
    }
}