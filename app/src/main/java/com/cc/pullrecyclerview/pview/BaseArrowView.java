package com.cc.pullrecyclerview.pview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.cc.pullrecyclerview.pview.listener.ArrowViewListener;

/**
 * description: 自定义下拉刷新头部需要继承
 * author: chencong
 * date: 2017/11/3
 */

public abstract class BaseArrowView extends LinearLayout implements ArrowViewListener {
    public BaseArrowView(Context context) {
        super(context);
    }

    public BaseArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
