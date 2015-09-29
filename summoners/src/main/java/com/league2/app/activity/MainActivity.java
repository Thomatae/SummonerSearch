package com.league2.app.activity;

import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.league2.app.Module.DaggerApplication;
import com.league2.app.adapter.DrawerAdapter;
import com.league2.app.adapter.ViewPagerAdapter;
import com.league2.app.event.ProfileUpdatedEvent;
import com.league2.app.event.UserIdEvent;
import com.league2.app.fragment.SetUpFragment;
import com.league2.app.R;
import com.league2.app.fragment.RankedFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

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

    private SharedPreferences mSharedPreferences;

    private String mUserName;
    private long mUserId;
    private String TITLES[] = {"Home","Summoner Search", "Champions"};
    private int ICONS[] = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};

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
            mContainer.setVisibility(View.GONE);
            initializeViewPager();
        } else {
            mUserName = getString(R.string.default_user_name);
            mUserId = 0;
            initializeHomepageFragment();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new DrawerAdapter(this,TITLES , ICONS, mUserName, R.drawable.ic_launcher);

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

        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void initializeViewPager() {
        initializeViewPagerAdapter();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initializeViewPagerAdapter() {
        //TODO Async Tasks here or inside Fragments themselves?
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setUpAdapter(mUserId);
        mViewPager.removeAllViews();
        mViewPager.setAdapter(adapter);
    }

    /**
     * This handles the user setting the profile name
     * Also should initiate ViewPager and Tabs
     * */
    @Subscribe
    public void onProfileUpdatedEvent(ProfileUpdatedEvent event) {
        if (mAdapter != null) {
            mUserName = mSharedPreferences.getString(getString(R.string.user_name), getString(R.string.default_user_name));
            mUserId = mSharedPreferences.getLong(getString(R.string.user_id), 0);
            mAdapter = new DrawerAdapter(this, TITLES, ICONS, mUserName, R.drawable.ic_launcher);
            mRecyclerView.setAdapter(mAdapter);
        }

        mContainer.setVisibility(View.GONE);
        initializeViewPager();
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeHomepageFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SetUpFragment()).commit();
    }
}
