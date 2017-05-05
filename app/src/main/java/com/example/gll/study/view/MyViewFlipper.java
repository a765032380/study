package com.example.gll.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;
import com.example.gll.study.R;
/**
 * Created by gll on 17-4-27.
 *
 * 因为ViewFlipper没有直接获取View变化的回调接口
 */

public class MyViewFlipper extends ViewFlipper implements GestureDetector.OnGestureListener {

    private Animation animation;
    private Context context;
    //自定义回调接口，传出当前页卡的位置
    private FlipperFacousChangedListener flipperFacousChangedListener;
    //自定义回调接口传出当前滑动状态
    private IsSorollListener isSorollListener;
    //滑动接口
    private GestureDetector gestureDetector;

    public MyViewFlipper(Context context) {
        super(context);
        this.context=context;
    }

    public MyViewFlipper(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        this.context = mContext;
        gestureDetector = new GestureDetector(mContext, this);
        setLongClickable(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public void startFlipping() {
        // TODO Auto-generated method stub
        super.startFlipping();
        setInAnimation(AnimationUtils.loadAnimation(context,
                R.anim.push_left_in));
        setOutAnimation(AnimationUtils.loadAnimation(context,
                R.anim.push_left_out));
    }
    public boolean dispatchTouchEvent(MotionEvent ev){
        gestureDetector.onTouchEvent(ev);//在这里先处理下你的手势左右滑动事件
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
//        stopFlipping();       //用户点击屏幕时，停止滑动
//        setAutoStart(false);   //取消自动滑动
        return this.gestureDetector.onTouchEvent(event);   //把touch事件交给gesture处理
    }
    @Override
    public void showNext() {
        switch (getDisplayedChild()){
                    case 0:
                        flipperFacousChangedListener.onFliperChanged(1);
                        break;
                    case 1:
                        flipperFacousChangedListener.onFliperChanged(2);
                        break;
                    case 2:
                        flipperFacousChangedListener.onFliperChanged(0);
                        break;
                }
        super.showNext();
    }

    @Override
    public void showPrevious() {
        switch (getDisplayedChild()){
        case 0:
            flipperFacousChangedListener.onFliperChanged(2);
            break;
        case 1:
            flipperFacousChangedListener.onFliperChanged(0);
            break;
        case 2:
            flipperFacousChangedListener.onFliperChanged(1);
            break;
    }
        super.showPrevious();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        setAutoStart(false);
        stopFlipping();
        isSorollListener.onIsSoroll(false);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 120) {// 如果是从右向左滑动
            // 注册flipper的进出效果
            setInAnimation(context,R.anim.push_left_in);
            setOutAnimation(context,R.anim.push_left_out);
            showNext();
            setAutoStart(true);
            startFlipping();
            isSorollListener.onIsSoroll(true);
            return true;
        } else if (e1.getX() - e2.getX() < -120) {// 如果是从左向右滑动
            setInAnimation(context,R.anim.push_right_in);
            setOutAnimation(context,R.anim.push_right_out);
            showPrevious();
            setAutoStart(true);
            startFlipping();
            isSorollListener.onIsSoroll(true);
            return true;
        }
        isSorollListener.onIsSoroll(false);
        return false;
    }
    public interface FlipperFacousChangedListener{
        public void onFliperChanged(int index);
    }
    public interface IsSorollListener{
        public void onIsSoroll(boolean isSoroll);
    }

    public void setOnIsSorollListener(IsSorollListener isSorollListener){
        this.isSorollListener=isSorollListener;
    }

    public void setOnFacousChangedListener(FlipperFacousChangedListener flipperFacousChangedListener){
        this.flipperFacousChangedListener=flipperFacousChangedListener;
    }
}
