package online.himakeit.lightmusic.ui.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author：LiXueLong
 * @date：2018/1/30
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class CustomViewPager extends ViewPager {

    OnSingleTouchListener mOnSingleTouchListener;
    PointF mDownPointF = new PointF();

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /**
                 * 记录按下时的坐标
                 */
                mDownPointF.x = ev.getX();
                mDownPointF.y = ev.getY();
                /**
                 * 有内容，多于1个时，通知其父控件，现在进行的是本控件的操作，不允许拦截
                 */
                if (this.getChildCount() > 1) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                /**
                 * 有内容，多于1个时，通知其父控件，现在进行的是本控件的操作，不允许拦截
                 */
                if (this.getChildCount() > 1) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                /**
                 * 判断按下和松手是否为同一个坐标点
                 */
                if (PointF.length(ev.getX() - mDownPointF.x, ev.getY() - mDownPointF.y)
                        < (float) 5.0) {
                    onSingleTouch(this);
                    return true;
                }
                break;
            default:
        }
        return super.onTouchEvent(ev);
    }

    public void onSingleTouch(View view) {
        if (mOnSingleTouchListener != null) {
            mOnSingleTouchListener.onSingleTouch(view);
        }
    }

    public interface OnSingleTouchListener {
        public void onSingleTouch(View view);
    }

    public void setmOnSingleTouchListener(OnSingleTouchListener mOnSingleTouchListener) {
        this.mOnSingleTouchListener = mOnSingleTouchListener;
    }
}
