package com.yi.kotlin.alert;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.yi.kotlin.R;


/**
 * 仿IOS 确认样式弹窗
 *
 * @author Yi
 * @date 2017/9/9
 */

public class IOSConfirmDialog extends Dialog implements View.OnClickListener {

    private OnConfirmDialogListener mCancelListener;
    private OnConfirmDialogListener mConfirmListener;

    private TextView mTitle;
    private TextView mContent;
    private EditText mEdit;
    private TextView mSpiltLine;

    private TextView mConfirm;
    private TextView mCancel;

    public String input;

    public boolean clickDismiss = true;

    public IOSConfirmDialog(Context context) {
        super(context, R.style.alert_dialog);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.ios_dialog_confirom, null);
        mTitle = (TextView) rootView.findViewById(R.id.tv_title);
        mEdit = (EditText) rootView.findViewById(R.id.et_input);
        mContent = (TextView) rootView.findViewById(R.id.tv_content);
        mConfirm = (TextView) rootView.findViewById(R.id.tv_sure);
        mSpiltLine = (TextView) rootView.findViewById(R.id.dialog_button_spilt_line);
        mConfirm.setOnClickListener(this);
        mCancel = (TextView) rootView.findViewById(R.id.tv_cancel);
        mCancel.setOnClickListener(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(rootView);
    }

    public IOSConfirmDialog setCancel(boolean cancelable) {
        setCancelable(cancelable);
        return this;
    }

    public IOSConfirmDialog setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            mTitle.setVisibility(View.GONE);
        } else {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public IOSConfirmDialog setClickDismiss(boolean dismiss) {
        this.clickDismiss = dismiss;
        return this;
    }

    public IOSConfirmDialog setContent(String content) {
        mContent.setText(content);
        mContent.setVisibility(View.VISIBLE);
        mEdit.setVisibility(View.GONE);
        return this;
    }

    public IOSConfirmDialog showInput() {
        mContent.setVisibility(View.GONE);
        mEdit.setVisibility(View.VISIBLE);
        return this;
    }

    public IOSConfirmDialog setConfirmText(String confirmText) {
        mConfirm.setText(confirmText);
        return this;
    }

    public IOSConfirmDialog setConfirmTextColor(int color) {
        mConfirm.setTextColor(color);
        return this;
    }

    public IOSConfirmDialog setCancelText(String cancelText) {
        if (!TextUtils.isEmpty(cancelText)) {
            mCancel.setText(cancelText);
            mCancel.setVisibility(View.VISIBLE);
            mSpiltLine.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public IOSConfirmDialog setCancelTextColor(int color) {
        mCancel.setTextColor(color);
        return this;
    }

    public IOSConfirmDialog setConfirmListener(OnConfirmDialogListener confirmListener) {
        this.mConfirmListener = confirmListener;
        return this;
    }

    public IOSConfirmDialog setCancelListener(OnConfirmDialogListener cancelListener) {
        this.mCancelListener = cancelListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (clickDismiss) {
            dismiss();
        }
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            if (null != mCancelListener) {
                mCancelListener.onDialogClick(this);
            }
        } else if (i == R.id.tv_sure) {
            input = String.valueOf(mEdit.getText());
            if (null != mConfirmListener) {
                mConfirmListener.onDialogClick(this);
            }
        }
    }
}
