package com.main.androiddemo.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by justme on 16/1/23.
 */
public abstract class BaseXAdapter<T> extends BaseAdapter {
    private Context mContext;
    private ArrayList<T> list;

    public BaseXAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<T>();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void deleteAll() {
        if (list != null) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    public void deleteItem(int position) {
        if (list != null && position >= 0 && position < list.size()) {
            list.remove(position);
            notifyDataSetChanged();
        }
    }

    public void addAll(ArrayList<T> temp) {
        if (temp == null) {
            return;
        }
        if (list == null) {
            list = temp;
        } else {
            list.addAll(temp);
        }
        notifyDataSetChanged();
    }

    public void addItem(T temp) {
        if (list != null) {
            addItem(temp, list.size());
        }
    }

    public void addItem(T temp, int index) {
        if (temp == null) {
            return;
        }
        if (list != null) {
            list.add(index, temp);
            notifyDataSetChanged();
        }
    }

}
