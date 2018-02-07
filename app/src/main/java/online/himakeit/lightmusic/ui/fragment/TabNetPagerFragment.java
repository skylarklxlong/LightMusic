package online.himakeit.lightmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import online.himakeit.lightmusic.R;

/**
 * @author：LiXueLong
 * @date：2018/1/30
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class TabNetPagerFragment extends BaseFragment {

    ViewPager mViewPager;
    int mPage = 0;
    boolean isFirstLoad = true;

    TabNetPagerNewSongFragment mNewSongFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_net, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_tab_net_container);
        if (mViewPager != null) {
            setupViewPager(mViewPager);
            mViewPager.setOffscreenPageLimit(2);
        }
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.tl_tab_net);
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mNewSongFragment == null) {
            return;
        }
        if (isVisibleToUser && isFirstLoad) {
            mNewSongFragment.requestData();
            isFirstLoad = false;
        }
    }

    private void setupViewPager(ViewPager mViewPager) {
        TabNetPagerViewPagerAdapter mAdapter = new TabNetPagerViewPagerAdapter(getChildFragmentManager());
        mNewSongFragment = new TabNetPagerNewSongFragment();
        mAdapter.addFragment(mNewSongFragment, "新歌");
        mAdapter.addFragment(new TabNetPagerSongListFragment(), "歌单");
        mAdapter.addFragment(new TabNetPagerSongRankFragment(), "排行榜");
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setCurrentItem(mPage);
    }

    static class TabNetPagerViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public TabNetPagerViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
