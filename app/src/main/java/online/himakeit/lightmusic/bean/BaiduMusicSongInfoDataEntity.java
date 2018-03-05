package online.himakeit.lightmusic.bean;

/**
 * @author：LiXueLong
 * @date：2018/3/5
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicSongInfoDataEntity {
    /**
     * "can_load": true,
     * "can_see": 1,
     * "down_type": 0,
     * "file_bitrate": 64,
     * "file_duration": 290,
     * "file_extension": "mp3",
     * "file_link": "http://zhangmenshiting.qianqian.com/data2/music/90fb2ed26f4e5fbd8efa4a7144758421/569080853/569080853.mp3?xcode=a41621e3d31c6ff252d17b2d9b423fd1",
     * "file_size": 2319698,
     * "free": 1,
     * "hash": "30c399653d72cfe996939596731327176f418b53",
     * "is_udition_url": 1,
     * "original": 0,
     * "preload": 40,
     * "replay_gain": "0.720001",
     * "show_link": "http://zhangmenshiting.qianqian.com/data2/music/90fb2ed26f4e5fbd8efa4a7144758421/569080853/569080853.mp3?xcode=a41621e3d31c6ff252d17b2d9b423fd1",
     * "song_file_id": 569080853
     */

    private boolean can_load;
    private int can_see;
    private int down_type;
    private int file_bitrate;
    private int file_duration;
    private String file_extension;
    private String file_link;
    private long file_size;
    private int free;
    private String hash;
    private int is_udition_url;
    private int original;
    private int preload;
    private String replay_gain;
    private String show_link;
    private long song_file_id;

    public boolean isCan_load() {
        return can_load;
    }

    public void setCan_load(boolean can_load) {
        this.can_load = can_load;
    }

    public int getCan_see() {
        return can_see;
    }

    public void setCan_see(int can_see) {
        this.can_see = can_see;
    }

    public int getDown_type() {
        return down_type;
    }

    public void setDown_type(int down_type) {
        this.down_type = down_type;
    }

    public int getFile_bitrate() {
        return file_bitrate;
    }

    public void setFile_bitrate(int file_bitrate) {
        this.file_bitrate = file_bitrate;
    }

    public int getFile_duration() {
        return file_duration;
    }

    public void setFile_duration(int file_duration) {
        this.file_duration = file_duration;
    }

    public String getFile_extension() {
        return file_extension;
    }

    public void setFile_extension(String file_extension) {
        this.file_extension = file_extension;
    }

    public String getFile_link() {
        return file_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getIs_udition_url() {
        return is_udition_url;
    }

    public void setIs_udition_url(int is_udition_url) {
        this.is_udition_url = is_udition_url;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public int getPreload() {
        return preload;
    }

    public void setPreload(int preload) {
        this.preload = preload;
    }

    public String getReplay_gain() {
        return replay_gain;
    }

    public void setReplay_gain(String replay_gain) {
        this.replay_gain = replay_gain;
    }

    public String getShow_link() {
        return show_link;
    }

    public void setShow_link(String show_link) {
        this.show_link = show_link;
    }

    public long getSong_file_id() {
        return song_file_id;
    }

    public void setSong_file_id(long song_file_id) {
        this.song_file_id = song_file_id;
    }
}
