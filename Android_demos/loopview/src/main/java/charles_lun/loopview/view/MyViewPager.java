package charles_lun.loopview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * 创建时间：2015/10/10 09 : 56
 * 项目名称：qwb1.7.0
 * 类说明：解决listview 嵌套viewpager手势冲突
 * 创建人： lc
 *
 * @since JDK 1.7.0
 * Copyright (c) lc-版权所有
 */
public class MyViewPager extends ViewPager {

    private ViewGroup parentView;


    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (parentView != null) {
            parentView.requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (parentView != null) {
            parentView.requestDisallowInterceptTouchEvent(false);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (parentView != null) {
            parentView.requestDisallowInterceptTouchEvent(false);
        }
        return super.onTouchEvent(ev);
    }


    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }
}
