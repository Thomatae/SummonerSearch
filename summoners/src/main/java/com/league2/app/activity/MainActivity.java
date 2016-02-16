package com.league2.app.activity;

import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.league2.app.Module.DaggerApplication;
import com.league2.app.adapter.DrawerAdapter;
import com.league2.app.adapter.ViewPagerAdapter;
import com.league2.app.event.ProfileUpdatedEvent;
import com.league2.app.event.SelectedGameEvent;
import com.league2.app.event.UserIdEvent;
import com.league2.app.fragment.SelectedGameFragment;
import com.league2.app.fragment.SetUpFragment;
import com.league2.app.R;
import com.league2.app.fragment.SettingsFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.NavigationListener {

    public static final String SUMMONER_ID = "summonerId";

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FrameLayout mContainer;
    private ViewPagerAdapter mViewPagerAdapter;
    private SharedPreferences mSharedPreferences;

    private String mUserName;
    private long mUserId;
    private int mProfileIconId;
    private String TITLES[] = {"Home","Summoner Search", "Favorites"};
    private int ICONS[] = {R.drawable.ic_home, R.drawable.ic_action, R.drawable.ic_champions};
    private boolean mClearedData;

    @Inject Bus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerApplication.inject(this);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        mContainer = (FrameLayout) findViewById(R.id.fragment_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //check if user name exists
        mSharedPreferences = getSharedPreferences(getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
        if (mSharedPreferences.contains(getString(R.string.user_name))) {
            mUserName = mSharedPreferences.getString(getString(R.string.user_name), getString(R.string.default_user_name));
            mUserId = mSharedPreferences.getLong(getString(R.string.user_id), 0);
            mProfileIconId = mSharedPreferences.getInt(getString(R.string.user_profile_icon_id), 0);
            mContainer.setVisibility(View.GONE);
            initializeViewPager();
        } else {
            mUserName = getString(R.string.default_user_name);
            mUserId = 0;
            mProfileIconId = 0;
            initializeHomepageFragment();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new DrawerAdapter(this, this, TITLES , ICONS, mUserName, mProfileIconId);

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }

        };

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDrawerToggle.isDrawerIndicatorEnabled()) {
                    backArrowClicked();
                }
            }
        });
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void initializeViewPager() {
        initializeViewPagerAdapter();
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initializeViewPagerAdapter() {
        //TODO Async Tasks here or inside Fragments themselves?
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.clearAdapter();
        Log.d("UserId main: ", mUserId + "");
        mViewPagerAdapter.setUpAdapter(mUserId);
        mViewPager.removeAllViews();
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    /**
     * This handles the user setting the profile name
     * Also should initiate ViewPager and Tabs
     * */
    @Subscribe
    public void onProfileUpdatedEvent(ProfileUpdatedEvent event) {
        if (event.isClear) {
            mClearedData = true;
            clearProfile();
            updateMenuAdapter();
        } else {
            mContainer.setVisibility(View.GONE);
            updateMenuAdapter();
            initializeViewPager();
        }
    }

    private void updateMenuAdapter() {
        if (mAdapter != null) {
            mUserName = mSharedPreferences.getString(getString(R.string.user_name), getString(R.string.default_user_name));
            mUserId = mSharedPreferences.getLong(getString(R.string.user_id), 0);
            mProfileIconId = mSharedPreferences.getInt(getString(R.string.user_profile_icon_id), 0);
            mAdapter = new DrawerAdapter(this, this, TITLES, ICONS, mUserName, mProfileIconId);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void clearProfile() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(getString(R.string.user_id));
        editor.remove(getString(R.string.user_name));
        editor.remove(getString(R.string.user_profile_icon_id));
        editor.apply();
    }

    @Produce
    public UserIdEvent onProduceUserIdEvent() {
        return new UserIdEvent(mUserId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mBus.unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startPreferenceFragment();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void backArrowClicked() {
        if (mClearedData || mUserId == 0) {
            replaceFragment(new SetUpFragment());
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mClearedData = false;
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mContainer.setVisibility(View.GONE);
            mTabLayout.setVisibility(View.VISIBLE);
        }
    }

    private void startSelectGameFragment(long gameId) {
        mContainer.setVisibility(View.VISIBLE);
        mTabLayout.setVisibility(View.GONE);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        Fragment fragment = SelectedGameFragment.newInstance(gameId);
        replaceFragment(fragment);
    }

    private void startPreferenceFragment() {
        mContainer.setVisibility(View.VISIBLE);
        mTabLayout.setVisibility(View.GONE);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        replaceFragment(new SettingsFragment());
    }

    private void initializeHomepageFragment() {
        //TODO hide view pager stuff
        mTabLayout.setVisibility(View.GONE);
        mContainer.setVisibility(View.VISIBLE);
        addFragment(new SetUpFragment());
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void startHome() {
        Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startSummonerSearch() {
        Toast.makeText(this, "Summoner", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startChampionSearch() {
        Toast.makeText(this, "Champion", Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onSelectedGameEvent(SelectedGameEvent event) {
        startSelectGameFragment(event.mGameId);
    }
}
