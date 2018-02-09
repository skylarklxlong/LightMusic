package online.himakeit.lightmusic.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import online.himakeit.lightmusic.R;

/**
 * @author：LiXueLong
 * @date：2018/2/8
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class QuickControlsFragment extends BaseFragment {

    private ImageView mIvAlbunArt, mIvPlayList, mIvPlayBtn, mIvPlayNext;
    private TextView mTvSongName, mTvSongArtist;
    private ProgressBar mProgressBar;

    public static QuickControlsFragment newInstance() {
        return new QuickControlsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_bottom_quick_controls, container, false);
        initView(mRootView);
        return mRootView;
    }

    private void initView(View mRootView) {
        mIvAlbunArt = (ImageView) mRootView.findViewById(R.id.iv_bottom_playbar_img);
        mIvPlayList = (ImageView) mRootView.findViewById(R.id.iv_bottom_playbar_playlist);
        mIvPlayBtn = (ImageView) mRootView.findViewById(R.id.iv_bottom_playbar_playbtn);
        mIvPlayNext = (ImageView) mRootView.findViewById(R.id.iv_bottom_playbar_playnext);
        mTvSongName = (TextView) mRootView.findViewById(R.id.tv_bottom_playbar_song_name);
        mTvSongArtist = (TextView) mRootView.findViewById(R.id.tv_bottom_playbar_song_author);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.progress_bottom_playbar);

        mIvPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 播放列表按钮
                 */
            }
        });
        mIvPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 播放音乐按钮
                 */
            }
        });
        mIvPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 下一首音乐按钮
                 */
            }
        });
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
