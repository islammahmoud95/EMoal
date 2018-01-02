package com.example.islammahoud.emoal.ItemActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.islammahoud.emoal.Clientsadapter;
import com.example.islammahoud.emoal.L;
import com.example.islammahoud.emoal.MainActivity;
import com.example.islammahoud.emoal.R;
import com.example.islammahoud.emoal.Sponserfragment;
import com.example.islammahoud.emoal.data.Category;
import com.example.islammahoud.emoal.data.Clients;
import com.example.islammahoud.emoal.data.Subcat;
import com.example.islammahoud.emoal.parser.Requester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class ItemActiv extends AppCompatActivity implements MaterialTabListener {
    private MaterialTabHost mTabHost;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    private int pos;
    private String name;
    private int z=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        setupTabs();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


         pos= getIntent().getIntExtra("position",0);
        name= getIntent().getStringExtra("name");


        mTabHost.setVisibility(View.GONE);
        mPager.setVisibility(View.GONE);
        Fragment fera=ItemFragment.newInstance(pos,name);
        getSupportFragmentManager().beginTransaction().add(R.id.container,fera).commit();
      /*  subcats=new Requester().getsavedsubcatstdata(getApplicationContext());
        int x=subcats.size();
        for (int i=0;i<x;i++)
        {

            for (int j=0;j<x;j++)
            {

                if ( subcats.get(i).getId() == subcats.get(j).getId())
                {
                    z=z+1;
                    Toast.makeText(this,String.valueOf(z),Toast.LENGTH_LONG).show();
                }
            }
            if(z>1)
            {
                mTabHost.setVisibility(View.VISIBLE);
                mPager.setVisibility(View.VISIBLE);
                Fragment fera=ItemFragment.newInstance(pos);
                getSupportFragmentManager().beginTransaction().add(R.id.container,fera).commit();
                Toast.makeText(this,String.valueOf(z),Toast.LENGTH_LONG).show();
                break;
            }
            else {
                mTabHost.setVisibility(View.GONE);
                mPager.setVisibility(View.GONE);
                Fragment fera=ItemFragment.newInstance(pos);
                getSupportFragmentManager().beginTransaction().add(R.id.container,fera).commit();
                Toast.makeText(this,String.valueOf(z),Toast.LENGTH_LONG).show();
                break;
            }
        }*/
    }


    //matrial tab
    public void setupTabs() {
        mTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setFocusable(false);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        //when the page changes in the ViewPager, update the Tabs accordingly
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);

            }
        });
        //Add all the Tabs to the TabHost
        for (int i = 0; i < mAdapter.getCount(); i++) {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setText(mAdapter.getPageTitle(i))
                            // .setIcon(mAdapter.getIcon(i))
                            .setTabListener(this));
        }
    }

    public void onDrawerItemClicked(int i) {
    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        mPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        // int icons[]={R.drawable.icon_calendar,R.drawable.icon_speaker,R.drawable.sponsor2,R.drawable.icon_feedback,R.drawable.aboutus,R.drawable.aboutus};
        String[] titles = {"الرعاة","التصنيفات"};

        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
            L.m("getItem called for " + num);
            switch (num) {
                case 0:
                    setTitle("الرعاة");
                    fragment = Sponserfragment.newInstance(1);
                    break;
                case 1:
                    setTitle("التصنيفات");
                    fragment = Sponserfragment.newInstance(2);
                    break;
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

      /*  private Drawable getIcon(int position) {
            return getResources().getDrawable(icons[position]);
        }*/
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
