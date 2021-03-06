package online.himakeit.lightmusic.config;

import android.os.Environment;

/**
 * @author：LiXueLong
 * @date：2018/2/26
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class Constants {
    /**
     * SD卡路径
     */
    public static final String SD_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    /**
     * 缓存保存路径
     */
    public static final String DOWNLOAD_PATH = SD_PATH + "MusicPlayerTest/BufferFiles/";
    /**
     * SD卡预留最小值
     */
    public static final int SD_REMAIN_SIZE = 50 * 1024 * 1024;
    /**
     * 单次缓存文件最大值
     */
    public static final int AUDIO_BUFFER_MAX_LENGTH = 2 * 1024 * 1024;
    /**
     * 缓存文件个数最大值
     */
    public static final int CACHE_FILE_NUMBER = 3;
    /**
     * 预缓存文件大小
     */
    public static final int PRECACHE_SIZE = 300 * 1000;
    // Http Header Name
    public final static String CONTENT_RANGE = "Content-Range";
    public final static String CONTENT_LENGTH = "Content-Length";
    public final static String RANGE = "Range";
    public final static String HOST = "Host";
    public final static String USER_AGENT = "User-Agent";
    // Http Header Value Parts
    public final static String RANGE_PARAMS = "bytes=";
    public final static String RANGE_PARAMS_0 = "bytes=0-";
    public final static String CONTENT_RANGE_PARAMS = "bytes ";

    public final static String LINE_BREAK = "\r\n";
    public final static String HTTP_END = LINE_BREAK + LINE_BREAK;

    public static final String MUSIC_COUNT_CHANGED = "online.himakeit.lightmusic.musiccountchanged";
    public static final String PLAYLIST_ITEM_MOVED = "online.himakeit.lightmusic.mmoved";
    public static final String PLAYLIST_COUNT_CHANGED = "online.himakeit.lightmusic.playlistcountchanged";
    public static final String CHANGE_THEME = "online.himakeit.lightmusic.themechange";
    public static final String EMPTY_LIST = "online.himakeit.lightmusic.emptyplaylist";
    public static final String PACKAGE = "online.himakeit.lightmusic";

    public static final int FAV_PLAYLIST = 10;
}
