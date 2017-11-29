package com.cc.pullrecyclerview.pview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * description: 新建的一个类
 * author: chencong
 * date: 2017/11/3
 */

public class SimpleArrowView extends BaseArrowView {

    private String TAG = "SimpleArrowView";
    private TextView mText;

    public SimpleArrowView(Context context) {
        this(context,null);
    }

    public SimpleArrowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mText = new TextView(getContext());
        mText.setText("下拉刷新");
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        mText.setGravity(Gravity.CENTER);
        mText.setLayoutParams(layoutParams);
        addView(mText);
    }
    @Override
    public void onRefreshing() {
        Log.d(TAG, "onRefreshing: ");
        mText.setText("onRefreshing");
    }

    @Override
    public void onPulling(int progress, boolean canRefresh) {
        Log.d(TAG, "onPulling: "+progress+"canRefresh:"+canRefresh);
        mText.setText("onPulling: "+progress+"canRefresh:"+canRefresh);
    }
    @Override
    public void onRefreshed() {
        Log.d(TAG, "onRefreshed: ");
        mText.setText( "onRefreshed: ");
    }

    @Override
    public void onCancelRefresh() {
        Log.d(TAG, "onCancelRefresh: ");
        mText.setText( "onCancelRefresh: ");
    }
}
