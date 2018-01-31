package online.himakeit.lightmusic.bean;

/**
 * @author：LiXueLong
 * @date：2018/1/31
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class PlaylistEntity {
    public final long id;
    public final String name;
    public final int songCount;
    public final String albumArt;
    public final String author;

    public PlaylistEntity(long id, String name, int songCount, String albumArt, String author) {
        this.id = id;
        this.name = name;
        this.songCount = songCount;
        this.albumArt = albumArt;
        this.author = author;
    }

    public PlaylistEntity() {
        this.id = -1;
        this.name = "";
        this.songCount = -1;
        this.albumArt = "";
        this.author = "";
    }
}
