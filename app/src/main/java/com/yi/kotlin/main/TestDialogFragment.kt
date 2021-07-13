package com.yi.kotlin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yi.kotlin.R
import com.yi.kotlin.base.BaseDialogFragment

/**
 * create by yi on 7/13/21
 */
class TestDialogFragment : BaseDialogFragment() {
    companion object {
        fun getInstant(): TestDialogFragment {
            return TestDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_test, container)
    }
}