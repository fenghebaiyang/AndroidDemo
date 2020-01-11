package com.main.androiddemo.widget.refreshlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.main.androiddemo.R;
import com.main.androiddemo.adapter.BaseXViewHolder;
import com.main.androiddemo.adapter.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/5/4 0004
 */
public abstract class ExRecyclerAdapter<T> extends RecyclerView.Adapter<BaseXViewHolder> {

    protected Context mContext;
    /**
     * 数据集合
     */
    protected List<T> list;

    public static int TYPE_LOAD_MORE = 0x0001;

    private boolean mHasMore = false;

    private boolean mNextLoadEnable = false;
    private boolean mLoadMoreEnable = false;
    private boolean mLoading = false;
//    private LoadMoreView mLoadMoreView = new SimpleLoadMoreView();
//    private RequestLoadMoreListener mRequestLoadMoreListener;
    private boolean mEnableLoadMoreEndClick = false;

    public ExRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<T>();
    }

    public ExRecyclerAdapter(Context mContext, ArrayList<T> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public BaseXViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOAD_MORE) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_default_footer, parent, false);
            return new ExLoadViewHolder(itemView);
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getConvertViewRes(viewType), parent, false);
        return new BaseXViewHolder(itemClickListener, itemView);
    }

    @Override
    public void onBindViewHolder(BaseXViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_LOAD_MORE) {

        } else {
            getItemView(holder, position);
        }
    }

    @Override
    public final int getItemCount() {
        return mHasMore ? getItemRealCount() + 1 : getItemRealCount();
    }

    public int getItemRealCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public final int getItemViewType(int position) {
        if (list != null && position < list.size()) {
            return getItemViewTypes(position);
        } else {
            return TYPE_LOAD_MORE;
        }
    }

    public int getItemViewTypes(int position) {
        return super.getItemViewType(position);
    }

    public abstract int getConvertViewRes(int viewType);

    public abstract void getItemView(BaseXViewHolder holder, int position);

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
        //notifyItemRangeInserted(positionStart, temp.size());
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

    /**
     * <p/>  加入某条数据
     * <br/> @version 1.0
     * <br/> @createTime 2016/8/26 0026 9:20
     * <br/> @updateTime 2016/8/26 0026 9:20
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param temp
     * @param position
     */
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
        notifyItemInserted(position);
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
        int previousSize = getItemCount();
        if (list != null) {
            list.clear();
        }
        if (previousSize > 0) {
            notifyItemRangeRemoved(0, previousSize);
        }
        //notifyDataSetChanged();
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
            notifyItemRemoved(position);
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

    /**
     * item点击事件
     */
    private RecyclerItemClickListener itemClickListener;

    /**
     * <p/>  item的点击事件
     * <br/> @version 1.0
     * <br/> @createTime 2016/1/4 10:54
     * <br/> @updateTime 2016/1/4 10:54
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @return 单个点击事件
     */
    public RecyclerItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    /**
     * <p/>  设置点击事件
     * <br/> @version 1.0
     * <br/> @createTime 2016/1/4 10:53
     * <br/> @updateTime 2016/1/4 10:53
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param itemClickListener Recycler的单个Item的点击事件
     */
    public void setItemClickListener(RecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setHasMore(boolean mHasMore) {
        if (this.mHasMore ^ mHasMore) {
            this.mHasMore = mHasMore;
            if (mHasMore) {
                notifyItemInserted(getItemCount());
            } else {
                notifyItemRemoved(getItemCount());
            }
        }
    }


}
