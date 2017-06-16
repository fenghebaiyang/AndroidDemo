package com.main.androiddemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.main.androiddemo.R;
import com.main.androiddemo.adapter.BaseXAdapter;

import java.util.ArrayList;

/**
 * @Description 9宫格样式展示网络图片
 * @Author xiaojianfeng
 * @Date 16/6/26 上午11:09
 */
public class GridImagesDisplay extends AutoSizeGridView {

    private Context mContext;
    private BaseXAdapter<String> adapter;

    public GridImagesDisplay(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public GridImagesDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public GridImagesDisplay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init() {
        adapter = new BaseXAdapter<String>(mContext) {
            @Override
            public int getConvertViewRes(int position, int type) {
                return R.layout.item_grid_images_display;
            }

            @Override
            public View getItemView(int position, View convertView, ViewGroup parent, ViewHolder viewHolder) {
                AspectRatioImageView img = viewHolder.getView(R.id.img);
                if (list.size() == 1) {
                    img.setProportion(4 / 3);
                } else {
                    img.setProportion(1);
                }
                Glide.with(mContext).load(list.get(position)).into(img);
                return convertView;
            }
        };
        setAdapter(adapter);
    }


    /**
     * <p/>  添加图片
     * <br/> @version 1.0
     * <br/> @createTime 2016/5/25 0025 16:18
     * <br/> @updateTime 2016/5/25 0025 16:18
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param list 初始数据
     */
    public void setImageList(ArrayList<String> list) {
        if (list == null) return;
        if (list.size() == 1) {
            setNumColumns(1);
        } else if (list.size() == 2 || list.size() == 4) {
            setNumColumns(2);
        } else {
            setNumColumns(3);
        }
        if (adapter != null) {
            adapter.deleteAll();
            adapter.addAll(list);
        }
    }
}
