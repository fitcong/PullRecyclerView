package com.cc.pullrecyclerview.pview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * description: 简易的分割线实现,暂时只支持了LinearLayoutManager,其他请进行自定义
 * 如果需要自定义实现需要考虑到顶部的ArrowView,HeaderView,FooterView的个数
 * author: chencong
 * date: 2017/10/25
 */

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {


    private int mDividerHeight = 10;

    private int mDividerColor = 0xffff4b55;

    private Paint mDividerPaint;

    public SimpleItemDecoration() {
        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setColor(mDividerColor);
    }


    public int getmDividerHeight() {
        return mDividerHeight;
    }

    public void setmDividerHeight(int mDividerHeight) {
        this.mDividerHeight = mDividerHeight;
    }

    public int getmDividerColor() {
        return mDividerColor;
    }

    public void setmDividerColor(int mDividerColor) {
        this.mDividerColor = mDividerColor;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent instanceof PullToRefreshRecyclerView) {
            PullToRefreshRecyclerView rootView = (PullToRefreshRecyclerView) parent;
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = parent.getChildAt(i);
                int childAdapterPosition = parent.getChildAdapterPosition(childAt);
                if(childAdapterPosition>=1+(rootView.getHeaderViewSize()==0?0:1)&&childAdapterPosition<=1+(rootView.getHeaderViewSize()==0?0:1)+rootView.getAdapter().getItemCount()){
                    int top = childAt.getTop() - mDividerHeight;
                    int left = parent.getPaddingLeft();
                    int bottom = childAt.getTop();
                    int right = parent.getWidth() - parent.getPaddingRight();
                    c.drawRect(left, top, right, bottom, mDividerPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent instanceof PullToRefreshRecyclerView) {
            PullToRefreshRecyclerView rootView = (PullToRefreshRecyclerView) parent;
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if(childAdapterPosition>=1+(rootView.getHeaderViewSize()==0?0:1)&&childAdapterPosition<=1+(rootView.getHeaderViewSize()==0?0:1)+rootView.getAdapter().getItemCount()){
                outRect.top = mDividerHeight;
            }

        }

    }


}
