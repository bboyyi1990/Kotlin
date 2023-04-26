package com.yi.kotlin.alert

import android.os.Bundle
import android.text.SpannableString
import android.view.View
import androidx.fragment.app.DialogFragment
import com.yi.kotlin.R
import com.yi.kotlin.data.TextData
import kotlinx.android.synthetic.main.dialog_select.dialog_select_option_layout
import kotlinx.android.synthetic.main.dialog_select.dialog_title_tv
import kotlinx.android.synthetic.main.dialog_select.tv_cancel
import kotlinx.android.synthetic.main.dialog_select_item.view.dialog_item_select_tv
import kotlinx.android.synthetic.main.dialog_select_item.view.dialog_item_spilt_line

class CommonSelectDialog : BaseSheetDialogFragment() {
    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.alert_dialog)
    }

    private var title: String? = null
    private val opts = mutableListOf<SpannableString>()
    private var cancelStr: String? = null
    private var callback: ((Int) -> Unit)? = null
    override fun getLayout(): Int = R.layout.dialog_select

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

    fun setCancelText(cancel: String): CommonSelectDialog {
        this.cancelStr = cancel
        return this
    }

    fun addOptions(options: Array<String>, callback: (index: Int) -> Unit): CommonSelectDialog {
        return addOptions(options.toMutableList(), callback)
    }

    fun addOptions(opts: MutableList<String>, callback: (index: Int) -> Unit): CommonSelectDialog {
        val textDataArray = mutableListOf<TextData>()
        opts.forEach { textDataArray.add(TextData(it)) }
        addSpannableOptions(textDataArray, callback)
        return this
    }

    fun addSpannableOptions(opts: MutableList<TextData>, invoke: (index: Int) -> Unit)
            : CommonSelectDialog {
        opts.forEach { this.opts.add(it.getSpannable()) }
        this.callback = invoke
        return this
    }
}