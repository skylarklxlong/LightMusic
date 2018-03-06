package online.himakeit.lightmusic.ui.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.afollestad.appthemeengine.ATEActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import online.himakeit.lightmusic.MediaAidlInterface;
import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.config.Constants;
import online.himakeit.lightmusic.interfaces.MusicStateListener;
import online.himakeit.lightmusic.service.MediaService;
import online.himakeit.lightmusic.service.MusicPlayer;
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

    private MusicPlayer.ServiceToken mToken;
    /**
     * receiver 接受播放状态变化等
     */
    private PlaybackStatus mPlaybackStatus;

    private ArrayList<MusicStateListener> mMusicListener = new ArrayList<>();

    /**
     * 更新播放队列
     */
    public void updateQueue() {

    }

    /**
     * 更新歌曲状态信息
     */
    public void updateTrackInfo() {
        for (final MusicStateListener listener : mMusicListener) {
            if (listener != null) {
                listener.reloadAdapter();
                listener.updateTrackInfo();
            }
        }
    }

    /**
     * fragment界面刷新
     */
    public void refreshUI() {
        for (final MusicStateListener listener : mMusicListener) {
            if (listener != null) {
                listener.reloadAdapter();
            }
        }

    }

    public void updateTime() {
        for (final MusicStateListener listener : mMusicListener) {
            if (listener != null) {
                listener.updateTime();
            }
        }
    }

    /**
     * 歌曲切换
     */
    public void updateTrack() {

    }


    public void updateLrc() {

    }

    /**
     * @param p 更新歌曲缓冲进度值，p取值从0~100
     */
    public void updateBuffer(int p) {

    }

    /**
     * @param l 歌曲是否加载中
     */
    public void loading(boolean l) {

    }

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

        mToken = MusicPlayer.bindToService(this, this);
        mPlaybackStatus = new PlaybackStatus(this);

        IntentFilter f = new IntentFilter();
        f.addAction(MediaService.PLAYSTATE_CHANGED);
        f.addAction(MediaService.META_CHANGED);
        f.addAction(MediaService.QUEUE_CHANGED);
        f.addAction(Constants.MUSIC_COUNT_CHANGED);
        f.addAction(MediaService.TRACK_PREPARED);
        f.addAction(MediaService.BUFFER_UP);
        f.addAction(Constants.EMPTY_LIST);
        f.addAction(MediaService.MUSIC_CHANGED);
        f.addAction(MediaService.LRC_UPDATED);
        f.addAction(Constants.PLAYLIST_COUNT_CHANGED);
        f.addAction(MediaService.MUSIC_LODING);
        registerReceiver(mPlaybackStatus, new IntentFilter(f));

        showQuickControl(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService();
        try {
            unregisterReceiver(mPlaybackStatus);
        } catch (final Throwable e) {
        }
        mMusicListener.clear();
    }

    public void unbindService() {
        if (mToken != null) {
            MusicPlayer.unbindFromService(mToken);
            mToken = null;
        }
    }

    public void setMusicStateListenerListener(final MusicStateListener status) {
        if (status == this) {
            throw new UnsupportedOperationException("Override the method, don't add a listener");
        }

        if (status != null) {
            mMusicListener.add(status);
        }
    }

    public void removeMusicStateListenerListener(final MusicStateListener status) {
        if (status != null) {
            mMusicListener.remove(status);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicPlayer.mService = MediaAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        MusicPlayer.mService = null;
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

    private static final class PlaybackStatus extends BroadcastReceiver {

        private final WeakReference<BaseActivity> mReference;

        public PlaybackStatus(BaseActivity baseActivity) {
            mReference = new WeakReference<BaseActivity>(baseActivity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BaseActivity baseActivity = mReference.get();
            if (baseActivity != null) {
                if (action.equals(MediaService.META_CHANGED)) {
                    baseActivity.updateTrackInfo();
                } else if (action.equals(MediaService.PLAYSTATE_CHANGED)) {

                } else if (action.equals(MediaService.TRACK_PREPARED)) {
                    baseActivity.updateTime();
                } else if (action.equals(MediaService.BUFFER_UP)) {
                    baseActivity.updateBuffer(intent.getIntExtra("progress", 0));
                } else if (action.equals(MediaService.MUSIC_LODING)) {
                    baseActivity.loading(intent.getBooleanExtra("isloading", false));
                } else if (action.equals(MediaService.REFRESH)) {

                } else if (action.equals(Constants.MUSIC_COUNT_CHANGED)) {
                    baseActivity.refreshUI();
                } else if (action.equals(Constants.PLAYLIST_COUNT_CHANGED)) {
                    baseActivity.refreshUI();
                } else if (action.equals(MediaService.QUEUE_CHANGED)) {
                    baseActivity.updateQueue();
                } else if (action.equals(MediaService.TRACK_ERROR)) {
                    Toast.makeText(baseActivity, "退出", Toast.LENGTH_SHORT).show();
                } else if (action.equals(MediaService.MUSIC_CHANGED)) {
                    baseActivity.updateTrack();
                } else if (action.equals(MediaService.LRC_UPDATED)) {
                    baseActivity.updateLrc();
                }
            }
        }
    }
}
