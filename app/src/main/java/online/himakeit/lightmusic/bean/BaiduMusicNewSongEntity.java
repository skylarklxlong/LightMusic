package online.himakeit.lightmusic.bean;

import java.util.ArrayList;

/**
 * @author：LiXueLong
 * @date：2018/2/6
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicNewSongEntity {
    /**
     * {
     "result": {
     "mix_1": {},
     "mod_29": {},
     "show_list": {},
     "mix_22": {},
     "mix_29": {},
     "entry": {},
     "scene": {},
     "new_song": {},
     "mix_9": {},
     "recsong": {},
     "diy": {},
     "focus": {},
     "mod_27": {},
     "radio": {},
     "king": {}
     },
     "error_code": 22000,
     "module": []
     }
     */

    private BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongMixOneEntity>> mix_1;
    private BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongDiyEntity>> diy;
    private BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongRadioEntity>> radio;

    public BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongMixOneEntity>> getMix_1() {
        return mix_1;
    }

    public void setMix_1(BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongMixOneEntity>> mix_1) {
        this.mix_1 = mix_1;
    }

    public BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongDiyEntity>> getDiy() {
        return diy;
    }

    public void setDiy(BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongDiyEntity>> diy) {
        this.diy = diy;
    }

    public BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongRadioEntity>> getRadio() {
        return radio;
    }

    public void setRadio(BaiduMusicBaseEntity<ArrayList<BaiduMusicNewSongRadioEntity>> radio) {
        this.radio = radio;
    }
}
