package online.himakeit.lightmusic.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.BaiduMusicSongListContentEntity;
import online.himakeit.lightmusic.bean.BaiduMusicSongListEntity;
import online.himakeit.lightmusic.network.ApiManager;
import online.himakeit.lightmusic.network.BaiduNetCallBack;
import online.himakeit.lightmusic.ui.activity.PlaylistActivity;

/**
 * @author：LiXueLong
 * @date：2018/2/1
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class TabNetPagerSongListFragment extends BaseAttachFragment {

    private FrameLayout mFlContainer;
    private View mMainView;
    private RecyclerView mRecyclerView;
    private int mLastVisibleItem;
    private SongListRecyclerAdapter mAdapter;
    private int pageCount = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_net_song_list, container, false);
        mFlContainer = (FrameLayout) view.findViewById(R.id.fl_tab_net_song_list);
        View mLoadingView = LayoutInflater.from(mContext).inflate(R.layout.layout_loading, mFlContainer, false);
        mFlContainer.addView(mLoadingView);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mMainView == null) {
                mMainView = LayoutInflater.from(mContext).inflate(R.layout.fragment_tab_net_song_list_layout, mFlContainer, false);
                mRecyclerView = (RecyclerView) mMainView.findViewById(R.id.recycler_tabnet_song_list);
                final GridLayoutManager mGridLayout = new GridLayoutManager(mContext, 2);
                mRecyclerView.setLayoutManager(mGridLayout);
                /**
                 * 当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，
                 * 并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()。
                 * （其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged()去整体刷新一下）
                 */
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItem + 1 == mAdapter.getItemCount()) {
                            loadData(++pageCount);
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        mLastVisibleItem = mGridLayout.findLastVisibleItemPosition();
                    }
                });

                loadData(pageCount);
            }
        }
    }

    private void loadData(final int pageNo) {

        ApiManager.getInstance().getSongListData(pageNo, 10, new BaiduNetCallBack<BaiduMusicSongListEntity>() {
            @Override
            public void onSuccess(BaiduMusicSongListEntity baiduMusicSongListEntity) {
                if (baiduMusicSongListEntity != null) {
                    if (baiduMusicSongListEntity.getError_code() == 22000) {
                        if (baiduMusicSongListEntity.getContent() != null && baiduMusicSongListEntity.getContent().size() > 0) {
                            if (pageNo == 1) {
                                mAdapter = new SongListRecyclerAdapter(baiduMusicSongListEntity.getContent());
                                mRecyclerView.setAdapter(mAdapter);
                                mFlContainer.removeAllViews();
                                mFlContainer.addView(mMainView);
                            } else {
                                mAdapter.update(baiduMusicSongListEntity.getContent());
                            }
                        }
                    }
                }
            }
        });

    }

    class SongListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<BaiduMusicSongListContentEntity> mSongContentList;
        public int TYPE_ITEM = 0;
        public int TYPE_FOOTER = 1;
        private LayoutInflater mLayoutInflater;
        private SpannableString spannableString;

        public SongListRecyclerAdapter(ArrayList<BaiduMusicSongListContentEntity> mSongContentList) {
            this.mSongContentList = mSongContentList;

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_earphone);
            ImageSpan imageSpan = new ImageSpan(mContext, bitmap, ImageSpan.ALIGN_BASELINE);
            spannableString = new SpannableString("icon");
            spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        public void update(ArrayList<BaiduMusicSongListContentEntity> mSongContentList) {
            this.mSongContentList = mSongContentList;
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            if (position + 1 == mSongContentList.size() + 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
            if (viewType == TYPE_ITEM) {
                return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_fragment_tab_net_song_list, parent, false));
            } else {
                return new FooterViewHolder(mLayoutInflater.inflate(R.layout.layout_loading, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ItemViewHolder) {
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                final BaiduMusicSongListContentEntity songListContentEntity = mSongContentList.get(position);
                ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(songListContentEntity.getPic_w300()))
                        .setResizeOptions(new ResizeOptions(300, 300))
                        .build();

                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(itemViewHolder.mSimpleDraweeArt.getController())
                        .setImageRequest(imageRequest)
                        .build();

                itemViewHolder.mSimpleDraweeArt.setController(controller);
                itemViewHolder.mTvName.setText(songListContentEntity.getTitle());
                itemViewHolder.mTvCount.setText(spannableString);

                int count = Integer.parseInt(songListContentEntity.getListenum());
                if (count > 10000) {
                    count = count / 10000;
                    itemViewHolder.mTvCount.append(" " + count + "万");
                } else {
                    itemViewHolder.mTvCount.append(" " + songListContentEntity.getListenum());
                }

                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mIntent = new Intent(mContext, PlaylistActivity.class);
                        mIntent.putExtra("playlistid", songListContentEntity.getListid());
                        mIntent.putExtra("islocal", false);
                        mIntent.putExtra("albumart", songListContentEntity.getPic_w300());
                        mIntent.putExtra("playlistname", songListContentEntity.getTitle());
                        mIntent.putExtra("playlistDetail", songListContentEntity.getTag());
                        mIntent.putExtra("playlistcount", songListContentEntity.getListenum());
                        mContext.startActivity(mIntent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mSongContentList == null) {
                return 0;
            }
            return mSongContentList.size() + 1;
        }

        class FooterViewHolder extends RecyclerView.ViewHolder {
            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            SimpleDraweeView mSimpleDraweeArt;
            TextView mTvName, mTvCount;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mSimpleDraweeArt = (SimpleDraweeView) itemView.findViewById(R.id.simple_drawee_item_tab_net_song_list_art);
                mTvName = (TextView) itemView.findViewById(R.id.tv_item_tab_net_song_list_name);
                mTvCount = (TextView) itemView.findViewById(R.id.tv_item_tab_net_song_list_listen_count);
            }
        }
    }
}
