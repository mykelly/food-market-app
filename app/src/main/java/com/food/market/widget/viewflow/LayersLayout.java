package com.food.market.widget.viewflow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class LayersLayout extends LinearLayout {
	/**
	 * 自定义图层
	 */
	private HomeViewFlow viewFlow;
	private String TAG = "MyViewFlow";
	boolean onHorizontal = false;
	float x = 0.0f;
	float y = 0.0f;
    private boolean actionUP;
	public LayersLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LayersLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void setView(HomeViewFlow viewFlow) {
		this.viewFlow = viewFlow;
	}

	// 对触屏事件进行重定向
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			onHorizontal = false;
			actionUP=false;
			x = ev.getX();
			y = ev.getY();
			break;
		case MotionEvent.ACTION_UP:
			onHorizontal = false;
			actionUP=true;
			break;
		default:
			break;
		}

		if (HomeViewFlow.onTouch && viewFlow!=null) {
			float dx = Math.abs(ev.getX() - x);
			
			if (dx > 20.0) {
				onHorizontal = true;
			}
			if(actionUP&&dx<1.0){
				onHorizontal = true;
				viewFlow.onclick.onClickPosition();
			}
			if (onHorizontal) {
				return true;
			} else {
				return super.onInterceptTouchEvent(ev);
			}
		} else {
			return super.onInterceptTouchEvent(ev);
		}

	}

	// 对触屏事件进行处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i(TAG, "viewFlow是否被点击：" + HomeViewFlow.onTouch);
		if (HomeViewFlow.onTouch && viewFlow!=null) {
			return viewFlow.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}
}
