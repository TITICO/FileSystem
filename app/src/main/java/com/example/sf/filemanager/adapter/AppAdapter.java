package com.example.sf.filemanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.entity.LayoutElement;

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
        LayoutElement e=  mApps.get(position);
        holder.icon.setImageDrawable(e.getBitmapDrawable());
        holder.name.setText(e.getTitle());
        holder.size.setText(e.getSize());
    }

    @Override
    public int getItemCount() {
        return mApps.size();
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
