package online.himakeit.lightmusic;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import online.himakeit.lightmusic.util.ToastUtil;

/**
 * @author：LiXueLong
 * @date：2018/1/31
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class AppContext extends Application {
    /**
     * 全局应用的上下文
     */
    static AppContext mAppContext;

    static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = this;
        mHandler = new Handler();

        ToastUtil.register(this);
    }

    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        return mHandler;
    }

    /**
     * 获取全局的AppContext
     *
     * @return
     */
    public static AppContext getAppContext() {
        return mAppContext;
    }

    /**
     * 版本名
     *
     * @return
     */
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 版本号
     *
     * @return
     */
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    private static PackageInfo getPackageInfo() {
        PackageInfo info = null;

        try {
            PackageManager packageManager = mAppContext.getPackageManager();
            info = packageManager.getPackageInfo(mAppContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return info;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return info;
    }
}
