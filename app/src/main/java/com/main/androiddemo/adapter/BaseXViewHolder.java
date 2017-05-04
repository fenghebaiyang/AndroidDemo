package com.main.androiddemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/5/4 0004
 */
public class BaseXViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SparseArray<View> views = new SparseArray<View>();
    private RecyclerItemClickListener itemClickListener;

    public BaseXViewHolder(RecyclerItemClickListener itemClickListener, View view) {
        super(view);
        if (itemClickListener != null) {
            itemView.setOnClickListener(this);
        }
        this.itemClickListener = itemClickListener;
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

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.onItemClickListener(v, getAdapterPosition());
        }
    }
}
