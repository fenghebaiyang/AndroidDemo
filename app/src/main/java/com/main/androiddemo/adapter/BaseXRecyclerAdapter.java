package com.main.androiddemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * <br/> Description:recyclerView的基类适配器
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/8/25 0025
 */
public class BaseXRecyclerAdapter extends RecyclerView.Adapter<BaseXRecyclerAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> views = new SparseArray<View>();
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        @SuppressWarnings({"unchecked", "hiding"})
        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = itemView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }
    }
}
