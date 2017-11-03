package com.cc.pullrecyclerview.pview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * description: 新建的一个类
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
