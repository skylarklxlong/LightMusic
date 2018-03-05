package online.himakeit.lightmusic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author：LiXueLong
 * @date：2018/3/2
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class StringUtils {

    /**
     * 使用UTF-8编码字符串
     *
     * @param str
     * @return
     */
    public static String encodeUtf8(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
