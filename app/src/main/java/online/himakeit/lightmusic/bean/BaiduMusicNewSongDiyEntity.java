package online.himakeit.lightmusic.bean;

/**
 * @author：LiXueLong
 * @date：2018/2/6
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicNewSongDiyEntity {
    /**
     * {
     "error_code": 22000,
     "result": [
     {
     "position": 1,
     "tag": "华语,怀旧,影视原声",
     "songidlist": [],
     "pic": "http://musicugc.qianqian.com/ugcdiy/pic/c3282308b02047311e2a83e159184072.jpg",
     "title": "萦绕在耳边的台剧旋律",
     "collectnum": 146,
     "type": "gedan",
     "listenum": 68580,
     "listid": "515021776"
     },
     {
     "position": 2,
     "tag": "国语,励志",
     "songidlist": [],
     "pic": "http://musicugc.qianqian.com/ugcdiy/pic/f61eaa9dd3d4c4e1c592fa1a7e27605e.jpg",
     "title": "听一首关于梦想的声音",
     "collectnum": 18,
     "type": "gedan",
     "listenum": 3894,
     "listid": "518570521"
     },{},{},{},{}
     ]
     }
     */

    private int position;
    private String tag;
    private String pic;
    private String title;
    private int collectnum;
    private String type;
    private int listenum;
    private String listid;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(int collectnum) {
        this.collectnum = collectnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getListenum() {
        return listenum;
    }

    public void setListenum(int listenum) {
        this.listenum = listenum;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }
}
