package com.league2.app.activity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.league2.app.fragment.Homepage;
import com.league2.app.R;

public class MainActivity extends AppCompatActivity {

    public static final String SUMMONER_ID = "summonerId";

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private SharedPreferences mSharedPreferences;

    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if user name exists
            mSharedPreferences = getSharedPreferences(getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
            if (mSharedPreferences.contains(getString(R.string.user_name))) {
               mUserName = mSharedPreferences.getString(getString(R.string.user_name), getString(R.string.default_user_name));
            }


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


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


        if (savedInstanceState == null) {
            initializeHomepageFragment();
        }
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
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Homepage()).commit();
    }
}
