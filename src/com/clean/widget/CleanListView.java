package com.clean.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.clean.R;
import com.clean.database.LogUtil;

public class CleanListView extends ListView implements OnGestureListener {
	private static final String TAG = "CleanListView";

	private final int mTouchSlop;// 判断滑动的一个距离,scroll的时候会用到

	private int mDragOriginalPos;// 手指拖动项原始在列表中的位置
	private int mDragCurrentPos;// 手指拖动的时候，当前拖动项在列表中的位置
	private int mCurrentPos;// 当前数据项中的位置

	private int mDragOffsetX;// 当前视图和屏幕X轴上的距离
	private int mDragOffsetY;// 当前视图和屏幕Y轴上的距离
	private WindowManager mWindowManager;// windows窗口控制类
	private WindowManager.LayoutParams mWindowParams;// 用于控制拖拽项的显示的参数

	private int mUpScrollBounce;// 拖动的时候，开始向上滚动的边界
	private int mDownScrollBounce;// 拖动的时候，开始向下滚动的边界

	/**
	 * 捏
	 * 
	 * @author feng
	 * 
	 */
	public interface OnPinchInListener {

		public void onPinchIn();
	};

	/**
	 * 捏的相反动作
	 * 
	 * @author feng
	 * 
	 */
	public interface OnListViewPinchOutListener {
		public void onPinchOut(int top, int bottom);
	}

	/**
	 * 到底端后向上拉
	 * 
	 * @author feng
	 * 
	 */
	public interface OnListViewDragUpListener {
		public void onDragUp();
	}

	/**
	 * 到顶端后向上拉
	 * 
	 * @author feng
	 * 
	 */
	public interface OnListViewDragDownListener {
		public void onDragDown();
	}

	/**
	 * ListView中的条目向左拉的监听事件
	 * 
	 * @author feng
	 * 
	 */
	public interface OnItemDragLeftListener {
		public void onItemDragLeft(AdapterView<?> parent, View view,
				int position, long id);
	}

	/**
	 * ListView中的条目向右拉的监听事件
	 * 
	 * @author feng
	 * 
	 */
	public interface OnItemDragRightListener {
		public void onItemDragRight(AdapterView<?> parent, View view,
				int position, long id);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		// 捕获 down时间
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			int x = (int) ev.getX();
			int y = (int) ev.getY();

			// 使用系统ListView自带的方法计算出选中的数据项位置
			mDragOriginalPos = mCurrentPos = pointToPosition(x, y);

			// 如果是无效位置（超出边界，分割线等位置），返回
			if (mDragOriginalPos == AdapterView.INVALID_POSITION) {
				return super.onInterceptTouchEvent(ev);
			}
			// 获取选中项View
			ViewGroup view = (ViewGroup) getChildAt(mDragOriginalPos
					- getFirstVisiblePosition());
			// mCurrent点击位置在点击View内的相对位置

			mCurrentPos = y - view.getTop();

			mDragOffsetX = (int) (ev.getRawX() - x);
			mDragOffsetY = (int) (ev.getRawY() - y);

			View textView = view.findViewById(R.id.titleTV);
			if ((textView.getLeft() > x) && (x < textView.getRight())) {
				// 准备拖动
				// 初始化拖动时滚动变量
				// mTouchSlop定义了拖动的偏差位(一般+-10)
				// mUpScrollBounce当在屏幕的上部(上面1/3区域)或者更上的区域，执行拖动的边界，mDownScrollBounce同理定义
				mUpScrollBounce = Math.min(y - mTouchSlop, getHeight() / 3);
				mDownScrollBounce = Math.min(y + mTouchSlop,
						getHeight() * 2 / 3);

				view.setDrawingCacheEnabled(true);
				Bitmap bm = Bitmap.createBitmap(view.getDrawingCache());
				startDrag(bm, y);
			}

		}

		return super.onInterceptTouchEvent(ev);
	}

	private void startDrag(Bitmap bm, int y) {

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		return super.onTouchEvent(ev);
	}

	public CleanListView(Context context) {
		this(context, null, 0);
	}

	public CleanListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	public CleanListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
	@Override
	public boolean onDown(MotionEvent e) {
		LogUtil.e(TAG, "onDown");
		return false;
	}

	// 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
	// 注意和onDown()的区别，强调的是没有松开或者拖动的状态
	@Override
	public void onShowPress(MotionEvent e) {
		LogUtil.e(TAG, "onShowPress");
	}

	// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		LogUtil.e(TAG, "onSingleTapUp");
		return false;
	}

	// 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		LogUtil.e(TAG, "onScroll");
		return false;
	}

	// 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
	@Override
	public void onLongPress(MotionEvent e) {
		LogUtil.e(TAG, "onLongPress");
	}

	// 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		LogUtil.e(TAG, "onFling");
		return false;
	}

}
