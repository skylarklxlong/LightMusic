package online.himakeit.lightmusic.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.BaiduMusicBaseEntity;
import online.himakeit.lightmusic.bean.BaiduMusicNewSongDiyEntity;
import online.himakeit.lightmusic.bean.BaiduMusicNewSongEntity;
import online.himakeit.lightmusic.bean.BaiduMusicNewSongMixOneEntity;
import online.himakeit.lightmusic.bean.BaiduMusicNewSongRadioEntity;
import online.himakeit.lightmusic.network.ApiManager;
import online.himakeit.lightmusic.network.BaiduNetCallBack;
import online.himakeit.lightmusic.ui.activity.AlbumsDetailActivity;
import online.himakeit.lightmusic.ui.activity.PlaylistActivity;
import online.himakeit.lightmusic.ui.activity.RadioDetailActivity;
import online.himakeit.lightmusic.ui.widget.CarouselImageView;
import online.himakeit.lightmusic.util.LogUtils;
import online.himakeit.lightmusic.util.PreferencesUtility;

/**
 * @author：LiXueLong
 * @date：2018/2/1
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class TabNetPagerNewSongFragment extends BaseAttachFragment {

    private ViewGroup mContent;
    private LayoutInflater mLayoutInflater;
    private View mMainLayoutView, mLoadingView, mAnchorRadioView, mNewAlbumView, mRecommendedSongListView;
    private LinearLayout mLlViewContent, mLlItemLayout;
    private boolean isDayFirst;
    private CarouselImageView mCarouselImageView;
    private String mPosition;
    private NewSongCommonRecyclerAdapter mMixOneAdapter, mDiyAdapter, mRadioAdapter;
    private HashMap<String, View> mViewHashMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContent = (ViewGroup) inflater.inflate(R.layout.fragment_tab_net_new_song, container, false);

        mLayoutInflater = LayoutInflater.from(mContext);

        mMainLayoutView = mLayoutInflater.inflate(R.layout.fragment_tab_net_new_song_layout, container, false);
        String date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
        Button mBtnDailySongRecommend = (Button) mMainLayoutView.findViewById(R.id.btn_tab_net_daily_song_recommend);
        mBtnDailySongRecommend.setText(date);

        mLlViewContent = (LinearLayout) mMainLayoutView.findViewById(R.id.ll_tab_net_new_song_layout);
        mLlItemLayout = (LinearLayout) mMainLayoutView.findViewById(R.id.ll_tab_net_new_song_item_change);

        if (!PreferencesUtility.getInstance(mContext).isCurrentDayFirst(date)) {
            PreferencesUtility.getInstance(mContext).setCurrentDate(date);
            View mLoadingRecommendView = mLayoutInflater.inflate(R.layout.layout_loading_recommend, container, false);
            ImageView mIvLoadingRecommend = (ImageView) mLoadingRecommendView.findViewById(R.id.iv_loading_recommend_img);
            RotateAnimation mRotateAnimation = new RotateAnimation(0, 360, 1, 0.5F, 1, 0.5F);
            mRotateAnimation.setDuration(20000L);
            mRotateAnimation.setInterpolator(new LinearInterpolator());
            mRotateAnimation.setRepeatCount(Animation.INFINITE);
            mRotateAnimation.setRepeatMode(Animation.INFINITE);
            mIvLoadingRecommend.startAnimation(mRotateAnimation);
            isDayFirst = true;
            mContent.addView(mLoadingRecommendView);
        }

        mLoadingView = mLayoutInflater.inflate(R.layout.layout_loading, null, false);
        mLlItemLayout.setVisibility(View.INVISIBLE);
        mLlViewContent.addView(mLoadingView);

        TextView mTvChangeItem = (TextView) mMainLayoutView.findViewById(R.id.tv_tab_net_new_song_change_item);
        mTvChangeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/2/3 to item change activity
            }
        });

        mCarouselImageView = (CarouselImageView) mMainLayoutView.findViewById(R.id.carousel_image_view);
        if (!isDayFirst) {
            mContent.addView(mMainLayoutView);
        }

        return mContent;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mCarouselImageView != null) {
                mCarouselImageView.requestFocus();
            }
        }
    }

    public void requestData() {
        reloadAdapter();
    }

    private void reloadAdapter() {

        ApiManager.getInstance().getNewSongData(3, new BaiduNetCallBack<BaiduMusicBaseEntity<BaiduMusicNewSongEntity>>() {
            @Override
            public void onSuccess(BaiduMusicBaseEntity<BaiduMusicNewSongEntity> newSongEntitys) {
                if (newSongEntitys != null) {
                    BaiduMusicNewSongEntity newSongEntity = newSongEntitys.getResult();
                    if (newSongEntity != null) {
                        LogUtils.show("===" + newSongEntity.getRadio().getResult().size());
                        mAnchorRadioView = mLayoutInflater.inflate(R.layout.layout_fragment_tab_net_new_song, mLlViewContent, false);
                        RecyclerView mRecyclerView01 = (RecyclerView) mAnchorRadioView.findViewById(R.id.recycler_tabnet_new_song);
                        GridLayoutManager mGridLayoutManager01 = new GridLayoutManager(mContext, 3);
                        mRecyclerView01.setLayoutManager(mGridLayoutManager01);
                        mRadioAdapter = new NewSongCommonRecyclerAdapter(newSongEntitys, 3);
                        mRecyclerView01.setAdapter(mRadioAdapter);
                        mRecyclerView01.setHasFixedSize(true);
                        TextView mTvMore01 = (TextView) mAnchorRadioView.findViewById(R.id.tv_item_tab_net_new_song_more);
                        TextView mTvCate01 = (TextView) mAnchorRadioView.findViewById(R.id.tv_item_tab_net_new_song_categroy);
                        ImageView mIvImg01 = (ImageView) mAnchorRadioView.findViewById(R.id.iv_item_tab_net_new_song_img);
                        mTvCate01.setText("主播电台");
                        mIvImg01.setImageResource(R.drawable.ic_recommend_anchor_radio);
                        mTvMore01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 2018/2/7 do something
//                        mChangeView.changeTo(1);
                            }
                        });

                        mNewAlbumView = mLayoutInflater.inflate(R.layout.layout_fragment_tab_net_new_song, mLlViewContent, false);
                        RecyclerView mRecyclerView02 = (RecyclerView) mNewAlbumView.findViewById(R.id.recycler_tabnet_new_song);
                        GridLayoutManager mGridLayoutManager02 = new GridLayoutManager(mContext, 3);
                        mRecyclerView02.setLayoutManager(mGridLayoutManager02);
                        mMixOneAdapter = new NewSongCommonRecyclerAdapter(newSongEntitys, 1);
                        mRecyclerView02.setAdapter(mMixOneAdapter);
                        mRecyclerView02.setHasFixedSize(true);
                        TextView mTvMore02 = (TextView) mNewAlbumView.findViewById(R.id.tv_item_tab_net_new_song_more);
                        TextView mTvCate02 = (TextView) mNewAlbumView.findViewById(R.id.tv_item_tab_net_new_song_categroy);
                        ImageView mIvImg02 = (ImageView) mNewAlbumView.findViewById(R.id.iv_item_tab_net_new_song_img);
                        mTvCate02.setText("新专辑上架");
                        mIvImg02.setImageResource(R.drawable.ic_recommend_new_album);
                        mTvMore02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 2018/2/7 do something
//                        mChangeView.changeTo(1);
                            }
                        });

                        mRecommendedSongListView = mLayoutInflater.inflate(R.layout.layout_fragment_tab_net_new_song, mLlViewContent, false);
                        RecyclerView mRecyclerView03 = (RecyclerView) mRecommendedSongListView.findViewById(R.id.recycler_tabnet_new_song);
                        GridLayoutManager mGridLayoutManager03 = new GridLayoutManager(mContext, 3);
                        mRecyclerView03.setLayoutManager(mGridLayoutManager03);
                        mDiyAdapter = new NewSongCommonRecyclerAdapter(newSongEntitys, 2);
                        mRecyclerView03.setAdapter(mDiyAdapter);
                        mRecyclerView03.setHasFixedSize(true);
                        TextView mTvMore03 = (TextView) mRecommendedSongListView.findViewById(R.id.tv_item_tab_net_new_song_more);
                        TextView mTvCate03 = (TextView) mRecommendedSongListView.findViewById(R.id.tv_item_tab_net_new_song_categroy);
                        ImageView mIvImg03 = (ImageView) mRecommendedSongListView.findViewById(R.id.iv_item_tab_net_new_song_img);
                        mTvCate03.setText("推荐歌单");
                        mIvImg03.setImageResource(R.drawable.ic_recommend_song_list);
                        mTvMore03.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 2018/2/7 do something
//                        mChangeView.changeTo(1);
                            }
                        });

//                        mRadioAdapter.update(newSongEntitys);
//                        mMixOneAdapter.update(newSongEntitys);
//                        mDiyAdapter.update(newSongEntitys);

                        mViewHashMap = new HashMap<>();
                        mViewHashMap.put("推荐歌单", mRecommendedSongListView);
                        mViewHashMap.put("最新专辑", mNewAlbumView);
                        mViewHashMap.put("主播电台", mAnchorRadioView);
                        mPosition = PreferencesUtility.getInstance(mContext).getItemPosition();
                        mLlViewContent.removeView(mLoadingView);
                        if (isDayFirst) {
                            mContent.removeAllViews();
                            mContent.addView(mMainLayoutView);
                        }
                        addViews();
                        mLlItemLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPosition == null) {
            return;
        }
        String itemPosition = PreferencesUtility.getInstance(mContext).getItemPosition();
        if (!itemPosition.equals(mPosition)) {
            mPosition = itemPosition;
            mLlViewContent.removeAllViews();
            addViews();
        }
    }

    private void addViews() {
        String[] split = mPosition.split(" ");
        for (int i = 0; i < split.length; i++) {
            mLlViewContent.addView(mViewHashMap.get(split[i]));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCarouselImageView.onDestroy();
    }

    class NewSongCommonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        BaiduMusicBaseEntity<BaiduMusicNewSongEntity> mData;
        int mType;
        ArrayList<BaiduMusicNewSongMixOneEntity> mixOneEntityArrayList = new ArrayList<>();
        ArrayList<BaiduMusicNewSongDiyEntity> diyEntityArrayList = new ArrayList<>();
        ArrayList<BaiduMusicNewSongRadioEntity> radioEntityArrayList = new ArrayList<>();
        SpannableString spannableString;

        public NewSongCommonRecyclerAdapter(BaiduMusicBaseEntity<BaiduMusicNewSongEntity> mData, int mType) {
            this.mData = mData;
            this.mType = mType;
            if (mData != null) {
                mixOneEntityArrayList.addAll(mData.getResult().getMix_1().getResult());
                diyEntityArrayList.addAll(mData.getResult().getDiy().getResult());
                radioEntityArrayList.addAll(mData.getResult().getRadio().getResult());
            }

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_earphone);
            ImageSpan imageSpan = new ImageSpan(mContext, bitmap, ImageSpan.ALIGN_BASELINE);
            spannableString = new SpannableString("icon");
            spannableString.setSpan(imageSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        public void update(BaiduMusicBaseEntity<BaiduMusicNewSongEntity> mData) {
            this.mData = mData;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_tab_net_new_song, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ItemViewHolder) {
                ItemViewHolder viewHolder = (ItemViewHolder) holder;
                if (mType == 1) {
                    if (mixOneEntityArrayList != null && mixOneEntityArrayList.size() > 0) {
                        final BaiduMusicNewSongMixOneEntity mixOneEntity = mixOneEntityArrayList.get(position);
                        Glide.with(mContext)
                                .load(mixOneEntity.getPic())
                                .centerCrop()
                                .placeholder(R.drawable.placeholder_disk_300)
                                .error(R.drawable.placeholder_disk_300)
                                .into(viewHolder.mIvImg);
                        viewHolder.mTvName.setText(mixOneEntity.getTitle());
                        viewHolder.mTvSubName.setText(mixOneEntity.getAuthor());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, AlbumsDetailActivity.class);
                                intent.putExtra("albumid", mixOneEntity.getType_id());
                                intent.putExtra("albumart", mixOneEntity.getPic());
                                intent.putExtra("albumname", mixOneEntity.getTitle());
                                intent.putExtra("albumdetail", mixOneEntity.getDesc());
                                mContext.startActivity(intent);
                            }
                        });

                    }
                } else if (mType == 2) {
                    if (diyEntityArrayList != null && diyEntityArrayList.size() > 0) {
                        final BaiduMusicNewSongDiyEntity diyEntity = diyEntityArrayList.get(position);
                        Glide.with(mContext)
                                .load(diyEntity.getPic())
                                .centerCrop()
                                .placeholder(R.drawable.placeholder_disk_300)
                                .error(R.drawable.placeholder_disk_300)
                                .into(viewHolder.mIvImg);
                        viewHolder.mTvName.setText(diyEntity.getTitle());
                        viewHolder.mTvListenNum.setText(spannableString);
                        int count = diyEntity.getListenum();
                        if (count > 10000) {
                            count = count / 10000;
                            viewHolder.mTvListenNum.append(" " + count + "万");
                        } else {
                            viewHolder.mTvListenNum.append(" " + diyEntity.getListenum());
                        }
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, PlaylistActivity.class);
                                intent.putExtra("playlistid", diyEntity.getListid());
                                intent.putExtra("islocal", false);
                                intent.putExtra("albumart", diyEntity.getPic());
                                intent.putExtra("playlistname", diyEntity.getTitle());
                                intent.putExtra("playlistDetail", diyEntity.getTag());
                                intent.putExtra("playlistcount", diyEntity.getListenum());
                                mContext.startActivity(intent);
                            }
                        });
                    }
                } else if (mType == 3) {
                    if (radioEntityArrayList != null && radioEntityArrayList.size() > 0) {
                        final BaiduMusicNewSongRadioEntity radioEntity = radioEntityArrayList.get(position);
                        Glide.with(mContext)
                                .load(radioEntity.getPic())
                                .centerCrop()
                                .placeholder(R.drawable.placeholder_disk_300)
                                .error(R.drawable.placeholder_disk_300)
                                .into(viewHolder.mIvImg);
                        viewHolder.mTvName.setText(radioEntity.getTitle());
                        viewHolder.mTvSubName.setText(radioEntity.getDesc());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, RadioDetailActivity.class);
                                intent.putExtra("albumid", radioEntity.getAlbum_id());
                                intent.putExtra("albumart", radioEntity.getPic());
                                intent.putExtra("albumname", radioEntity.getTitle());
                                intent.putExtra("artistname", radioEntity.getDesc());
                                mContext.startActivity(intent);
                            }
                        });
                    }
                }
            }

        }

        @Override
        public int getItemCount() {
            if (mType == 1) {
                /**
                 * 新专辑上架
                 */
                if (mixOneEntityArrayList != null && mixOneEntityArrayList.size() > 0) {
                    if (mixOneEntityArrayList.size() < 7) {
                        return mixOneEntityArrayList.size();
                    } else {
                        return 6;
                    }
                }
            } else if (mType == 2) {
                /**
                 * 推荐歌单
                 */
                if (diyEntityArrayList != null && diyEntityArrayList.size() > 0) {
                    if (diyEntityArrayList.size() < 7) {
                        return diyEntityArrayList.size();
                    } else {
                        return 6;
                    }
                }
            } else if (mType == 3) {
                /**
                 * 主播电台
                 */
                if (radioEntityArrayList != null && radioEntityArrayList.size() > 0) {
                    if (radioEntityArrayList.size() < 7) {
                        return radioEntityArrayList.size();
                    } else {
                        return 6;
                    }
                }
            }
            return 0;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            ImageView mIvImg;
            TextView mTvListenNum, mTvName, mTvSubName;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mIvImg = (ImageView) itemView.findViewById(R.id.iv_item_tab_net_new_song_art);
                mTvListenNum = (TextView) itemView.findViewById(R.id.tv_item_tab_net_new_song_listen_num);
                mTvName = (TextView) itemView.findViewById(R.id.tv_item_tab_net_new_song_name);
                mTvSubName = (TextView) itemView.findViewById(R.id.tv_item_tab_net_new_song_subname);
            }
        }
    }
}
