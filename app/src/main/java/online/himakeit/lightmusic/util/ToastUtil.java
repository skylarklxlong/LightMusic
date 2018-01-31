package online.himakeit.lightmusic.util;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * @author：LiXueLong
 * @date：2018/1/31
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class ToastUtil {
    public static Context sContext;


    private ToastUtil() {
    }


    public static void register(Context context) {
        sContext = context.getApplicationContext();
    }


    private static void check() {
        if (sContext == null) {
            throw new NullPointerException(
                    "Must initial call Toasts.register(Context context) in your " +
                            "<? " +
                            "extends Application class>");
        }
    }

    /**
     * 在子线程中显示Toast，会报错：
     * java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()
     * 这句话的意思是：如果在一个线程中没有调用Looper.prepare(),就不能在该线程中创建Handler。
     * Looper.prepare();
     * Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
     * Looper.loop();
     *
     * @param resId
     */
    public static void showShort(int resId) {
        try {
            check();
            Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Looper.prepare();
            Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }

    }


    public static void showShort(String message) {
        try {
            check();
            Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Looper.prepare();
            Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }

    }


    public static void showLong(int resId) {
        try {
            check();
            Toast.makeText(sContext, resId, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Looper.prepare();
            Toast.makeText(sContext, resId, Toast.LENGTH_LONG).show();
            Looper.loop();
        }
    }


    public static void showLong(String message) {
        try {
            check();
            Toast.makeText(sContext, message, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Looper.prepare();
            Toast.makeText(sContext, message, Toast.LENGTH_LONG).show();
            Looper.loop();
        }
    }


    public static void showLongX2(String message) {
        showLong(message);
        showLong(message);
    }


    public static void showLongX2(int resId) {
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(int resId) {
        showLong(resId);
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(String message) {
        showLong(message);
        showLong(message);
        showLong(message);
    }
}
