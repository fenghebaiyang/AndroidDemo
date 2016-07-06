package com.main.androiddemo.widget;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * <br/> Description:加载对话框
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/6/15 0015
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public class LoadingDialog extends AlertDialog {

    private Context mContext;

    public LoadingDialog(Context context) {
        super(context);
        init();
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {

    }

}
