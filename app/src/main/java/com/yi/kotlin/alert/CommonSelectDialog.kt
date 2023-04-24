package com.yi.kotlin.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yi.kotlin.R
import kotlinx.android.synthetic.main.dialog_select.*
import kotlinx.android.synthetic.main.dialog_select_item.view.*

class CommonSelectDialog : BottomSheetDialogFragment() {
    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.alert_dialog)
    }

    private var title: String? = null
    private var opts: MutableList<String>? = null
    private var callback: ((Int) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.dialog_select, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_cancel.setOnClickListener { dismiss() }
        //setTitle
        dialog_title_tv.text = title
        dialog_title_tv.visibility = if (title.isNullOrEmpty()) View.GONE else View.VISIBLE
        //setOptions
        opts?.also {
            for ((index, value) in it.withIndex()) {
                val childView = layoutInflater.inflate(R.layout.dialog_select_item, null)
                childView.dialog_item_select_tv.text = value
                childView.dialog_item_spilt_line.visibility = if (index == 0) View.GONE
                else View.VISIBLE
                childView.setOnClickListener { v ->
                    dismiss()
                    callback?.invoke(v.tag as Int)
                }
                childView.tag = index
                dialog_select_option_layout.addView(childView)
            }
        }
    }

    fun setTitle(title: String): CommonSelectDialog {
        this.title = title
        return this
    }

    fun addOptions(options: Array<String>, callback: (index: Int) -> Unit): CommonSelectDialog {
        this.opts = options.toMutableList()
        this.callback = callback
        return this
    }

    fun addOptions(opts: MutableList<String>, callback: (index: Int) -> Unit): CommonSelectDialog {
        this.opts = opts
        this.callback = callback
        return this
    }
}