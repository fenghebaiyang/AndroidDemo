package com.main.androiddemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.main.androiddemo.api.ManagerRequest;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description
 * @Author xiaojianfeng
 * @Date 2017/3/31 下午10:50
 */

public class StepLineManager {
    public static String TYPE_INTENT = "type_intent";
    private volatile static StepLineManager ourInstance;
    private Queue<ActivityType> mapQueue = new LinkedList<>();

    private StepLineManager() {

    }

    public static StepLineManager getInstance() {
        if (ourInstance == null) {
            synchronized (ManagerRequest.class) {
                if (ourInstance == null) {
                    ourInstance = new StepLineManager();
                }
            }
        }
        return ourInstance;
    }

    public StepLineManager addStep(Class c, Integer type) {
        mapQueue.offer(new ActivityType(c, type));
        return this;
    }

    public boolean nextStep(Activity mActivity, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        ActivityType activityType = mapQueue.poll();
        if (activityType != null) {
            bundle.putInt(TYPE_INTENT, activityType.getType());
            Intent intent = new Intent();
            intent.setClass(mActivity, activityType.getCls());
            intent.putExtras(bundle);
            mActivity.startActivityForResult(intent, activityType.getType());
            return true;
        }
        return false;
    }

    public static class ActivityType {
        private Class cls;
        private Integer type;

        public ActivityType(Class cls, Integer type) {
            this.cls = cls;
            this.type = type;
        }

        public Class getCls() {
            return cls;
        }

        public void setCls(Class cls) {
            this.cls = cls;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }
}
