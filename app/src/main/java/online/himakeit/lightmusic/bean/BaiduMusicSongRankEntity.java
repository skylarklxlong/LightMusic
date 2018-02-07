package online.himakeit.lightmusic.bean;

import java.util.List;

/**
 * @author：LiXueLong
 * @date：2018/2/5
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicSongRankEntity {

    /**
     * {
     "song_list": [
     {
     "artist_id": "15",
     "language": "国语",
     "pic_big": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_150,h_150",
     "pic_small": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_90,h_90",
     "country": "内地",
     "area": "0",
     "publishtime": "2018-01-11",
     "album_no": "1",
     "lrclink": "http://qukufile2.qianqian.com/data2/lrc/ef80d282b94f37e92bee6e5b9b417124/569080826/569080826.lrc",
     "copy_type": "3",
     "hot": "331875",
     "all_artist_ting_uid": "45561",
     "resource_type": "0",
     "is_new": "1",
     "rank_change": "1",
     "rank": "1",
     "all_artist_id": "15",
     "style": "",
     "del_status": "0",
     "relate_status": "0",
     "toneid": "0",
     "all_rate": "64,128,256,320,flac",
     "file_duration": 290,
     "has_mv_mobile": 0,
     "versions": "影视原声",
     "bitrate_fee": "{\"0\":\"0|0\",\"1\":\"0|0\"}",
     "biaoshi": "first,lossless",
     "info": "",
     "has_filmtv": "0",
     "si_proxycompany": "上海腾讯企鹅影视文化传播有限公司",
     "res_encryption_flag": "0",
     "song_id": "569080829",
     "title": "无问西东",
     "ting_uid": "45561",
     "author": "王菲",
     "album_id": "569080827",
     "album_title": "无问西东",
     "is_first_publish": 0,
     "havehigh": 2,
     "charge": 0,
     "has_mv": 0,
     "learn": 0,
     "song_source": "web",
     "piao_id": "0",
     "korean_bb_song": "0",
     "resource_type_ext": "0",
     "mv_provider": "0000000000",
     "artist_name": "王菲",
     "pic_radio": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_300,h_300",
     "pic_s500": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_500,h_500",
     "pic_premium": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_500,h_500",
     "pic_huge": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_1000,h_1000",
     "album_500_500": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_500,h_500",
     "album_800_800": "",
     "album_1000_1000": "http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_1000,h_1000"
     },
     {},
     {}
     ],
     "billboard": {},
     "error_code": 22000
     }
     */

    private int error_code;
    private List<BaiduMusicSongRankSongListEntity> song_list;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<BaiduMusicSongRankSongListEntity> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<BaiduMusicSongRankSongListEntity> song_list) {
        this.song_list = song_list;
    }
}
