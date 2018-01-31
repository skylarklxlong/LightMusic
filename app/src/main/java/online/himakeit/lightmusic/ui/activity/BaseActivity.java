package online.himakeit.lightmusic.ui.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;

import com.afollestad.appthemeengine.ATEActivity;

import online.himakeit.lightmusic.util.ATEUtil;

/**
 * @author：LiXueLong
 * @date：2018/1/30
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaseActivity extends ATEActivity implements ServiceConnection {

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
}
