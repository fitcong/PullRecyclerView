package com.cc.pullrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cc.pullrecyclerview.pview.listener.ILoadMoreState;


public class LoadingMoreFooter extends LinearLayout implements ILoadMoreState {
    private TextView mText;
    private String loadingHint = "正在加载...";
    private String noMoreHint = "没有更多了...";
    private String loadingDoneHint = "加载完成...";

    public LoadingMoreFooter(Context context) {
        super(context);
        initView();
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setLoadingHint(String hint) {
        loadingHint = hint;
    }

    public void setNoMoreHint(String hint) {
        noMoreHint = hint;
    }

    public void setLoadingDoneHint(String hint) {
        loadingDoneHint = hint;
    }

    public void initView() {
        setGravity(Gravity.CENTER);
        setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mText = new TextView(getContext());
        mText.setText(loadingDoneHint);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        mText.setGravity(Gravity.CENTER);
        mText.setLayoutParams(layoutParams);
        addView(mText);
    }

    @Override
    public void onLoading() {
        mText.setText(loadingHint);
    }

    @Override
    public void onComplete() {
        mText.setText(loadingDoneHint);
    }

    @Override
    public void onNoMore() {
        mText.setText(noMoreHint);
    }

    @Override
    public View getLoadMoreView() {
        return this;
    }
}
