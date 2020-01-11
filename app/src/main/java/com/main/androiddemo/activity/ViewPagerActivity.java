package com.main.androiddemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.main.androiddemo.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewPagerActivity extends AppCompatActivity {



    private ImageView[] imageViews = null;//存放imageview的
    private ImageView imageView = null;
    private ViewPager advPager = null;
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;//是否继续进行切换（子线程是否执行，自动进行切换）

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initViewPager();
    }

    private void initViewPager() {
//        advPager = (ViewPager) findViewById(R.id.adv_pager);
//        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
//
//        List<View> advPictures = new ArrayList<View>();
//
//        ImageView imageview1 = new ImageView(this);
//        imageview1.setBackgroundResource(R.drawable.advertising_default_1);
//        advPictures.add(imageview1);
//
//        ImageView imageview2 = new ImageView(this);
//        imageview2.setBackgroundResource(R.drawable.advertising_default_2);
//        advPictures.add(imageview2);
//
//        ImageView imageview3 = new ImageView(this);
//        imageview3.setBackgroundResource(R.drawable.advertising_default_3);
//        advPictures.add(imageview3);
//
//        ImageView imageview4 = new ImageView(this);
//        imageview4.setBackgroundResource(R.drawable.advertising_default);
//        advPictures.add(imageview4);
//
//        imageViews = new ImageView[advPictures.size()];
//
//        for (int i = 0; i < advPictures.size(); i++) {
//            imageView = new ImageView(this);
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
//            imageView.setPadding(5, 5, 5, 5);
//            imageViews[i] = imageView;
//            if (i == 0) {
//                imageViews[i]
//                        .setBackgroundResource(R.drawable.banner_dian_focus);
//            } else {
//                imageViews[i]
//                        .setBackgroundResource(R.drawable.banner_dian_blur);
//            }
//            group.addView(imageViews[i]);
//        }
//
//        advPager.setAdapter(new AdvAdapter(advPictures));
//        advPager.setOnPageChangeListener(new GuidePageChangeListener());
//        //No.1
//        //开始写代码，要求：设置viewpager的触摸监听事件，以及图片的加载时的子线程的开启
//        advPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });
//        viewHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                viewHandler.sendEmptyMessage(what.get());
//                whatOption();
//            }
//        });
//        //end_code
    }
    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > imageViews.length - 1) {
            what.getAndAdd(-4);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
    }
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            advPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            what.getAndSet(arg0);
//            for (int i = 0; i < imageViews.length; i++) {
//                imageViews[arg0]
//                        .setBackgroundResource(R.drawable.banner_dian_focus);
//                if (arg0 != i) {
//                    imageViews[i]
//                            .setBackgroundResource(R.drawable.banner_dian_blur);
//                }
//            }

        }

    }
    private final class AdvAdapter extends PagerAdapter {
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

    }
}
