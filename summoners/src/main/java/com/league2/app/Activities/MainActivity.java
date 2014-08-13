package com.league2.app.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.league2.app.Fragments.Homepage;
import com.league2.app.R;
import com.league2.app.Vo.SummonerInfoVo;

public class MainActivity extends ActionBarActivity implements Homepage.Callbacks {

    public static final String SUMMONER_ID = "summonerId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Homepage homepageFragment = new Homepage();
        homepageFragment.setCallbacks(this);
        homepageFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, homepageFragment).commit();
    }

    @Override
    public void setSummonerId(long summonerId) {
        Intent intent = new Intent(this, InfoPagerActivity.class);
        intent.putExtra(SUMMONER_ID, summonerId);
        startActivity(intent);
    }


}
