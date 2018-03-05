package online.himakeit.lightmusic.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * @author：LiXueLong
 * @date：2018/3/5
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class PermissionUtils {

    public static boolean checkPermission(Context context, String permissionName) {
        if (context == null) {
            throw new RuntimeException("Context is null");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return PackageManager.PERMISSION_GRANTED == context.checkSelfPermission(permissionName);
        } else {
            return true;
        }
    }

    public static boolean checkPermission(Context context, String[] permissionName) {
        if (context == null) {
            throw new RuntimeException("Context is null");
        }

        for (String permission : permissionName) {
            if (!checkPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }
}
