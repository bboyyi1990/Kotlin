package com.yi.kotlin.alert;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yi.kotlin.R;

import java.util.List;

/**
 * 仿IOS 选择样式弹窗
 * Created by Yi on 2017/9/11.
 */

public class IOSSelectDialog extends Dialog {

    private TextView mTitle;

    private LinearLayout optionLayout;

    public IOSSelectDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

        getWindow().getAttributes().gravity = Gravity.BOTTOM;
        getWindow().setWindowAnimations(R.style.ios_alert);
//        getWindow().setBackgroundDrawableResource(R.drawable.transparent_bg);
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select, null);
        optionLayout = (LinearLayout) rootView.findViewById(R.id.dialog_select_option_layout);
        mTitle = (TextView) rootView.findViewById(R.id.dialog_title_tv);
        rootView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(rootView);
    }

    public IOSSelectDialog setTitle(String title) {
        mTitle.setText(title);
        mTitle.setVisibility(View.VISIBLE);
        return this;
    }

    public IOSSelectDialog addOptions(final String[] options, final OnSelectDialogListener optionListener) {
        for (int i = 0; i < options.length; i++) {
            View childView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_item, null);
            ((TextView) childView.findViewById(R.id.dialog_item_select_tv)).setText(options[i]);
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (null != optionListener) {
                        optionListener.select((Integer) v.getTag());
                    }
                }
            });
            childView.setTag(i);
            optionLayout.addView(childView);
        }
        return this;
    }

    public IOSSelectDialog addOptions(final List<String> options, final OnSelectDialogListener optionListener) {
        for (int i = 0; i < options.size(); i++) {
            View childView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_item, null);
            ((TextView) childView.findViewById(R.id.dialog_item_select_tv)).setText(options.get(i));
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (null != optionListener) {
                        optionListener.select((Integer) v.getTag());
                    }
                }
            });
            childView.setTag(i);
            optionLayout.addView(childView);
        }
        return this;
    }

}
