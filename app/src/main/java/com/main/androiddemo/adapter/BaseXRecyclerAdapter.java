package com.main.androiddemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/> Description:recyclerView的基类适配器
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/8/25 0025
 */
public abstract class BaseXRecyclerAdapter<T> extends RecyclerView.Adapter<BaseXRecyclerAdapter.ViewHolder> {

    protected Context mContext;
    /**
     * 数据集合
     */
    protected List<T> list;

    public BaseXRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<T>();
    }

    public BaseXRecyclerAdapter(Context mContext, ArrayList<T> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getConvertViewRes(viewType), parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public abstract int getConvertViewRes(int viewType);

    /**
     * <p>   适配器列表添加数据，并刷新
     * <br/> @version 1.0
     * <br/> @createTime 2015/11/20 15:46
     * <br/> @updateTime 2015/11/20 15:46
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param temp list
     */
    public void addAll(List<T> temp) {
        if (temp == null) {
            return;
        }
        int positionStart = getItemCount();
        if (list == null) {
            list = temp;
        } else {
            list.addAll(temp);
        }
        notifyItemRangeChanged(positionStart, temp.size());
    }

    /**
     * <p/>  添加单个Item
     * <br/> @version 1.0
     * <br/> @createTime 2016/1/11 18:03
     * <br/> @updateTime 2016/1/11 18:03
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param temp 对象
     */
    public void addItem(T temp) {
        this.addItem(temp, getItemCount());
    }

    public void addItem(T temp, int position) {
        if (temp == null) {
            return;
        }
        if (position < 0 || position > getItemCount()) {
            return;
        }
        if (list == null) {
            list = new ArrayList<T>();
        }
        list.add(position, temp);
        notifyItemChanged(position);
    }

    /**
     * 清除所有数据，刷新界面
     * <p/>
     * <br/> @version 1.0
     * <br/> @createTime 2015/11/20 15:46
     * <br/> @updateTime 2015/11/20 15:46
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     */
    public void deleteAll() {
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * <p/>
     * <br/> @version 1.0
     * <br/> @createTime 16/8/25 下午10:22
     * <br/> @updateTime 16/8/25 下午10:22
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param position
     */
    public void deleteItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            return;
        }

        if (list != null) {
            list.remove(position);
            notifyItemRangeChanged(position, getItemCount() - position);
        }
    }

    /**
     * <p/>  返回list对象
     * <br/> @version 1.0
     * <br/> @createTime 2016/1/13 13:46
     * <br/> @updateTime 2016/1/13 13:46
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @return list
     */
    public List<T> getList() {
        return list;
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
