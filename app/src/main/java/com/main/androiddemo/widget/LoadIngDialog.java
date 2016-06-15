package com.main.androiddemo.widget;

import android.app.Dialog;
import android.content.Context;

/**
 * <br/> Description:加载对话框
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/6/15 0015
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public class LoadIngDialog extends Dialog {

    private Context mContext;

    public LoadIngDialog(Context context) {
        super(context);
        init();
    }

    public LoadIngDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected LoadIngDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {

    }

}
