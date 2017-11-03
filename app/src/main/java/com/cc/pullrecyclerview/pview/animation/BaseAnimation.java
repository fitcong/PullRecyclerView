package com.cc.pullrecyclerview.pview.animation;

import android.animation.Animator;
import android.view.View;

/**
 * description: 动画的基类,如果需要自定义就继承改方法进行复写
 * author: chencong
 * date: 2017/11/2
 */

public abstract class BaseAnimation {

    /**
     *
     * @param view
     * @return
     */
    public abstract Animator getAnimators(View view);

}
