package com.main.androiddemo.widget.refreshlayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.main.androiddemo.adapter.BaseXAdapter;
import com.main.androiddemo.widget.loadmore.LoadMoreDefaultFooterView;

import java.util.ArrayList;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/5/2 0002
 */
public class LoadBaseXAdapter extends BaseXAdapter {

    private LoadMoreDefaultFooterView footerView;

    public LoadBaseXAdapter(Context mContext) {
        super(mContext);
    }

    public LoadBaseXAdapter(Context mContext, ArrayList list) {
        super(mContext, list);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getConvertViewRes(int position, int type) {
        return 0;
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent, ViewHolder viewHolder) {
        return null;
    }
}
