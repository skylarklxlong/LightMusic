package online.himakeit.lightmusic.bean;

import java.util.ArrayList;

/**
 * @author：LiXueLong
 * @date：2018/2/6
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class BaiduMusicPicBaseEntity {
    private int error_code;
    private ArrayList<BaiduMusicPicDataEntity> pic;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ArrayList<BaiduMusicPicDataEntity> getPic() {
        return pic;
    }

    public void setPic(ArrayList<BaiduMusicPicDataEntity> pic) {
        this.pic = pic;
    }
}
