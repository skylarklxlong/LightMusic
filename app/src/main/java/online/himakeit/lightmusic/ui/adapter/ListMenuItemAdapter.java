package online.himakeit.lightmusic.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.LvMenuItem;

/**
 * @author：LiXueLong
 * @date：2018/1/30
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class ListMenuItemAdapter extends BaseAdapter {

    private final int mIconSize;
    private LayoutInflater mInflater;
    private Context mContext;

    private List<LvMenuItem> mItems = new ArrayList<LvMenuItem>(Arrays.asList(
            new LvMenuItem(R.drawable.topmenu_icn_night, "夜间模式"),
            new LvMenuItem(R.drawable.topmenu_icn_skin, "主题换肤"),
            new LvMenuItem(R.drawable.topmenu_icn_time, "定时关闭音乐"),
            new LvMenuItem(R.drawable.topmenu_icn_vip, "下载歌曲品质"),
            new LvMenuItem(R.drawable.topmenu_icn_exit, "退出")
    ));

    public ListMenuItemAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        mIconSize = mContext.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LvMenuItem item = mItems.get(position);
        switch (item.type) {
            case LvMenuItem.TYPE_NORMAL:
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item_design_drawer, parent, false);
                }
                TextView itemView = (TextView) convertView;
                itemView.setText(item.name);
                Drawable icon = mContext.getResources().getDrawable(item.icon);
                if (icon != null) {
                    icon.setBounds(0, 0, mIconSize, mIconSize);
                    TextViewCompat.setCompoundDrawablesRelative(itemView, icon, null, null, null);
                }
                break;
            case LvMenuItem.TYPE_NO_ICON:
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item_design_drawer_subheader, parent, false);
                }
                TextView subHeader = (TextView) convertView;
                subHeader.setText(item.name);
                break;
            case LvMenuItem.TYPE_SEPARATOR:
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item_design_drawer_separator, parent, false);
                }
                break;
            default:
        }
        return convertView;
    }
}
