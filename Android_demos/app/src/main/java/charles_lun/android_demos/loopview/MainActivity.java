package charles_lun.android_demos.loopview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import charles_lun.android_demos.R;
import charles_lun.loopview.InfiniteIndicatorLayout;
import charles_lun.loopview.indicator.CircleIndicator;
import charles_lun.loopview.slideview.BaseSliderView;
import charles_lun.loopview.slideview.DefaultSliderView;
import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;

/**
 * 创建时间：2016/1/20 14 : 31
 * 项目名称：
 * 类说明：广告轮播效果
 * 创建人： lc
 * <p/>
 *
 * @since JDK 1.7.0
 * Copyright (c) lc-版权所有
 */
public class MainActivity extends Activity implements BaseSliderView.OnSliderClickListener {
    private InfiniteIndicatorLayout mPager;
    private ZrcListView mListView;
    private View headerView;
    private MyHandler myHandler;

    private String[] uriArray = {"http://d.hiphotos.baidu.com/image/h%3D300/sign=01d79488efc4b7452b94b116fffd1e78/58ee3d6d55fbb2fb48944ab34b4a20a44723dcd7.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D300/sign=7642b6b293dda144c5096ab282b6d009/dc54564e9258d109c94bbb13d558ccbf6d814de2.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D300/sign=3551764b85cb39dbdec06156e01709a7/2f738bd4b31c8701491ea047237f9e2f0608ffe3.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D300/sign=afa202ee9a2f070840052c00d925b865/d8f9d72a6059252dfc5e0da5309b033b5ab5b9c1.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);
        myHandler = new MyHandler();
        headerView = View.inflate(this, R.layout.header_view, null);
        mPager = (InfiniteIndicatorLayout) headerView.findViewById(R.id.mPager);
        initZrcListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPager != null) {
            mPager.startAutoScroll();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPager != null) {
            mPager.stopAutoScroll();
        }
    }

    private void initZrcListView() {
        mListView = (ZrcListView) findViewById(R.id.mListview);
        // 设置下拉刷新的样式
        SimpleHeader header = new SimpleHeader(this);
        header.setTextColor(0xffff6734);
        header.setCircleColor(0xffff6734);
        mListView.setHeadable(header);
        // 设置加载更多的样式
        SimpleFooter footer = new SimpleFooter(this);
        footer.setCircleColor(0xffff6734);
        mListView.setFootable(footer);
        mListView.addHeaderView(headerView, null, false);

        // 下拉刷新事件回调
        mListView.setOnRefreshStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
                refresh();
            }
        });
        // 加载更多事件回调
        mListView.setOnLoadMoreStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
            }
        });
        mListView.setDividerHeight(0);
        mListView.setAdapter(new MyAdapter());
        mListView.refresh();

    }


    private void refresh() {
        myHandler.removeCallbacks(runnable);
        myHandler.postDelayed(runnable, 3000);
        showLoopView();
    }


    private void showLoopView() {
        mPager.removeAllSliders();
        DefaultSliderView sliderView;

        for (String uri : uriArray) {
            sliderView = new DefaultSliderView(this);
            sliderView.image(uri).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
            sliderView.getBundle().putString("extra", uri);
            mPager.addSlider(sliderView);
        }

        mPager.setIndicatorPosition(InfiniteIndicatorLayout.IndicatorPosition.Right_Bottom);
        CircleIndicator circleIndicator = (CircleIndicator) mPager.getPagerIndicator();
        circleIndicator.setStrokeColor(getResources().getColor(R.color.cfd2131));
        circleIndicator.setPageColor(getResources().getColor(R.color.cff6734));
        circleIndicator.setFillColor(getResources().getColor(R.color.cffffff));
        circleIndicator.setStrokeWidth(3);
        circleIndicator.setRadius(10);
        circleIndicator.setPadding(5, 5, 5, 5);
        mPager.setInfinite(true);
        mPager.startAutoScroll();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        Bundle bundle = slider.getBundle();
        Toast.makeText(this, bundle.getString("extra"), Toast.LENGTH_LONG).show();
    }

    class MyHandler extends Handler {

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mListView.setRefreshSuccess();
        }
    };


    /**
     *
     * listview和viewpager的手势冲突
     * 重写listview的onInterceptTouchEvent
     *
     * */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                dy = ev.getY();
//                dx = ev.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float offsetX = Math.abs(ev.getX() - dx);
//                float offsetY = Math.abs(ev.getY() - dy);
//
//                if (offsetY > 20f && offsetY > offsetX) {
//                    return true;
//                }
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

}
