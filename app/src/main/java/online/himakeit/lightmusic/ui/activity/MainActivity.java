package online.himakeit.lightmusic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.afollestad.appthemeengine.customizers.ATEActivityThemeCustomizer;

import java.util.ArrayList;
import java.util.List;

import online.himakeit.lightmusic.R;
import online.himakeit.lightmusic.ui.adapter.ListMenuItemAdapter;
import online.himakeit.lightmusic.ui.fragment.TabFriendsPagerFragment;
import online.himakeit.lightmusic.ui.fragment.TabMusicPagerFragment;
import online.himakeit.lightmusic.ui.fragment.TabNetPagerFragment;
import online.himakeit.lightmusic.ui.widget.CustomViewPager;
import online.himakeit.lightmusic.util.ATEUtil;

/**
 * @author：LiXueLong
 * @date：2018/1/30
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class MainActivity extends BaseActivity implements ATEActivityThemeCustomizer{

    private static final String TAG = "MainActivity";

    DrawerLayout mDrawer;
    ActionBar mActionBar;
    ImageView mIvBarNet, mIvBarMusic, mIvBarFriends, mIvBarSearch;
    ListView mLvLeftMenu;
    ArrayList<ImageView> mIvTabsList = new ArrayList<>();

    private boolean isDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        isDarkTheme = ATEUtil.getATEKey(this).equals("dark_theme");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initToolbar();
        initViewPager();
        initUpDrawer();
    }

    private void initUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mLvLeftMenu.addHeaderView(inflater.inflate(R.layout.nav_header_main, mLvLeftMenu, false));
        mLvLeftMenu.setAdapter(new ListMenuItemAdapter(this));
        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Log.e(TAG, "onItemClick: 0");
                        break;
                    case 1:
                        Log.e(TAG, "onItemClick: 1");
                        mDrawer.closeDrawers();
                        break;
                    case 2:
                        Log.e(TAG, "onItemClick: 2");
                        break;
                    case 3:
                        Log.e(TAG, "onItemClick: 3");
                        break;
                    case 4:
                        Log.e(TAG, "onItemClick: 4");
                        break;
                    case 5:
                        Log.e(TAG, "onItemClick: 5");
                        break;
                    default:
                }
            }
        });
    }

    private void initViews() {
        mDrawer = (DrawerLayout) findViewById(R.id.dl_main);
        mLvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
        mIvBarNet = (ImageView) findViewById(R.id.iv_bar_net);
        mIvBarMusic = (ImageView) findViewById(R.id.iv_bar_music);
        mIvBarFriends = (ImageView) findViewById(R.id.iv_bar_friends);
        mIvBarSearch = (ImageView) findViewById(R.id.iv_bar_search);
    }

    private void initViewPager() {
        mIvTabsList.add(mIvBarNet);
        mIvTabsList.add(mIvBarMusic);
        mIvTabsList.add(mIvBarFriends);

        final CustomViewPager mViewPager = (CustomViewPager) findViewById(R.id.vp_main_container);

        TabNetPagerFragment mNetFragment = new TabNetPagerFragment();
        TabMusicPagerFragment mMusicFragment = new TabMusicPagerFragment();
        TabFriendsPagerFragment mFriendsFragment = new TabFriendsPagerFragment();

        CustomViewPagerAdapter mViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(mNetFragment);
        mViewPagerAdapter.addFragment(mMusicFragment);
        mViewPagerAdapter.addFragment(mFriendsFragment);

        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(1);
        mIvBarMusic.setSelected(true);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mIvBarNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
        mIvBarMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });
        mIvBarFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);
            }
        });
        mIvBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, NetSearchWordsActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                MainActivity.this.startActivity(mIntent);
            }
        });
    }

    private void switchTabs(int position) {
        for (int i = 0; i < mIvTabsList.size(); i++) {
            if (position == i) {
                mIvTabsList.get(i).setSelected(true);
            } else {
                mIvTabsList.get(i).setSelected(false);
            }
        }
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mActionBar.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public int getActivityTheme() {
        return isDarkTheme ? R.style.AppThemeDark : R.style.AppThemeLight;
    }

    static class CustomViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> mFragmentList = new ArrayList<>();

        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
