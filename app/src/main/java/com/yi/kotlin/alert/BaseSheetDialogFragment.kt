package com.yi.kotlin.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yi.kotlin.R

/**
 * this is base bottom sheet dialog fragment
 * if you want user BottomSheetDialogFragment
 * you‘d batter extend this
 */
abstract class BaseSheetDialogFragment : BottomSheetDialogFragment() {
    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog)
    }

    /**
     * lazy view binding for convenient use
     * you must override the property
     */
    open val binding: ViewBinding by lazy { TODO("must implement view binding") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.let {
            it.setCanceledOnTouchOutside(isCancelableOnTouchOutside())
            val id = com.google.android.material.R.id.design_bottom_sheet
            val bottomSheetView = it.findViewById<View>(id)
            val behavior = BottomSheetBehavior.from(bottomSheetView)
            behavior.state = getStatus()
            behavior.isHideable = isHideable()
            behavior.isDraggable = isDraggable()
            behavior.peekHeight = getPeekHeight()
        }
    }

    abstract fun getLayout(): Int

    open fun isHideable() = false

    open fun isDraggable() = true

    open fun getPeekHeight(): Int = BottomSheetBehavior.PEEK_HEIGHT_AUTO

    open fun getStatus(): Int = BottomSheetBehavior.STATE_COLLAPSED

    open fun isCancelableOnTouchOutside() = true

    fun show(fm: FragmentManager?) = fm?.let { show(it, "BaseSheetDialogFragment") }
}