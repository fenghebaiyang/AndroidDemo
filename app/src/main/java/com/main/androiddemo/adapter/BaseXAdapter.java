package com.main.androiddemo.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseXAdapter<T> extends BaseAdapter {
    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 数据集合
     */
    protected List<T> list;

    public BaseXAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<T>();
    }

    public BaseXAdapter(Context mContext, ArrayList<T> list) {
        this.mContext = mContext;
        this.list = list;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getConvertViewRes(position, getItemViewType(position)),parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return getItemView(position, convertView, parent, viewHolder);
    }


    public abstract int getConvertViewRes(int position, int type);

    public abstract View getItemView(int position, View convertView, ViewGroup parent, ViewHolder viewHolder);

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
        if (list == null) {
            list = temp;
        } else {
            list.addAll(temp);
        }
        notifyDataSetChanged();
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
        if (temp == null) {
            return;
        }
        if (list == null) {
            list = new ArrayList<T>();
        }
        list.add(temp);
        notifyDataSetChanged();
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

    public class ViewHolder {

        private SparseArray<View> views = new SparseArray<View>();
        private View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        @SuppressWarnings({"unchecked", "hiding"})
        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }
    }

}
