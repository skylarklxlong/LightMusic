package online.himakeit.lightmusic.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import online.himakeit.lightmusic.AppContext;
import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.service.MusicPlayer;

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

    public Runnable mUpdateProgress = new Runnable() {
        @Override
        public void run() {
            long duration = MusicPlayer.duration();
            long position = MusicPlayer.position();
            if (duration > 0 && duration < 627080716) {
                mProgressBar.setProgress((int) (1000 * position / duration));
            }

            if (MusicPlayer.isPlaying()) {
                mProgressBar.postDelayed(mUpdateProgress, 50);
            } else {
                mProgressBar.removeCallbacks(mUpdateProgress);
            }
        }
    };

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

        mProgressBar.postDelayed(mUpdateProgress, 0);

        mIvPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 播放列表按钮
                 */
                AppContext.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: 2018/3/5 播放列表界面
                    }
                }, 60);
            }
        });
        mIvPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 播放音乐按钮
                 */
                mIvPlayBtn.setImageResource(MusicPlayer.isPlaying() ? R.drawable.playbar_btn_pause :
                        R.drawable.playbar_btn_play);
                if (MusicPlayer.getQueueSize() == 0) {
                    Toast.makeText(mContext, "播放列表为空", Toast.LENGTH_SHORT).show();
                } else {
                    AppContext.getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MusicPlayer.playOrPause();
                        }
                    }, 60);
                }
            }
        });
        mIvPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 下一首音乐按钮
                 */
                AppContext.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MusicPlayer.next();
                    }
                }, 60);
            }
        });
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/5 跳转到正在播放界面
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mProgressBar.removeCallbacks(mUpdateProgress);
    }

    @Override
    public void onResume() {
        super.onResume();
        mProgressBar.setProgress(1000);
        mProgressBar.removeCallbacks(mUpdateProgress);
        mProgressBar.postDelayed(mUpdateProgress, 0);
        updateNowplayingCard();
    }

    private void updateNowplayingCard() {
        mTvSongName.setText(MusicPlayer.getTrackName());
        mTvSongArtist.setText(MusicPlayer.getArtistName());
        Glide.with(this)
                .load(MusicPlayer.getAlbumPath())
                .centerCrop()
                .placeholder(R.drawable.placeholder_disk_210)
                .into(mIvAlbunArt);
    }

    public void updateState() {
        if (MusicPlayer.isPlaying()) {
            mIvPlayBtn.setImageResource(R.drawable.playbar_btn_pause);
            mProgressBar.removeCallbacks(mUpdateProgress);
            mProgressBar.postDelayed(mUpdateProgress, 60);
        } else {
            mIvPlayBtn.setImageResource(R.drawable.playbar_btn_play);
            mProgressBar.removeCallbacks(mUpdateProgress);
        }
    }

    @Override
    public void updateTrackInfo() {
        updateNowplayingCard();
        updateState();
    }
}
