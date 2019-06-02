package com.forty7.floatdemo;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TouchTestImageView extends ImageView {

    public TouchTestImageView(Context context) {
        super(context);
        this.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(100, 100);
    }

    float downX, downY;
    float moveX, moveY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getRawX();
            downY = event.getRawY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            moveX = event.getRawX();
            moveY = event.getRawY();

            this.setX(getX() + (moveX - downX));
            this.setY(getY() + (moveY - downY));
            downX = moveX;
            downY = moveY;
        }

        return true;// 返回true 表示 处理Touch事件
//         return super.onTouchEvent(event);

    }


    public void setXY(float x, float y){
        this.setX(x);
        this.setY(y);
    }

}
