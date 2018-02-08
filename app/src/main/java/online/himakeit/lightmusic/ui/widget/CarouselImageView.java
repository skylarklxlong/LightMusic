package online.himakeit.lightmusic.ui.widget;

import android.content.Context;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.BaiduMusicPicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicPicDataEntity;
import online.himakeit.lightmusic.network.ApiManager;
import online.himakeit.lightmusic.network.BaiduNetCallBack;
import online.himakeit.lightmusic.util.LogUtils;

/**
 * @author：LiXueLong
 * @date：2018/2/2
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class CarouselImageView extends FrameLayout {

    /**
     * 轮播图图片数量
     */
    private final static int IMAGE_COUNT = 7;
    /**
     * 自动轮播时间间隔
     */
    private final static int TIME_INTERVAL = 3;
    /**
     * 自动轮播启用开关
     */
    private final static boolean AUTO_PLAY = true;

    private CarouselImageViewPagerAdapter mViewPagerAdapter;
    private ArrayList<String> imageNet = new ArrayList<>(7);
    private List<ImageView> imageViewList = new ArrayList<>();
    private List<View> dotViewList = new ArrayList<>();
    private ViewPager viewPager;
    private Context mContext;
    /**
     * 当前轮播页面
     */
    private int currentItem = 0;

    /**
     * 定时任务
     */
    private ScheduledExecutorService scheduledExecutorService;

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
    };

    public CarouselImageView(Context context) {
        super(context);
        mContext = context;

    }

    public CarouselImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public CarouselImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initImageData();
        initView(context);
        if (AUTO_PLAY) {
            startPlay();
        }
    }

    ArrayList<BaiduMusicPicDataEntity> mDataList = new ArrayList<>();

    private void initImageData() {
        ApiManager.getInstance().getPicData(7, new BaiduNetCallBack<BaiduMusicPicBaseEntity>() {
            @Override
            public void onSuccess(BaiduMusicPicBaseEntity entity) {
                if (entity != null) {
                    if (entity.getError_code() == 22000) {
                        mDataList.addAll(entity.getPic());
                        if (mDataList != null && mDataList.size() > 0) {
                            imageNet.clear();
                            for (int i = 0; i < mDataList.size(); i++) {
                                imageNet.add(mDataList.get(i).getRandpic());

                                Glide.with(mContext)
                                        .load(imageNet.get(i))
                                        .centerCrop()
                                        .error(R.drawable.placeholder_disk_210)
                                        .placeholder(R.drawable.placeholder_disk_210)
                                        .into(imageViewList.get(i));
                                LogUtils.show("======" + imageNet.get(i));
                            }

                        }
                    }
                }
            }
        });

    }

    private void initView(Context mContext) {
        LayoutInflater.from(mContext).inflate(R.layout.layout_carousel_image_view, this, true);

        for (int i = 0; i < 7; i++) {
            ImageView mIvImg = new ImageView(mContext);
            imageViewList.add(mIvImg);
        }

        dotViewList.add(findViewById(R.id.v_dot1));
        dotViewList.add(findViewById(R.id.v_dot2));
        dotViewList.add(findViewById(R.id.v_dot3));
        dotViewList.add(findViewById(R.id.v_dot4));
        dotViewList.add(findViewById(R.id.v_dot5));
        dotViewList.add(findViewById(R.id.v_dot6));
        dotViewList.add(findViewById(R.id.v_dot7));

        viewPager = (ViewPager) findViewById(R.id.vp_carousel_image_view);
        viewPager.setFocusable(true);
        mViewPagerAdapter = new CarouselImageViewPagerAdapter();
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.addOnPageChangeListener(new MyPageChangeListener());
    }

    public void onDestroy() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            scheduledExecutorService = null;
        }
    }

    public void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new LoopTask(), 1, TIME_INTERVAL, TimeUnit.SECONDS);
    }

    public void stopPlay() {
        scheduledExecutorService.shutdown();
    }

    private class LoopTask implements Runnable {
        @Override
        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViewList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    /**
     * 解决滑动冲突
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
        }
        return super.dispatchTouchEvent(ev);
    }

    private class CarouselImageViewPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == position) {
                    dotViewList.get(i).setBackgroundResource(R.drawable.red_point);
                } else {
                    dotViewList.get(i).setBackgroundResource(R.drawable.grey_point);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case 0:
                    /**
                     * 滑动完毕，继续回到第一张图
                     */
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    } else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        /**
                         * 当前为第一张，从左向右滑
                         */
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
                case 1:
                    /**
                     * 手势滑动，空闲
                     */
                    isAutoPlay = false;
                    stopPlay();
                    startPlay();
                    break;
                case 2:
                    /**
                     * 界面切换中
                     */
                    isAutoPlay = true;
                    break;
                default:
            }
        }
    }

}
