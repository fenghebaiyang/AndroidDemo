package com.main.androiddemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 自动计算list高度的Listview控件
 * 
 * @Description 在将listview嵌套到Scrollview、gridview、listview等Scroll组件里边可使用此控件保证数据显示完整并避免事件冲突
 * @author 叶青
 * @version 1.0
 * @date 2014年12月31日
 */
public class AutoSizeListView extends ListView {

	/** view大小更改监听 */
	private OnSizeChangeListener listener;

	public AutoSizeListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public AutoSizeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AutoSizeListView(Context context) {
		super(context);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		if (listener != null) {
			listener.onChange(w, h, oldw, oldh);
		}
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	/**
	 * 设置size大小更改监听
	 * 
	 * @version 1.0
	 * @createTime 2013-11-28,下午4:31:49
	 * @updateTime 2013-11-28,下午4:31:49
	 * @createAuthor 叶青
	 * @updateAuthor 叶青
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 */
	public void setOnSizeChangListener(OnSizeChangeListener onSizeChangeListener) {
		this.listener = onSizeChangeListener;
	}

	/**
	 * view 大小更改监听事件
	 * 
	 * @Description
	 * @author 叶青
	 * @version 1.0
	 * @date 2013-11-28
	 */
	public interface OnSizeChangeListener {

		/**
		 * View 大小更改监听事件
		 * 
		 * @version 1.0
		 * @createTime 2013-11-28,下午4:18:33
		 * @updateTime 2013-11-28,下午4:18:33
		 * @createAuthor 叶青
		 * @updateAuthor 叶青
		 * @updateInfo (此处输入修改内容,若无修改可不写.)
		 * 
		 * @param w
		 *            新的宽度
		 * @param h
		 *            新的高度
		 * @param oldw
		 *            旧的宽度
		 * @param oldh
		 *            新的高度
		 */
		public void onChange(int w, int h, int oldw, int oldh);
	}

}
