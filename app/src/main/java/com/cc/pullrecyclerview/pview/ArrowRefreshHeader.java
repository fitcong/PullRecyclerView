package com.cc.pullrecyclerview.pview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Date;

public class ArrowRefreshHeader extends LinearLayout implements PullToRefreshHeaderHelper {
    private int mState = STATE_NORMAL;
    public int mMeasuredHeight;
    private BaseArrowView mView;
    private String TAG = "ArrowRefreshHeader";
    private int distance = 0;

    public ArrowRefreshHeader(Context context) {
        super(context);
        initView();
    }

    /**
     * @param context
     * @param attrs
     */
    public ArrowRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        // 初始情况，设置下拉刷新view高度为0
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);
        setGravity(Gravity.BOTTOM);

    }
    public void setChildView(@Nullable BaseArrowView view) {
        if(view.getParent()!=null)
           throw new RuntimeException("ArrowView has parent");
        mView = view;
        addView(mView, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight();
    }


    public void setState(int state) {
        if (mView == null)
            return;
        if (state == mState) return;
        switch (state) {
            case STATE_NORMAL:
                if (mState == STATE_RELEASE_TO_REFRESH) {
                    mView.onCancelRefresh();
                } else if (mState == STATE_REFRESHING) {
                    mView.onRefreshing();
                }
                break;
            case STATE_RELEASE_TO_REFRESH:
//                mView.onPulling(mMeasuredHeight, true);
                break;
            case STATE_REFRESHING:
                smoothScrollTo(mMeasuredHeight);
                mView.onRefreshing();
                break;
            case STATE_DONE:
                mView.onRefreshed();
                break;
            default:
        }
        mState = state;
    }

    public int getState() {
        return mState;
    }


    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LayoutParams lp = (LayoutParams) mView.getLayoutParams();
        lp.height = height;
        mView.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams) mView.getLayoutParams();
        return lp.height;
    }


    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_NORMAL);
            }
        }, 500);
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public static String friendlyTime(Date time) {
        //获取time距离当前的秒数
        int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);

        if (ct == 0) {
            return "刚刚";
        }

        if (ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        }
        if (ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = ct / 86400;
            return day + "天前";
        }
        if (ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

    @Override
    public void onMove(int dy) {
        if (getVisibleHeight() > 0 || dy > 0) {
            setVisibleHeight(dy + getVisibleHeight());
            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);
                    mView.onPulling(dy + getVisibleHeight(), true);
                } else {
                    setState(STATE_NORMAL);
                    mView.onPulling(dy + getVisibleHeight(), false);

                }
            }
        }
    }

    @Override
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisibleHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;
        if (getVisibleHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
            //return;
        }
        if (mState != STATE_REFRESHING) {
            smoothScrollTo(0);
        }
        if (mState == STATE_REFRESHING) {
            int destHeight = mMeasuredHeight;
            smoothScrollTo(destHeight);
        }
        return isOnRefresh;
    }

    @Override
    public void onRefreshComplete() {
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 200);
    }

}