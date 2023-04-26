package com.yi.kotlin.data

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

class TextData {
    var text: String? = null
    var color: Int? = null
    var size: Int? = null //dp
    var bold = false

    constructor(text: String?) {
        this.text = text
    }

    constructor(text: String?, color: Int?) {
        this.text = text
        this.color = color
    }


    fun getSpannable(): SpannableString {
        val ss = SpannableString(text)
        val length = text?.length ?: 0
        //set color
        color?.let {
            ss.setSpan(ForegroundColorSpan(it), 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        //set bold
        if (bold)
            ss.setSpan(StyleSpan(Typeface.BOLD), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) //粗体
        else
            ss.setSpan(StyleSpan(Typeface.NORMAL), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) //粗体
        //set size
        size?.let {
            ss.setSpan(AbsoluteSizeSpan(it, true), 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        return ss
    }
}