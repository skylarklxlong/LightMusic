package online.himakeit.lightmusic.bean;

import java.util.ArrayList;

/**
 * @author：LiXueLong
 * @date：2018/3/6
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicGeDanInfoEntity {

    /**
     * {
     "collectnum": "160",
     "content": [
     {
     "album_id": "28725684",
     "album_title": "涩女郎 电视原声带",
     "all_artist_id": "74",
     "all_rate": "64,128,256,320,flac",
     "author": "刘若英",
     "biaoshi": "lossless",
     "bitrate_fee": "{"0":"0|0","1":"0|0"}",
     "charge": 0,
     "copy_type": "1",
     "del_status": "0",
     "distribution": "0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,1111111111,1111111111,0000000000",
     "file_duration": "274",
     "has_mv": 1,
     "has_mv_mobile": 0,
     "havehigh": 2,
     "high_rate": "320",
     "is_first_publish": 0,
     "is_ksong": "0",
     "korean_bb_song": "0",
     "learn": 0,
     "mv_provider": "0000000000",
     "piao_id": "0",
     "pic_big": "http://qukufile2.qianqian.com/data2/pic/082d0bf43d0fe81d6dccf9374ed74214/558427393/558427393.jpg@s_1,w_150,h_150",
     "pic_radio": "http://qukufile2.qianqian.com/data2/pic/082d0bf43d0fe81d6dccf9374ed74214/558427393/558427393.jpg@s_1,w_300,h_300",
     "pic_s130": "http://qukufile2.qianqian.com/data2/pic/082d0bf43d0fe81d6dccf9374ed74214/558427393/558427393.jpg@s_1,w_130,h_130",
     "relate_status": "0",
     "resource_type": "0",
     "resource_type_ext": "0",
     "share": "http://music.baidu.com/song/28725763",
     "song_id": "28725763",
     "song_source": "web",
     "ting_uid": "1067",
     "title": "一辈子的孤单",
     "toneid": "0",
     "versions": ""
     },{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
     {},{},{},{},{},{},{},{},{},{},{},{},{},{}
     ],
     "desc": "每一个人的青春记忆中，都有一部偶像剧陪你长大，或许在多年以后，剧中的故事你已经忘记，但这些熟悉的旋律总会带给你如青春般美好的回忆。",
     "error_code": 22000,
     "height": "300",
     "listenum": "73334",
     "listid": "515021776",
     "pic_300": "http://musicugc.qianqian.com/ugcdiy/pic/c3282308b02047311e2a83e159184072.jpg",
     "pic_500": "http://musicugc.qianqian.com/ugcdiy/pic/c3282308b02047311e2a83e159184072.jpg",
     "pic_w700": "http://musicugc.qianqian.com/ugcdiy/pic/c3282308b02047311e2a83e159184072.jpg",
     "tag": "华语,怀旧,影视原声",
     "title": "萦绕在耳边的台剧旋律",
     "url": "http://music.baidu.com/songlist/515021776",
     "width": "300"
     }
     */

    private String collectnum;
    private ArrayList<BaiduMusicGeDanInfoDataEntity> content;
    private String desc;
    private long error_code;
    private String height;
    private String listenum;
    private String listid;
    private String pic_300;
    private String pic_500;
    private String pic_w700;
    private String tag;
    private String title;
    private String url;
    private String width;

    public String getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(String collectnum) {
        this.collectnum = collectnum;
    }

    public ArrayList<BaiduMusicGeDanInfoDataEntity> getContent() {
        return content;
    }

    public void setContent(ArrayList<BaiduMusicGeDanInfoDataEntity> content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getError_code() {
        return error_code;
    }

    public void setError_code(long error_code) {
        this.error_code = error_code;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getListenum() {
        return listenum;
    }

    public void setListenum(String listenum) {
        this.listenum = listenum;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getPic_300() {
        return pic_300;
    }

    public void setPic_300(String pic_300) {
        this.pic_300 = pic_300;
    }

    public String getPic_500() {
        return pic_500;
    }

    public void setPic_500(String pic_500) {
        this.pic_500 = pic_500;
    }

    public String getPic_w700() {
        return pic_w700;
    }

    public void setPic_w700(String pic_w700) {
        this.pic_w700 = pic_w700;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
