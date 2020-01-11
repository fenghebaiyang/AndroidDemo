package com.main.androiddemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.main.androiddemo.R;
import com.main.androiddemo.utils.DensityUtil;

/**
 * @Description
 * @Author xiaojianfeng
 * @Date 2017/10/12 0012 17:56
 */

public class TabMenu extends LinearLayout {

    private Context mContext;

    private int tabTextColor = Color.WHITE;
    private int tabSelectedTextColor = Color.BLACK;

    private int tabIconRes = -1;
    private int tabSelectedIconRes = -1;

    private int tabTextSize;
    private int tabSelectedTextSize;

    private String tabText;
    private boolean isSelected;

    private TextView txt;
    private ImageView img;

    public TabMenu(Context context) {
        super(context);
        this.mContext = context;
        init(null);
    }

    public TabMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    public TabMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        if (mContext == null) return;

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TabMenu);
            tabTextColor = a.getColor(R.styleable.TabMenu_tabTextColor, Color.WHITE);
            tabSelectedTextColor = a.getColor(R.styleable.TabMenu_tabSelectedTextColor, Color.BLACK);
            tabIconRes = a.getResourceId(R.styleable.TabMenu_tabIconRes, R.mipmap.ic_launcher);
            tabSelectedIconRes = a.getResourceId(R.styleable.TabMenu_tabSelectedIconRes, R.mipmap.ic_launcher);
            tabTextSize = a.getDimensionPixelSize(R.styleable.TabMenu_tabTextSize, DensityUtil.sp2px(mContext, 14));
            tabSelectedTextSize = a.getDimensionPixelSize(R.styleable.TabMenu_tabSelectedTextSize, DensityUtil.sp2px(mContext, 14));
            tabText = a.getString(R.styleable.TabMenu_tabText);
            a.recycle();
        }

        View.inflate(mContext, R.layout.view_tab_menu, this);
        txt = (TextView) findViewById(R.id.txt);
        img = (ImageView) findViewById(R.id.img);

        txt.setText(tabText);

        setSelected(false);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if (isSelected) {
            txt.setTextColor(tabSelectedTextColor);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabSelectedTextSize);
            img.setImageResource(tabSelectedIconRes);
        } else {
            txt.setTextColor(tabTextColor);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
            img.setImageResource(tabIconRes);
        }
    }
}
