package com.example.sf.filemanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 89003337 on 2017/4/17.
 */

public  abstract class BaseRecycleViewAdapter extends RecyclerView.Adapter{
    private List data;
    private LayoutInflater inflater;

    private void BaseRecycleViewAdapter(Context context,List data){
        this.data=data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            init(holder,position,data);
    }

    protected abstract void init(RecyclerView.ViewHolder holder, int position, List data);

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }
}
