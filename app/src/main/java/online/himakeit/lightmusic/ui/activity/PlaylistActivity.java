package online.himakeit.lightmusic.ui.activity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import online.himakeit.lightmusic.AppContext;
import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.BaiduMusicGeDanInfoDataEntity;
import online.himakeit.lightmusic.bean.BaiduMusicGeDanInfoEntity;
import online.himakeit.lightmusic.bean.MusicInfo;
import online.himakeit.lightmusic.config.Constants;
import online.himakeit.lightmusic.network.ApiManager;
import online.himakeit.lightmusic.network.BaiduNetCallBack;
import online.himakeit.lightmusic.service.MusicPlayer;
import online.himakeit.lightmusic.util.NetUtils;

/**
 * @author：LiXueLong
 * @date：2018/1/31
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class PlaylistActivity extends BaseActivity {

    Toolbar mToolbar;
    RelativeLayout mRlRecyclerHeader, mRlHeaderDetail;
    ImageView mIvAlbumArt, mIvPlayListArt, mIvLove, mIvHeaderComment, mIvHeaderCollect,
            mIvHeaderDownload, mIvHeaderShare;
    View mViewOverLay;
    FrameLayout mFlPlayList, mFLPlayListSecond, mFlTryAgain;
    TextView mTvHeaderAlbumTitleDetail, mTvListenCount, mTvHeaderAlbumTitle, mTvHeaderComment,
            mTvHeaderCollect, mTvHeaderDownload, mTvHeaderShare, mTvTryAgain;
    LinearLayout mLlHeaderComment, mLlHeaderCollect, mLlHeaderDownload, mLlHeaderShare;
    ActionBar mActionBar;
    RecyclerView mRecyclerView;
    PlayListDetailAdapter mAdapter;
    ArrayList<MusicInfo> mDataList = new ArrayList<>();
    ArrayList<BaiduMusicGeDanInfoDataEntity> mGeDanInfoDataEntityList = new ArrayList<>();

    private boolean isLocalPlaylist;
    private String playlistCount, playlsitId, albumPath, playlistName, playlistDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initIntent();

        setContentView(R.layout.activity_playlist);

        initHeaderView();

        setUpToolbar();

        initRecyclerView();

        loadAllLists();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.actionbar_back);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("歌单");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (!isLocalPlaylist) {
            mToolbar.setSubtitle(playlistDetail);
        }
    }

    private void initHeaderView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_playlist);

        mRlRecyclerHeader = (RelativeLayout) findViewById(R.id.rl_playlist_recycler_header);
        mIvAlbumArt = (ImageView) findViewById(R.id.iv_playlist_album_art);
        mViewOverLay = findViewById(R.id.view_playlist_overlay);

        mRlHeaderDetail = (RelativeLayout) findViewById(R.id.rl_playlist_header_detail);
        mFlPlayList = (FrameLayout) findViewById(R.id.fl_playlist);
        mIvPlayListArt = (ImageView) findViewById(R.id.iv_playlist_art);
        mFLPlayListSecond = (FrameLayout) findViewById(R.id.fl_playlist_second);
        mIvLove = (ImageView) findViewById(R.id.iv_playlist_playlove);
        mTvListenCount = (TextView) findViewById(R.id.tv_playlist_header_listen_count);

        mTvHeaderAlbumTitle = (TextView) findViewById(R.id.tv_playlist_header_album_title);
        mTvHeaderAlbumTitleDetail = (TextView) findViewById(R.id.tv_playlist_header_album_detail);

        mLlHeaderCollect = (LinearLayout) findViewById(R.id.ll_playlist_header_collect);
        mIvHeaderCollect = (ImageView) findViewById(R.id.iv_playlist_header_collect);
        mTvHeaderCollect = (TextView) findViewById(R.id.tv_playlist_header_collect);

        mLlHeaderComment = (LinearLayout) findViewById(R.id.ll_playlist_header_comment);
        mIvHeaderComment = (ImageView) findViewById(R.id.iv_playlist_header_comment);
        mTvHeaderComment = (TextView) findViewById(R.id.tv_playlist_header_comment);

        mLlHeaderShare = (LinearLayout) findViewById(R.id.ll_playlist_header_share);
        mIvHeaderShare = (ImageView) findViewById(R.id.iv_playlist_header_share);
        mTvHeaderShare = (TextView) findViewById(R.id.tv_playlist_header_share);

        mLlHeaderDownload = (LinearLayout) findViewById(R.id.ll_playlist_header_download);
        mIvHeaderDownload = (ImageView) findViewById(R.id.iv_playlist_header_download);
        mTvHeaderDownload = (TextView) findViewById(R.id.tv_playlist_header_download);

        mFlTryAgain = (FrameLayout) findViewById(R.id.fl_playlist_try_again);
        mTvTryAgain = (TextView) findViewById(R.id.tv_playlist_try_again);

        SpannableString mSpannStr = new SpannableString("cion");
        mSpannStr.setSpan(new ImageSpan(this,
                        BitmapFactory.decodeResource(getResources(),
                                R.drawable.ic_earphone), ImageSpan.ALIGN_BASELINE),
                0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvListenCount.setText(mSpannStr);
        if (playlistCount == null) {
            playlistCount = "0";
        }
        int count = Integer.parseInt(playlistCount);
        if (count > 10000) {
            count = count / 10000;
            mTvListenCount.append(" " + count + "万");
        } else {
            mTvListenCount.append(" " + count);
        }


        mLlHeaderDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/6 download btn
            }
        });
        mLlHeaderCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/6 collect btn
            }
        });
        mLlHeaderShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/6 share btn
            }
        });
        mLlHeaderComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/6 comment btn
            }
        });

        if (!isLocalPlaylist) {
            mRlHeaderDetail.setVisibility(View.GONE);
        }

        if (Integer.parseInt(playlsitId) == Constants.FAV_PLAYLIST) {
            mFLPlayListSecond.setVisibility(View.VISIBLE);
        }

        mTvTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAllLists();
            }
        });
    }

    private void loadAllLists() {
        if (isLocalPlaylist) {
            View loadView = LayoutInflater.from(this).inflate(R.layout.layout_loading, mFlTryAgain, false);
            mFlTryAgain.addView(loadView);
            loadLocalPlayInfo();
            return;
        }

        if (NetUtils.hasNetWorkConection(this)) {
            mTvTryAgain.setVisibility(View.GONE);
            View loadView = LayoutInflater.from(this).inflate(R.layout.layout_loading, mFlTryAgain, false);
            mFlTryAgain.addView(loadView);
            loadNetPlayInfo();
        } else {
            mTvTryAgain.setVisibility(View.VISIBLE);
        }
    }

    private void loadNetPlayInfo() {
        ApiManager.getInstance().getGeDanInfo(playlsitId, new BaiduNetCallBack<BaiduMusicGeDanInfoEntity>() {
            @Override
            public void onSuccess(BaiduMusicGeDanInfoEntity baiduMusicGeDanInfoEntity) {
                mFlTryAgain.removeAllViews();
                if (baiduMusicGeDanInfoEntity != null) {
                    playlistDetail = baiduMusicGeDanInfoEntity.getDesc();
                    ArrayList<BaiduMusicGeDanInfoDataEntity> geDanInfoDataEntityArrayList = baiduMusicGeDanInfoEntity.getContent();
                    int size = geDanInfoDataEntityArrayList.size();
                    for (int i = 0; i < size; i++) {
                        BaiduMusicGeDanInfoDataEntity infoDataEntity = geDanInfoDataEntityArrayList.get(i);
                        mGeDanInfoDataEntityList.add(infoDataEntity);
                    }

                    for (int i = 0; i < mGeDanInfoDataEntityList.size(); i++) {
                        BaiduMusicGeDanInfoDataEntity dataEntity = mGeDanInfoDataEntityList.get(i);
                        MusicInfo musicInfo = new MusicInfo();
                        musicInfo.songId = Integer.parseInt(dataEntity.getSong_id());
                        musicInfo.musicName = dataEntity.getTitle();
                        musicInfo.artist = dataEntity.getAlbum_title();
                        musicInfo.islocal = false;
//                        musicInfo.albumName = dataEntity.getAlbum_title();
                        musicInfo.albumId = Integer.parseInt(dataEntity.getAlbum_id());
//                        musicInfo.artistId = Integer.parseInt(dataEntity.getAll_artist_id());
//                        musicInfo.lrc = dataEntity.getLearn();
//                        musicInfo.albumData = dataEntity.getPic_radio();
                        mDataList.add(musicInfo);
                    }
                    mAdapter.updateDataSet(mDataList);
                }
            }
        });
    }

    private void loadLocalPlayInfo() {
        mFlTryAgain.removeAllViews();
    }

    private void initIntent() {
        if (getIntent().getExtras() != null) {
            isLocalPlaylist = getIntent().getBooleanExtra("islocal", false);
            playlsitId = getIntent().getStringExtra("playlistid");
            albumPath = getIntent().getStringExtra("albumart");
            playlistName = getIntent().getStringExtra("playlistname");
            playlistDetail = getIntent().getStringExtra("playlistDetail");
            playlistCount = getIntent().getStringExtra("playlistcount");

        }
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_playlist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new PlayListDetailAdapter(mDataList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    class PlayListDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        final static int FIRST_ITEM = 0;
        final static int ITEM = 1;
        private ArrayList<MusicInfo> arraylist;
        private Context mContext;

        public PlayListDetailAdapter(ArrayList<MusicInfo> arraylist, Context mContext) {
            this.arraylist = arraylist;
            this.mContext = mContext;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == FIRST_ITEM) {
                return new CustomItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_playlist_custom_header, parent, false));
            } else {
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_playlist_item, parent, false));
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position == FIRST_ITEM ? FIRST_ITEM : ITEM;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof CustomItemViewHolder) {
                CustomItemViewHolder viewHolder = (CustomItemViewHolder) holder;
                viewHolder.mTvNum.setText("（共" + arraylist.size() + "首）");
                viewHolder.mIvSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2018/3/6 CustomItemViewHolder select btn click
                    }
                });
            } else if (holder instanceof ItemViewHolder) {
                ItemViewHolder viewHolder = (ItemViewHolder) holder;
                final MusicInfo musicInfo = arraylist.get(position - 1);

                /**
                 * 判断该条目音乐是否在播放
                 */
                if (MusicPlayer.getCurrentAudioId() == musicInfo.songId) {
                    viewHolder.mTvRankNum.setVisibility(View.GONE);
                    viewHolder.mIvPlayState.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.mTvRankNum.setVisibility(View.VISIBLE);
                    viewHolder.mIvPlayState.setVisibility(View.GONE);
                    viewHolder.mTvRankNum.setText(position + "");
                }

                viewHolder.mTvSongTitle.setText(musicInfo.musicName);
                viewHolder.mTvSongAuthor.setText(musicInfo.artist);
                viewHolder.mIvPlayMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (musicInfo.islocal) {
                            // TODO: 2018/3/6 to MoreFragment
                        } else {
                            // TODO: 2018/3/6 to NetMoreFragment
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return arraylist == null ? 0 : arraylist.size() + 1;
        }

        public void updateDataSet(ArrayList<MusicInfo> arraylist) {
            this.arraylist = arraylist;
            notifyDataSetChanged();
        }

        public class CustomItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView mTvNum;
            ImageView mIvSelect;
            RelativeLayout mRlCustom;

            public CustomItemViewHolder(View itemView) {
                super(itemView);

                mTvNum = (TextView) itemView.findViewById(R.id.tv_playlist_playcount);
                mIvSelect = (ImageView) itemView.findViewById(R.id.iv_playlist_list);
                mRlCustom = (RelativeLayout) itemView.findViewById(R.id.rl_playlist_custom);

                mRlCustom.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                AppContext.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HashMap<Long, MusicInfo> infoHashMap = new HashMap<>();
                        int len = arraylist.size();
                        long[] list = new long[len];
                        for (int i = 0; i < len; i++) {
                            MusicInfo musicInfo = arraylist.get(i);
                            list[i] = musicInfo.songId;
                            infoHashMap.put(list[i], musicInfo);
                        }
                        if (getAdapterPosition() > -1) {
                            MusicPlayer.playAll(infoHashMap, list, 0, false);
                        }
                    }
                }, 70);
            }
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView mTvRankNum, mTvSongTitle, mTvSongAuthor;
            ImageView mIvPlayState, mIvPlayMenu;

            public ItemViewHolder(View itemView) {
                super(itemView);

                mTvRankNum = (TextView) itemView.findViewById(R.id.tv_playlist_ranknum);
                mTvSongTitle = (TextView) itemView.findViewById(R.id.tv_playlist_song_title);
                mTvSongAuthor = (TextView) itemView.findViewById(R.id.tv_playlist_song_author);
                mIvPlayState = (ImageView) itemView.findViewById(R.id.iv_playlist_playstate);
                mIvPlayMenu = (ImageView) itemView.findViewById(R.id.iv_playlist_playmenu);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                AppContext.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: 2018/3/5 这里有问题，怎么会是播放所有呢？
                        HashMap<Long, MusicInfo> infoHashMap = new HashMap<>();
                        int len = arraylist.size();
                        long[] list = new long[len];
                        for (int i = 0; i < len; i++) {
                            MusicInfo musicInfo = arraylist.get(i);
                            list[i] = musicInfo.songId;
                            infoHashMap.put(list[i], musicInfo);
                        }
                        if (getAdapterPosition() > -1) {
                            MusicPlayer.playAll(infoHashMap, list, 0, false);
                        }
                    }
                }, 70);
            }
        }
    }
}
