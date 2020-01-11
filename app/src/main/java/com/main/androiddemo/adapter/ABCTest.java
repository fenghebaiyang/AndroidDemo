package com.main.androiddemo.adapter;

import android.content.Context;

import com.main.androiddemo.bean.HuaBanBean;
import com.main.androiddemo.widget.refreshlayout.ExRecyclerAdapter;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/5/4 0004
 */
public class ABCTest extends ExRecyclerAdapter<HuaBanBean> {

    public ABCTest(Context mContext) {
        super(mContext);
    }

    @Override
    public int getConvertViewRes(int viewType) {
        return 0;
    }

    @Override
    public void getItemView(BaseXViewHolder holder, int position) {

    }
}
