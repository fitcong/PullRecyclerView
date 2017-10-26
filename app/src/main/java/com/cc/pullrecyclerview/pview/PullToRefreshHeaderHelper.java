package com.cc.pullrecyclerview.pview;

/**
 * description: 头部下拉
 * author: chencong
 * date: 2017/10/24
 */

public interface PullToRefreshHeaderHelper  {
    int STATE_NORMAL = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;


    void onMove(int dy);

    boolean releaseAction();

    void onRefreshComplete();

}
