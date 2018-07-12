package com.example.admin.myapplication;

import android.animation.LayoutTransition;
import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;

public class ContainnerLayout extends ViewGroup {
    public ContainnerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        View child = getChildAt(0);
        setMeasuredDimension(getResources().getDisplayMetrics().widthPixels, child.getMeasuredHeight() * getChildCount());
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
/**
 * 布局过程中需要考虑到margin
 */
        int count = getChildCount();
        int height = 0;
        int mLeft = 0;
        View childOne = getChildAt(0);
        height = childOne.getMeasuredHeight();
        MarginLayoutParams layoutParams = (MarginLayoutParams) childOne.getLayoutParams();
        for (int p = 0; p < count; p++) {
            View child = getChildAt(p);
            if (mLeft + child.getMeasuredWidth() > getResources().getDisplayMetrics().widthPixels) {
                mLeft = 0;
                height = height + child.getMeasuredHeight() + layoutParams.topMargin;
            }
            child.layout(mLeft, height - child.getMeasuredHeight() + layoutParams.topMargin, mLeft + child.getMeasuredWidth(), height + 20 + layoutParams.topMargin);
            mLeft = mLeft + child.getMeasuredWidth();


        }

    }
}
