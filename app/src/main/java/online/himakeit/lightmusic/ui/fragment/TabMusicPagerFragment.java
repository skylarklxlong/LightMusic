package online.himakeit.lightmusic.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.bean.PlaylistEntity;
import online.himakeit.lightmusic.bean.TabMusicPagerMenuItem;
import online.himakeit.lightmusic.ui.adapter.TabMusicPagerRecyclerAdapter;

/**
 * @author：LiXueLong
 * @date：2018/1/30
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class TabMusicPagerFragment extends BaseFragment {

    TabMusicPagerRecyclerAdapter mAdapter;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    List<TabMusicPagerMenuItem> mMenuItemList = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_music, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_tabmusic);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_tabmusic);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                R.color.refresh_progress_2, R.color.refresh_progress_1);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadAdapter();
            }
        });
        /**
         * 先给adapter设置空数据，异步加载好后更新数据，防止Recyclerview no attach
         */
        mAdapter = new TabMusicPagerRecyclerAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        /**
         * 设置没有item动画
         */
        ((SimpleItemAnimator) (mRecyclerView.getItemAnimator())).setSupportsChangeAnimations(false);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            /**
             * 相当于Fragment的onResume
             */
            reloadAdapter();
        }
    }

    @Override
    public void reloadAdapter() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ArrayList results = new ArrayList();
                setMusicInfo();
                ArrayList<PlaylistEntity> playlistEntities = new ArrayList<>();
                ArrayList<PlaylistEntity> netPlaylistEntities = new ArrayList<>();
                results.addAll(mMenuItemList);
                results.add("创建的歌单");
                results.addAll(playlistEntities);
                if (netPlaylistEntities != null) {
                    results.add("收藏的歌单");
                    results.addAll(netPlaylistEntities);
                }
                if (mAdapter == null) {
                    mAdapter = new TabMusicPagerRecyclerAdapter(mContext);
                }
                mAdapter.updateResults(results, playlistEntities, netPlaylistEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (mContext == null) {
                    return;
                }
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }.execute();

    }

    /**
     * 设置音乐overflow条目
     */
    private void setMusicInfo() {
        // TODO: 2018/1/31  判断系统版本是否在5.0以上，并判断是否有读写存储的权限
        if (true) {
            loadCount(false);
        } else {
            loadCount(true);
        }
    }

    private void loadCount(boolean hasPermission) {
        int localMusicCount = 0, recentMusicCount = 0, downLoadCount = 0, artistsCount = 0;
        if (hasPermission) {
            // TODO: 2018/1/31 操作数据库取出歌曲信息
        }
        setInfo("本地音乐", localMusicCount, R.drawable.music_icn_local, 0);
        setInfo("最近播放", recentMusicCount, R.drawable.music_icn_recent, 1);
        setInfo("下载管理", downLoadCount, R.drawable.music_icn_dld, 2);
        setInfo("我的歌手", artistsCount, R.drawable.music_icn_artist, 3);
    }

    /**
     * 为info设置数据，并放入mlistInfo
     * @param title
     * @param count
     * @param id
     * @param i
     */
    private void setInfo(String title, int count, int id, int i) {
        TabMusicPagerMenuItem menuItem = new TabMusicPagerMenuItem();
        menuItem.title = title;
        menuItem.count = count;
        menuItem.avatar = id;

        if (mMenuItemList.size() < 4) {
            mMenuItemList.add(new TabMusicPagerMenuItem());
        }
        mMenuItemList.set(i, menuItem);
    }
}
