package com.example.sf.filemanager.activitys;

import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.base.BaseActivity;
import com.example.sf.filemanager.fragments.AppFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, Toolbar.OnMenuItemClickListener {

    @Bind(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.action_bar)
    Toolbar toolbar;
    @Bind(R.id.container)
    FrameLayout container;
    private Fragment appFrag;
    private long exitTime=0;

    @Override
    protected void init() {
        initDrawerView(drawerLayout, navView);
        disableNavigationViewScrollbars(navView);
        appFrag = new AppFragment();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_l);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 去掉navigationview中menu的滚动条
     *
     * @param navigationView
     */
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    private void initDrawerView(DrawerLayout drawer, NavigationView navView) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams params = getWindow().getAttributes();
//            params.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            drawer.setFitsSystemWindows(true);
//            drawer.setClipToPadding(true);
//        }
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_vedio:
                break;
            case R.id.nav_apk:
                break;
            case R.id.nav_picture:
                break;
            case R.id.nav_music:
                break;
            case R.id.nav_doc:
                break;
            case R.id.nav_quick_access:
                break;
            case R.id.nav_recent_access:
                break;
            case R.id.nav_settings:

                break;
            case R.id.nav_application:
                replaceFragment(R.id.container, appFrag, "APP");
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis()-exitTime>2000){
            exitTime=System.currentTimeMillis();
            Toast.makeText(this,getString(R.string.exit_tip),Toast.LENGTH_SHORT).show();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sort_dir:
                break;
            case R.id.menu_sort_file:
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_paste:
                break;
            case R.id.menu_exit:
                break;
            case R.id.menu_layout:
                break;
            case R.id.menu_home:
                break;
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
