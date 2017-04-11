package com.example.sf.filemanager.activitys;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.base.BaseActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navView;

    @Override
    protected void init() {
        initDrawerView(drawerLayout,navView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initDrawerView(DrawerLayout drawer, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            drawer.setFitsSystemWindows(true);
            drawer.setClipToPadding(true);
        }
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
