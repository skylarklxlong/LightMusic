package online.himakeit.lightmusic.network;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：LiXueLong
 * @date：2018/2/5
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduNetHelper {
    public static Map<String, String> getMusicApiCommonParams(String method) {
        Map params = new HashMap();
        params.put("format", "json");
        params.put("version", "5.8.1.0");
        params.put("from", "android");
        params.put("method", method);
        return params;
    }
}
