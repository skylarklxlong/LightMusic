package online.himakeit.lightmusic.bean;

/**
 * @author：LiXueLong
 * @date：2018/2/26
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicLrcPicDataEntity {
    /**
     * {
     "album_id": 182772,
     "artist_1000_1000": "http://qukufile2.qianqian.com/data2/pic/246586431/246586431.jpg",
     "artist_480_800": "http://qukufile2.qianqian.com/data2/pic/105445485/105445485.jpg",
     "artist_640_1136": "http://qukufile2.qianqian.com/data2/pic/105445470/105445470.jpg",
     "artist_id": 786,
     "author": "羽泉",
     "avatar_s180": "http://qukufile2.qianqian.com/data2/pic/246586431/246586431.jpg@s_0,w_180",
     "avatar_s500": "http://qukufile2.qianqian.com/data2/pic/246586431/246586431.jpg@s_0,w_500",
     "lrc_md5": "6096cb1383732f8a5d99ede39ed0927a",
     "lrclink": "http://qukufile2.qianqian.com/data2/lrc/240284398/240284398.lrc",
     "pic_s1000": "http://qukufile2.qianqian.com/data2/music/5C123C4E11A70AB61A3EB2CC1E203942/252256504/252256504.jpg",
     "pic_s180": "http://qukufile2.qianqian.com/data2/music/5C123C4E11A70AB61A3EB2CC1E203942/252256504/252256504.jpg@s_0,w_180",
     "pic_s500": "http://qukufile2.qianqian.com/data2/music/5C123C4E11A70AB61A3EB2CC1E203942/252256504/252256504.jpg@s_0,w_500",
     "pic_type": 2,
     "song_id": 1159960,
     "song_title": "深呼吸",
     "title": "深呼吸"
     }
     */

    private int album_id;
    private String artist_480_800;
    private String lrclink;
    private String pic_s500;

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getArtist_480_800() {
        return artist_480_800;
    }

    public void setArtist_480_800(String artist_480_800) {
        this.artist_480_800 = artist_480_800;
    }

    public String getLrclink() {
        return lrclink;
    }

    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }

    public String getPic_s500() {
        return pic_s500;
    }

    public void setPic_s500(String pic_s500) {
        this.pic_s500 = pic_s500;
    }
}
