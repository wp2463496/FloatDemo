package com.forty7.floatdemo;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TouchImageView extends ImageView {

    public TouchImageView(Context context) {
        super(context);
        this.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(100, 100);
    }

//    float downX, downY;
//    float moveX, moveY;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            downX = event.getRawX();
//            downY = event.getRawY();
//        }
//
//        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            moveX = event.getRawX();
//            moveY = event.getRawY();
//
//            this.setX(getX() + (moveX - downX));
//            this.setY(getY() + (moveY - downY));
//            downX = moveX;
//            downY = moveY;
//        }
//
//        return true;// 返回true 表示 处理Touch事件
////         return super.onTouchEvent(event);
//
//    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean status = false;// 返回true 表示 处理Touch事件 false 表示 不处理Touch事件
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                status = false;
                break;
            case MotionEvent.ACTION_MOVE:
                status = false;
                break;
            case MotionEvent.ACTION_UP:
                status = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                status = false;
                break;
        }
        return status;
    }


    public void setXY(float x, float y){
        this.setX(x);
        this.setY(y);
    }

}
