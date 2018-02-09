package online.himakeit.lightmusic.ui.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.afollestad.appthemeengine.ATEActivity;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.ui.fragment.QuickControlsFragment;
import online.himakeit.lightmusic.util.ATEUtil;

/**
 * @author：LiXueLong
 * @date：2018/1/30
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaseActivity extends ATEActivity implements ServiceConnection {

    /**
     * 底部播放控制栏
     */
    private QuickControlsFragment mQcFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        showQuickControl(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Nullable
    @Override
    protected String getATEKey() {
        return ATEUtil.getATEKey(this);
    }

    /**
     * @param outState 取消保存状态
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    /**
     * @param savedInstanceState 取消保存状态
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 显示或关闭底部播放控制栏
     *
     * @param show
     */
    protected void showQuickControl(boolean show) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            if (mQcFragment == null) {
                mQcFragment = QuickControlsFragment.newInstance();
                transaction.add(R.id.fl_bottom_container, mQcFragment).commitAllowingStateLoss();
            } else {
                transaction.show(mQcFragment).commitAllowingStateLoss();
            }
        } else {
            if (mQcFragment != null) {
                transaction.hide(mQcFragment).commitAllowingStateLoss();
            }
        }
    }
}
