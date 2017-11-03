package com.cc.pullrecyclerview.pview;

/**
 * description: 自定义下拉布局必须要实现的接口
 * author: chencong
 * date: 2017/11/2
 */

public interface ArrowViewListener {

    /**
     * 正在加载
     */
    void onRefreshing();

    /**
     * 下拉进度
     *
     * @param progress   下拉进度,该数值会跟随用户的下拉而变动
     * @param canRefresh 是否已经到达可以松开加载的程度
     */
    void onPulling(int progress, boolean canRefresh);

    /**
     * 已经完成刷新,进行回弹的动画
     */
    void onRefreshed();

    /**
     * 用户取消的下拉刷新
     */
    void onCancelRefresh();

}
