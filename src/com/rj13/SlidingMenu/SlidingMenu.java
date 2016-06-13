package com.rj13.SlidingMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView{
   /**
    * Ϊʹ���Զ�������ʱ���ù��췽��
    * @param context
    * @param attrs
    */
	private LinearLayout mWapper;
	private  ViewGroup mMenu;
	private  ViewGroup mContent;
	private  int mScreenWidth;
	private  boolean  once=false;
	private  int mMenuWitdh;
	
	private int mMenuRightPadding=1000 ;  //����Ļ�Ҳ�ľ���
	
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
         WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
         DisplayMetrics outMetrics=new  DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth=outMetrics.widthPixels;
		//��dpת����Ϊpx������
		mMenuRightPadding=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,context.getResources().getDisplayMetrics())+60;
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
		if(!once){
		   mWapper=(LinearLayout)getChildAt(0);
		   mMenu=(ViewGroup)mWapper.getChildAt(0);
		   mContent=(ViewGroup)mWapper.getChildAt(1);
		 
		   mMenuWitdh= mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
		   mContent.getLayoutParams().width=mScreenWidth;
		  // mWapper.getLayoutParams().width=mMenu.getLayoutParams().width+mContent.getLayoutParams().width;
		   //��ΪmContent��mmenu�Ƿ�����mwapper�У����������������ʡ��
		   once=true;
		}  
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			// TODO Auto-generated method stub
			super.onLayout(changed, l, t, r, b);
			if(changed){
				
				this.scrollBy(mMenuWitdh, 0);
			}
		} 

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX=getScrollX();
			if(scrollX>=mMenuWitdh/2)
			{
				this.smoothScrollTo(mMenuWitdh, 0);
			}else
			{
				this.smoothScrollTo(0, 0);
			}
            return true;
		}
		return super.onTouchEvent(ev);
	}

}