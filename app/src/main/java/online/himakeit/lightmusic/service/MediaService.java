package online.himakeit.lightmusic.service;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import online.himakeit.lightmusic.util.LogUtils;

/**
 * @author：LiXueLong
 * @date：2018/2/7
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class MediaService extends Service {
    private static final String TAG = "MediaService";
    public static final String PLAYSTATE_CHANGED = "online.himakeit.lightmusic.playstatechanged";
    public static final String POSITION_CHANGED = "online.himakeit.lightmusic.positionchanged";
    public static final String META_CHANGED = "online.himakeit.lightmusic.metachanged";
    public static final String PLAYLIST_ITEM_MOVED = "online.himakeit.lightmusic.mmoved";
    public static final String QUEUE_CHANGED = "online.himakeit.lightmusic.queuechanged";
    public static final String PLAYLIST_CHANGED = "online.himakeit.lightmusic.playlistchanged";
    public static final String REPEATMODE_CHANGED = "online.himakeit.lightmusic.repeatmodechanged";
    public static final String SHUFFLEMODE_CHANGED = "online.himakeit.lightmusic.shufflemodechanged";
    public static final String TRACK_ERROR = "online.himakeit.lightmusic.trackerror";
    public static final String TIMBER_PACKAGE_NAME = "online.himakeit.lightmusic";
    public static final String MUSIC_PACKAGE_NAME = "com.android.music";
    public static final String SERVICECMD = "online.himakeit.lightmusic.musicservicecommand";
    public static final String TOGGLEPAUSE_ACTION = "online.himakeit.lightmusic.togglepause";
    public static final String PAUSE_ACTION = "online.himakeit.lightmusic.pause";
    public static final String STOP_ACTION = "online.himakeit.lightmusic.stop";
    public static final String PREVIOUS_ACTION = "online.himakeit.lightmusic.previous";
    public static final String PREVIOUS_FORCE_ACTION = "online.himakeit.lightmusic.previous.force";
    public static final String NEXT_ACTION = "online.himakeit.lightmusic.next";
    public static final String MUSIC_CHANGED = "online.himakeit.lightmusic.change_music";
    public static final String REPEAT_ACTION = "online.himakeit.lightmusic.repeat";
    public static final String SHUFFLE_ACTION = "online.himakeit.lightmusic.shuffle";
    public static final String FROM_MEDIA_BUTTON = "frommediabutton";
    public static final String REFRESH = "online.himakeit.lightmusic.refresh";
    public static final String LRC_UPDATED = "online.himakeit.lightmusic.updatelrc";
    public static final String UPDATE_LOCKSCREEN = "online.himakeit.lightmusic.updatelockscreen";
    public static final String CMDNAME = "command";
    public static final String CMDTOGGLEPAUSE = "togglepause";
    public static final String CMDSTOP = "stop";
    public static final String CMDPAUSE = "pause";
    public static final String CMDPLAY = "play";
    public static final String CMDPREVIOUS = "previous";
    public static final String CMDNEXT = "next";
    public static final String CMDNOTIF = "buttonId";
    public static final String TRACK_PREPARED = "online.himakeit.lightmusic.prepared";
    public static final String TRY_GET_TRACKINFO = "online.himakeit.lightmusic.gettrackinfo";
    public static final String BUFFER_UP = "online.himakeit.lightmusic.bufferup";
    public static final String LOCK_SCREEN = "online.himakeit.lightmusic.lock";
    public static final String SEND_PROGRESS = "online.himakeit.lightmusic.progress";
    public static final String MUSIC_LODING = "online.himakeit.lightmusic.loading";
    private static final String SHUTDOWN = "online.himakeit.lightmusic.shutdown";
    public static final String SETQUEUE = "online.himakeit.lightmusic.setqueue";
    public static final int NEXT = 2;
    public static final int LAST = 3;
    public static final int SHUFFLE_NONE = 0;
    public static final int SHUFFLE_NORMAL = 1;
    public static final int SHUFFLE_AUTO = 2;
    public static final int REPEAT_NONE = 2;
    public static final int REPEAT_CURRENT = 1;
    public static final int REPEAT_ALL = 2;
    public static final int MAX_HISTORY_SIZE = 1000;
    private static final boolean D = true;
    private static final int LRC_DOWNLOADED = -10;
    private static final int IDCOLIDX = 0;
    private static final int TRACK_ENDED = 1;
    private static final int TRACK_WENT_TO_NEXT = 2;
    private static final int RELEASE_WAKELOCK = 3;
    private static final int SERVER_DIED = 4;
    private static final int FOCUSCHANGE = 5;
    private static final int FADEDOWN = 6;
    private static final int FADEUP = 7;
    private static final int IDLE_DELAY = 5 * 60 * 1000;
    private static final long REWIND_INSTEAD_PREVIOUS_THRESHOLD = 3000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MediaStoreObserver extends ContentObserver implements Runnable {
        private static final long REFRESH_DELAY = 500;
        private Handler mHandler;

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MediaStoreObserver(Handler handler) {
            super(handler);
            mHandler = handler;
        }

        @Override
        public void onChange(boolean selfChange) {
            mHandler.removeCallbacks(this);
            mHandler.postDelayed(this, REFRESH_DELAY);
        }

        @Override
        public void run() {
            LogUtils.show(TAG, "calling refresh");
//            refresh();
        }
    }
}
