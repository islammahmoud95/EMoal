package com.example.islammahoud.emoal;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Visibility;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.islammahoud.emoal.data.Category;
import com.example.islammahoud.emoal.parser.Requester;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MaterialTabListener {

    private MaterialTabHost mTabHost;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    private AppBarLayout mAppBarLayout;
    private ArrayList<Category> categories;
    private Context context;
    private int catid;
    private Toolbar toolbar;
    private int color;
    private FloatingActionButton fab1,fab2,fab3,fab;
    boolean isOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupTabs();
         fab = (FloatingActionButton) findViewById(R.id.fab);
         fab1 = (FloatingActionButton) findViewById(R.id.fab1);
         fab2 = (FloatingActionButton) findViewById(R.id.fab2);
         fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }

            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
categories=new Requester().getsavedcatdata(getApplicationContext());
        int x=categories.size();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        for (int i=0;i<x;i++)
        {
            menu.add(categories.get(i).getId(),categories.get(i).getId(),categories.get(i).getId(),categories.get(i).getName());
            menu.setGroupCheckable(categories.get(i).getId(),true,true);
            menu.getItem(i+1).setIcon(R.drawable.e);
            catid=categories.get(i).getId();

        }
        mAppBarLayout= (AppBarLayout) findViewById(R.id.app_par);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_button) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            setTitle("الرعاة");
            mTabHost.setVisibility(View.VISIBLE);
            mPager.setVisibility(View.VISIBLE);
            ActionBar actionBar = getSupportActionBar();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new Sponserfragment()).commit();
            // Handle the camera action
        } else if(id != R.id.home) {
            setTitle(item.getTitle());
            mTabHost.setVisibility(View.GONE);
            mPager.setVisibility(View.GONE);
            Fragment fera=SubcatFragment.newInstance(id);
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fera).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    private void showFABMenu(){
        isOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        fab1.animate().translationX(getResources().getDimension(R.dimen.fab1));
        fab2.animate().translationX(getResources().getDimension(R.dimen.fab2));
        fab3.animate().translationX(getResources().getDimension(R.dimen.fab3));

    }

    private void closeFABMenu(){
        isOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
        fab1.animate().translationX(0);
        fab2.animate().translationX(0);
        fab3.animate().translationX(0);
    }
}