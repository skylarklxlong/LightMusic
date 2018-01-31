package online.himakeit.lightmusic.bean;

/**
 * @author：LiXueLong
 * @date：2018/1/31
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class TabMusicPagerMenuItem {

    /**
     * 信息标题
     */
    public String title;
    public int count;
    /**
     * 图片ID
     */
    public int avatar;
    public boolean countChanged = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public boolean isCountChanged() {
        return countChanged;
    }

    public void setCountChanged(boolean countChanged) {
        this.countChanged = countChanged;
    }
}
