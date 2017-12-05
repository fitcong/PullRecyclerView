package com.cc.pullrecyclerview.pview.listener;

import android.view.View;

/**
 * description: 加载更多的接口回调
 * author: chencong
 * date: 2017/12/5
 */

public interface ILoadMoreState {

    void onLoading();

    void onComplete();

    void onNoMore();

    View getLoadMoreView();

}
