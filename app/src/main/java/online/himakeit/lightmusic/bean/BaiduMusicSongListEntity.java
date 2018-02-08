package online.himakeit.lightmusic.bean;

import java.util.ArrayList;

/**
 * @author：LiXueLong
 * @date：2018/2/3
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicSongListEntity {
    /**
     * {
     * "content": [
     * {"collectnum": "2",
     * "desc": "李圣杰1973年生于台湾的一个天主教家庭，其祖先是最早到高雄传教的天主教信徒。1979年，李父为了吸引当地的人们到教堂做礼拜，特地从台北请了一支唱诗班来演唱，李圣杰开始对音乐产生兴趣。1997年李圣杰参加了吴宗宪主持的综艺节目《超级新人王》的歌唱比赛，凭借英文歌曲《I swear》获得冠军，比赛结束后，作为该比赛评委的音乐人鲍比达找到了李圣杰，邀请他出唱片。2004年与滚石唱片签约，发行了自己的成名专辑《绝对痴心·手放开》，2006年发行专辑《关于你的歌》。之后几年便渐渐的淡出了歌坛，直到2018年参加《歌手2》重回大众视野，才让大家重新爱上这把好嗓音。
     * 李圣杰的嗓音个性十足，极具辨识度，其真假声的自由转换尽显痴心情歌的魅力。他的歌曲具有人人都会唱的特质，让大家对他总有一份亲切感，当你失恋、分手、心痛的时候第一个想到李圣杰的歌。本单精选李圣杰滚石黄金时期的经典佳作，带你感受这独具魅力的深情嗓音。",
     * "height": "300",
     * "listenum": "1120",
     * "listid": "518461587",
     * "pic_300": "http://musicugc.qianqian.com/ugcdiy/pic/04faac04c797e29823447b105e864f71.jpg",
     * "pic_w300": "http://musicugc.qianqian.com/ugcdiy/pic/04faac04c797e29823447b105e864f71.jpg",
     * "songIds": ["564031","623597","488343","563816","563855","564081","564305","563929","300063","465227"],
     * "tag": "华语,流行",
     * "title": "抒情男声李圣杰，唱尽男人心事",
     * "width": "300"},
     * {},{},{},{},{},{},{},{},{}
     * ],
     * "error_code": 22000,
     * "havemore": 1,
     * "total": 10597
     * }
     */

    private int error_code;
    private int havemove;
    private int total;
    private ArrayList<BaiduMusicSongListContentEntity> content;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getHavemove() {
        return havemove;
    }

    public void setHavemove(int havemove) {
        this.havemove = havemove;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<BaiduMusicSongListContentEntity> getContent() {
        return content;
    }

    public void setContent(ArrayList<BaiduMusicSongListContentEntity> content) {
        this.content = content;
    }
}
