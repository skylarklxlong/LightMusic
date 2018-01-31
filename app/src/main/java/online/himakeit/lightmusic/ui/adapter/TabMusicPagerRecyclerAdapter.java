package online.himakeit.lightmusic.ui.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import online.himakeit.lightmusic.AppContext;
import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.PlaylistEntity;
import online.himakeit.lightmusic.bean.TabMusicPagerMenuItem;
import online.himakeit.lightmusic.ui.activity.LocalMusicManagerActivity;
import online.himakeit.lightmusic.ui.activity.PlaylistActivity;
import online.himakeit.lightmusic.ui.activity.PlaylistManagerActivity;
import online.himakeit.lightmusic.ui.activity.RecentPlaylistActivity;
import online.himakeit.lightmusic.util.ToastUtil;

/**
 * @author：LiXueLong
 * @date：2018/1/31
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class TabMusicPagerRecyclerAdapter extends RecyclerView.Adapter<TabMusicPagerRecyclerAdapter.ItemViewHolder> {

    private ArrayList<PlaylistEntity> playlistEntities, netPlaylistEntities = new ArrayList<>();
    private boolean createdExpanded = true;
    private boolean collectExpanded = true;
    private Context mContext;
    private ArrayList itemResults = new ArrayList();
    private boolean isLoveList = true;
    private LayoutInflater mInflater;

    public TabMusicPagerRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void updateResults(ArrayList itemResults, ArrayList<PlaylistEntity> playlistEntities,
                              ArrayList<PlaylistEntity> netPlaylistEntities) {
        isLoveList = true;
        this.itemResults = itemResults;
        this.playlistEntities = playlistEntities;
        this.netPlaylistEntities = netPlaylistEntities;
    }

    public void updatePlaylistInfos(ArrayList<PlaylistEntity> playlistEntities) {
        this.playlistEntities = playlistEntities;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View inflateMusic = mInflater.inflate(R.layout.item_fragment_tab_music, parent, false);
                ItemViewHolder musicViewHolder = new ItemViewHolder(inflateMusic);
                return musicViewHolder;
            case 1:
                if (isLoveList) {
                    View inflatePlayListLove = mInflater.inflate(R.layout.item_fragment_tab_music_playlist_love, parent, false);
                    ItemViewHolder musicPlayListViewHolder = new ItemViewHolder(inflatePlayListLove);
                    return musicPlayListViewHolder;
                }
                View inflatePlayListLove = mInflater.inflate(R.layout.item_fragment_tab_music_playlist, parent, false);
                ItemViewHolder musicPlayListViewHolder = new ItemViewHolder(inflatePlayListLove);
                return musicPlayListViewHolder;
            case 2:
            case 3:
                View inflateExpandable = mInflater.inflate(R.layout.item_fragment_tab_music_expandable, parent, false);
                ItemViewHolder musicExpandableViewHolder = new ItemViewHolder(inflateExpandable);
                return musicExpandableViewHolder;
            default:
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                TabMusicPagerMenuItem item = (TabMusicPagerMenuItem) itemResults.get(position);
                holder.mIvItemImg.setImageResource(item.avatar);
                holder.mTvItemTitle.setText(item.title);
                holder.mTvItemCount.setText("(" + item.count + ")");
                setItemOnListener(holder, position);
                break;
            case 1:
                PlaylistEntity playlistEntity = (PlaylistEntity) itemResults.get(position);
                if ((createdExpanded && "local".equals(playlistEntity.author))
                        || (collectExpanded && !"local".equals(playlistEntity.author))) {
                    if (playlistEntity.albumArt != null) {
                        holder.mSdvPlayListImg.setImageURI(Uri.parse(playlistEntity.albumArt));
                    }
                    holder.mTvPlayListTitle.setText(playlistEntity.name);
                    holder.mTvPlayListCount.setText(playlistEntity.songCount + "首");
                }
                setOnPlaylistListener(holder, position, playlistEntity.id, playlistEntity.albumArt, playlistEntity.name);
                isLoveList = false;
                break;
            case 2:
                holder.mIvExpandableImg.setImageResource(R.drawable.list_icn_arr_right);
                holder.mTvExpandableTitle.setText("创建的歌单" + "(" + playlistEntities.size() + ")");
                setExpandableListener(holder, position);
                break;
            case 3:
                holder.mIvExpandableImg.setImageResource(R.drawable.list_icn_arr_right);
                holder.mTvExpandableTitle.setText("收藏的歌单" + "(" + playlistEntities.size() + ")");
                setExpandableListener(holder, position);
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        if (itemResults == null) {
            return 0;
        }

        if (!createdExpanded && playlistEntities != null) {
            itemResults.removeAll(playlistEntities);
        }

        if (!collectExpanded) {
            itemResults.removeAll(netPlaylistEntities);
        }

        return itemResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == 0) {
            return -1;
        }
        if (itemResults.get(position) instanceof TabMusicPagerMenuItem) {
            return 0;
        }
        if (itemResults.get(position) instanceof PlaylistEntity) {
            return 1;
        }
        if (itemResults.get(position) instanceof String) {
            if ("收藏的歌单".equals((String) (itemResults.get(position)))) {
                return 3;
            }
        }
        return 2;
    }

    private void setOnPlaylistListener(ItemViewHolder holder, final int position, final long playlistid,
                                       final String albumArt, final String playlistname) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppContext.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mIntent = new Intent(mContext, PlaylistActivity.class);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        mIntent.putExtra("islocal", true);
                        mIntent.putExtra("playlistid", playlistid + "");
                        mIntent.putExtra("albumart", albumArt);
                        mIntent.putExtra("playlistname", playlistname);
                        mContext.startActivity(mIntent);
                    }
                }, 60);
            }
        });

        holder.mIvPlayListMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu mPopupMenu = new PopupMenu(mContext, v);
                mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (position == 5) {
                            ToastUtil.showShort("无法删除此歌单");

                        } else {
                            new MaterialDialog.Builder(mContext)
                                    .title("确定删除歌曲吗？")
                                    .positiveText("确定")
                                    .negativeText("取消")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            // TODO: 2018/1/31 删除歌曲并发送通知广播
                                            dialog.dismiss();
                                        }
                                    })
                                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        return true;
                    }
                });
                mPopupMenu.inflate(R.menu.menu_popup_item);
                mPopupMenu.show();
            }
        });
    }

    private void setItemOnListener(ItemViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppContext.getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent mIntent = new Intent(mContext, LocalMusicManagerActivity.class);
                                mIntent.putExtra("page_number", 0);
                                mContext.startActivity(mIntent);
                            }
                        }, 60);
                    }
                });

                break;
            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppContext.getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent mIntent = new Intent(mContext, RecentPlaylistActivity.class);
                                mContext.startActivity(mIntent);
                            }
                        }, 60);
                    }
                });
                break;
            case 2:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppContext.getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent mIntent = new Intent(mContext, RecentPlaylistActivity.class);
                                mContext.startActivity(mIntent);
                            }
                        }, 60);
                    }
                });
                break;
            case 3:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppContext.getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent mIntent = new Intent(mContext, LocalMusicManagerActivity.class);
                                mIntent.putExtra("page_number", 1);
                                mContext.startActivity(mIntent);
                            }
                        }, 60);
                    }
                });
                break;
            default:
        }
    }

    private void setExpandableListener(ItemViewHolder holder, int position) {
        holder.mIvExpandableMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, PlaylistManagerActivity.class);
                mContext.startActivity(mIntent);
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mIvItemImg, mIvPlayListMenu, mIvExpandableImg, mIvExpandableMenu;
        TextView mTvItemTitle, mTvItemCount, mTvPlayListTitle, mTvPlayListCount, mTvExpandableTitle;
        SimpleDraweeView mSdvPlayListImg;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mIvItemImg = itemView.findViewById(R.id.iv_tab_music_item_img);
            mTvItemTitle = itemView.findViewById(R.id.tv_tab_music_item_title);
            mTvItemCount = itemView.findViewById(R.id.tv_tab_music_item_count);

            mSdvPlayListImg = itemView.findViewById(R.id.simple_drawee_tab_music_playlist_img);
            mIvPlayListMenu = itemView.findViewById(R.id.iv_tab_music_playlist_menu);
            mTvPlayListTitle = itemView.findViewById(R.id.tv_tab_music_playlist_title);
            mTvPlayListCount = itemView.findViewById(R.id.tv_tab_music_playlist_count);

            mIvExpandableImg = itemView.findViewById(R.id.iv_tab_music_expandable_img);
            mIvExpandableMenu = itemView.findViewById(R.id.iv_tab_music_expandable_menu);
            mTvExpandableTitle = itemView.findViewById(R.id.tv_tab_music_expandable_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ObjectAnimator anim = null;
            anim = ObjectAnimator.ofFloat(mIvExpandableImg, "rotation", 90, 0);
            anim.setDuration(100);
            anim.setRepeatCount(0);
            anim.setInterpolator(new LinearInterpolator());
            switch (getItemViewType()) {
                case 2:
                    if (createdExpanded) {
                        itemResults.removeAll(playlistEntities);
                        updateResults(itemResults, playlistEntities, netPlaylistEntities);
                        notifyItemRangeRemoved(5, playlistEntities.size());
                        anim.start();
                        createdExpanded = false;
                    } else {
                        itemResults.removeAll(netPlaylistEntities);
                        itemResults.remove("收藏的歌单");
                        itemResults.addAll(playlistEntities);
                        itemResults.add("收藏的歌单");
                        itemResults.addAll(netPlaylistEntities);
                        updateResults(itemResults, playlistEntities, netPlaylistEntities);
                        notifyItemRangeInserted(5, playlistEntities.size());
                        anim.reverse();
                        createdExpanded = true;
                    }
                    break;
                case 3:
                    if (collectExpanded) {
                        itemResults.removeAll(netPlaylistEntities);
                        updateResults(itemResults, playlistEntities, netPlaylistEntities);
                        notifyItemRangeRemoved(6 + playlistEntities.size(), netPlaylistEntities.size());
                        anim.start();
                        collectExpanded = false;
                    } else {
                        itemResults.addAll(netPlaylistEntities);
                        updateResults(itemResults, playlistEntities, netPlaylistEntities);
                        notifyItemRangeInserted(6 + playlistEntities.size(), netPlaylistEntities.size());
                        anim.reverse();
                        collectExpanded = true;
                    }
                    break;
                default:
            }
        }
    }
}
