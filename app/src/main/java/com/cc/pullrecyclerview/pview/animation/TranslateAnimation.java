package com.cc.pullrecyclerview.pview.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * description: 实现的动画类
 * author: chencong
 * date: 2017/11/2
 */

public class TranslateAnimation extends BaseAnimation {
    private static final float DEFAULT_TRAN = 400f;

    private final float mTran;

    public TranslateAnimation() {
        this(DEFAULT_TRAN);
    }

    public TranslateAnimation(float mTran) {
        this.mTran = mTran;
    }


    @Override
    public Animator getAnimators(View view) {
        return ObjectAnimator.ofFloat(view,"translationY",mTran,0f);
    }

}
