package online.himakeit.lightmusic.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.BaiduMusicSongRankEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongRankSongListEntity;
import online.himakeit.lightmusic.network.ApiManager;
import online.himakeit.lightmusic.network.BaiduNetCallBack;

/**
 * @author：LiXueLong
 * @date：2018/2/1
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class TabNetPagerSongRankFragment extends BaseAttachFragment {

    //新歌榜
    public static int BILLBOARD_NEW_MUSIC = 1;
    //原创音乐榜
    public static int BILLBOARD_ORIGINAL = 200;
    //热歌榜
    public static int BILLBOARD_HOT_MUSIC = 2;
    //欧美金曲榜
    public static int BILLBOARD_EU_UK = 21;
    //King榜
    public static int BILLBOARD_KING = 100;
    //华语金曲榜
    public static int BILLBOARD_NET_MUSIC = 25;
    //经典老歌榜
    public static int BILLBOARD_CLASSIC_OLD = 22;

    int[] mRankCategroyList = new int[]{BILLBOARD_NEW_MUSIC, BILLBOARD_ORIGINAL, BILLBOARD_HOT_MUSIC
            , BILLBOARD_EU_UK, BILLBOARD_KING, BILLBOARD_NET_MUSIC};

    FrameLayout mFlContainer;
    View mMainView;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    SongRankRecyclerAdapter mAdapter;
    ArrayList<BaiduMusicSongRankSongListEntity> mDataList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_net_song_rank, container, false);
        mFlContainer = (FrameLayout) view.findViewById(R.id.fl_tab_net_song_rank);
        View mLoadingView = LayoutInflater.from(mContext).inflate(R.layout.layout_loading, mFlContainer, false);
        mFlContainer.addView(mLoadingView);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mMainView == null) {
                mMainView = LayoutInflater.from(mContext).inflate(R.layout.fragment_tab_net_song_rank_layout, null, false);
                mRecyclerView = (RecyclerView) mMainView.findViewById(R.id.recycler_tabnet_song_rank);
                mLinearLayoutManager = new LinearLayoutManager(mContext);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                mRecyclerView.setHasFixedSize(true);
                mAdapter = new SongRankRecyclerAdapter();
                mRecyclerView.setAdapter(mAdapter);
                loadData();
            }
        }
    }

    private void loadData() {
        for (int i = 0; i < mRankCategroyList.length; i++) {
            ApiManager.getInstance().getSongRankData(mRankCategroyList[i], 0, 3, new BaiduNetCallBack<BaiduMusicSongRankEntity>() {
                @Override
                public void onSuccess(BaiduMusicSongRankEntity baiduMusicSongRankEntity) {
                    if (baiduMusicSongRankEntity != null) {
                        if (baiduMusicSongRankEntity.getSong_list() != null && baiduMusicSongRankEntity.getSong_list().size() > 0) {
                            mDataList.addAll(baiduMusicSongRankEntity.getSong_list());

                            mAdapter.updateAdapter(mDataList);
                            mFlContainer.removeAllViews();
                            mFlContainer.addView(mMainView);
                        }
                    }
                }
            });
        }
    }

    class SongRankRecyclerAdapter extends RecyclerView.Adapter<SongRankRecyclerAdapter.ItemViewHolder> {

        ArrayList<BaiduMusicSongRankSongListEntity> mDataList;
        int[] picArray = {R.drawable.ic_song_rank_new_song, R.drawable.ic_song_rank_original_song,
                R.drawable.ic_song_rank_hot_song, R.drawable.ic_song_rank_classical_song,
                R.drawable.ic_song_rank_acg_song, R.drawable.ic_song_rank_soar_song,};

        public SongRankRecyclerAdapter() {
        }

        public void updateAdapter(ArrayList<BaiduMusicSongRankSongListEntity> mDataList) {
            this.mDataList = mDataList;
            notifyDataSetChanged();
        }

        @Override
        public SongRankRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_fragment_tab_net_song_rank, parent, false));
        }

        @Override
        public void onBindViewHolder(SongRankRecyclerAdapter.ItemViewHolder holder, int position) {
            BaiduMusicSongRankSongListEntity rankSongListEntity01 = mDataList.get(position * 3);
            BaiduMusicSongRankSongListEntity rankSongListEntity02 = mDataList.get(position * 3 + 1);
            BaiduMusicSongRankSongListEntity rankSongListEntity03 = mDataList.get(position * 3 + 2);

            holder.mTv01.setText(rankSongListEntity01.getTitle() + "-" + rankSongListEntity01.getAuthor());
            holder.mTv02.setText(rankSongListEntity02.getTitle() + "-" + rankSongListEntity02.getAuthor());
            holder.mTv03.setText(rankSongListEntity03.getTitle() + "-" + rankSongListEntity03.getAuthor());
            holder.mIvImg.setImageResource(picArray[position]);
        }

        @Override
        public int getItemCount() {
            return mDataList == null ? 0 : mDataList.size() / 3;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView mTv01, mTv02, mTv03;
            ImageView mIvImg;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mIvImg = (ImageView) itemView.findViewById(R.id.iv_item_tab_net_song_rank_img);
                mTv01 = (TextView) itemView.findViewById(R.id.tv_item_tab_net_song_rank_first);
                mTv02 = (TextView) itemView.findViewById(R.id.tv_item_tab_net_song_rank_second);
                mTv03 = (TextView) itemView.findViewById(R.id.tv_item_tab_net_song_rank_third);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getAdapterPosition() > -1) {
                            // TODO: 2018/2/5 跳转到榜单列表
                        }
                    }
                });
            }
        }
    }
}
