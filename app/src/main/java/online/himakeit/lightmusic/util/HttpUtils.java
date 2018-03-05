package online.himakeit.lightmusic.util;

import okhttp3.Request;
import okhttp3.Response;
import online.himakeit.lightmusic.AppContext;

/**
 * @author：LiXueLong
 * @date：2018/3/2
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class HttpUtils {

    public static String getResponseString(String url) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = AppContext.defaultOkHttpClient()
                    .newCall(request)
                    .execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
