package com.main.androiddemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/12/22 0022
 */
public class EasyBehavior extends CoordinatorLayout.Behavior<TextView> {
    public EasyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        child.setX(dependency.getX() + 200);
        child.setY(dependency.getY() + 200);
        child.setText(dependency.getX() + "," + dependency.getY());
        return true;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, TextView child, View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);
        child.setText("has remove");
    }
}
