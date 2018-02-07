package online.himakeit.lightmusic.bean;

/**
 * @author：LiXueLong
 * @date：2018/2/6
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicNewSongMixOneEntity {
    /**
     * {
     "error_code": 22000,
     "result": [
     {
     "desc": "1月新歌速递",
     "pic": "http://business.cdn.qianqian.com/qianqian/pic/bos_client_151496557800c8755f961dfa14ca034a5e9394bb3c.jpg",
     "type_id": "517269835",
     "type": 0,
     "title": "新歌抢鲜听",
     "tip_type": 0,
     "author": "1月新歌速递"
     },
     {
     "desc": "庆庆",
     "pic": "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1517800553a0de955cd0acf23103b77bbd4c60d30e.jpg",
     "type_id": "571647053",
     "type": 2,
     "title": "遗忘",
     "tip_type": 0,
     "author": "庆庆"
     },{},{},{},{}
     ]
     }
     */

    private String desc;
    private String pic;
    private String type_id;
    private int type;
    private String title;
    private int tip_type;
    private String author;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTip_type() {
        return tip_type;
    }

    public void setTip_type(int tip_type) {
        this.tip_type = tip_type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
