package com.main.androiddemo.widget;

import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;

/**
 * @Description
 * @Author xiaojianfeng
 * @Date 2017/10/13 0013 10:08
 */

public class TabMenuSelectedManager {
    private SparseArray<TabMenu> sparseArray = new SparseArray<>();
    private int selectedId = -1;
    private SelectedManagerListener listener;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == selectedId) return;
            sparseArray.get(id).setSelected(true);
            if (sparseArray.get(selectedId) != null)
                sparseArray.get(selectedId).setSelected(false);
            if (listener != null) {
                listener.onSelected(id, sparseArray.get(id));
                listener.unSelected(selectedId, sparseArray.get(selectedId));
            }
            selectedId = id;
        }
    };

    public interface SelectedManagerListener {

        void onSelected(int id, TabMenu tabMenu);

        void unSelected(int id, @Nullable TabMenu tabMenu);
    }

    public void setListener(SelectedManagerListener listener) {
        this.listener = listener;
    }

    public void addTabMenu(TabMenu... tabMenus) {
        sparseArray.clear();
        for (int i = 0; i < tabMenus.length; i++) {
            sparseArray.put(tabMenus[i].getId(), tabMenus[i]);
            tabMenus[i].setOnClickListener(onClickListener);
            tabMenus[i].setSelected(false);
        }
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        if (sparseArray.get(selectedId) != null) {
            sparseArray.get(selectedId).performClick();
        }
    }
}
