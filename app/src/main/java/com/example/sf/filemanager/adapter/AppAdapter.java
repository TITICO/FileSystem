package com.example.sf.filemanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.entity.LayoutElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by 89003337 on 2017/6/8.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {
    List<LayoutElement> mApps;
    private Context mCtx;

    public AppAdapter(List<LayoutElement> apps, Context ctx) {
        this.mApps = apps;
        this.mCtx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                mCtx).inflate(R.layout.item_app, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LayoutElement e=  mApps.get(position);
        holder.icon.setImageDrawable(e.getBitmapDrawable());
        holder.name.setText(e.getTitle());
        holder.size.setText(e.getSize());
        holder.operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(e,v);
            }
        });
        View parent= (View) holder.icon.getParent();
        parent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startApp(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    private void showPopup(final LayoutElement element,View view){
        PopupMenu menu=new PopupMenu(mCtx,view);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.app_operation_open:
                        startApp(element);
                        break;
                    case R.id.app_operation_backup:
                        backupApp(element);
                        break;
                    case R.id.app_operation_uninstall:
                        uninstallApp(element);
                        break;
                    case R.id.app_operation_attr:
                        mCtx.startActivity(new Intent(
                                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + element.getPermissions())));
                        break;
                    case R.id.app_operation_share:
                        break;
                }
                return false;
            }
        });
        menu.inflate(R.menu.app_option);
        menu.show();
    }

    private void startApp(LayoutElement element){
        Intent i1 = mCtx.getPackageManager().getLaunchIntentForPackage(element.getPermissions());
        if (i1 != null)
            mCtx.startActivity(i1);
        else
            Toast.makeText(mCtx, mCtx.getResources().getString(R.string.not_allowed), Toast.LENGTH_LONG).show();
    }

    private void backupApp(LayoutElement element)  {
        try{
            File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/app_backup");
            if(!dir.exists() || !dir.isDirectory())dir.mkdirs();
            File in = new File(element.getDes());
            FileInputStream fis = new FileInputStream(in);
            File dst=new File(dir,element.getTitle()+"_"+element.getSymlink().substring(element.getSymlink().indexOf("_")+1)+".apk");
            FileOutputStream fos = new FileOutputStream(dst);
            int count;
            byte[] buffer = new byte[256 * 1024];
            while ((count = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fis.close();
            fos.flush();
            fos.close();
            Toast.makeText(mCtx, mCtx.getResources().getString(R.string.app_backup_succeed), Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(mCtx, mCtx.getResources().getString(R.string.app_backup_failed), Toast.LENGTH_LONG).show();
        }
    }

    private boolean uninstallApp(LayoutElement element){
        try {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:" + element.getPermissions()));
            mCtx.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(mCtx, "" + e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;
        public TextView size;
        public TextView operation;
        public ViewHolder(View itemView) {
            super(itemView);
            icon= (ImageView) itemView.findViewById(R.id.icon);
            name= (TextView) itemView.findViewById(R.id.name);
            size= (TextView) itemView.findViewById(R.id.size);
            operation= (TextView) itemView.findViewById(R.id.operation);
        }
    }
}
