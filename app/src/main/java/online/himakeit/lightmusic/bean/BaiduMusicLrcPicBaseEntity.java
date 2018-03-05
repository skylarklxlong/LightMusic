package online.himakeit.lightmusic.bean;

import java.util.ArrayList;

/**
 * @author：LiXueLong
 * @date：2018/2/26
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicLrcPicBaseEntity {
    /**
     * {
     "error_code": 22000,
     "songinfo": [
     {
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
     },{},{},{},{}
     ]
     }
     */

    private int error_code;
    private ArrayList<BaiduMusicLrcPicDataEntity> songinfo;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ArrayList<BaiduMusicLrcPicDataEntity> getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(ArrayList<BaiduMusicLrcPicDataEntity> songinfo) {
        this.songinfo = songinfo;
    }
}
