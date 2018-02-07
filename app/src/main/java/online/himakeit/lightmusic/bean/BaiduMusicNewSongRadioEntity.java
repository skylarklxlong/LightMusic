package online.himakeit.lightmusic.bean;

/**
 * @author：LiXueLong
 * @date：2018/2/6
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicNewSongRadioEntity {
    /**
     * {
     "error_code": 22000,
     "result": [
     {
     "desc": "音乐推荐",
     "itemid": "13496780",
     "title": "有待咖啡",
     "album_id": "13420707",
     "type": "lebo",
     "channelid": "11373552",
     "pic": "http://business.cdn.qianqian.com/qianqian/pic/bos_client_019d01e1e994ba4e9d7890d9fc97f9eb.jpg"
     },
     {"desc": "教育",
     "itemid": "13487126",
     "title": "《小学生必背古诗词70+80》20：九月九日忆山东兄弟",
     "album_id": "13483086",
     "type": "lebo",
     "channelid": "11373555",
     "pic": "http://business.cdn.qianqian.com/qianqian/pic/bos_client_7d8839a780a4d599e245cefcebdd1d70.jpg"
     },{},{},{},{}
     ]
     }
     */

    private String desc;
    private String itemid;
    private String title;
    private String album_id;
    private String type;
    private String channelid;
    private String pic;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
