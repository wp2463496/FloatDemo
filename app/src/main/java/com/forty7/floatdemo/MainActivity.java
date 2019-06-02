package com.forty7.floatdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnTouchListener {
    private FrameLayout layAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layAll = findViewById(R.id.lay_all);
//        layAll.setOnTouchListener(this);
//        layAll.addView(new TouchTestImageView(this));
//        layAll.addView(new TouchTestImageView(this));
//        layAll.addView(new TouchTestImageView(this));
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        boolean status = false;
        int eventX = (int) event.getX();
        int eventY = (int) event.getY();
        int action = event.getAction();
//        Log.d("TEST", "eventX = " + eventX + "  eventY = " + eventY);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                layAll.addView(new TouchImageView(this));
                status = true;
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; i++) {
                    Log.d("TEST","pointerid is " +  event.getPointerId(i) + " pointerIndex is " + i);
                }
                status = false;
                break;
            case MotionEvent.ACTION_UP:
                layAll.removeAllViews();
                status = true;
                break;
            case MotionEvent.ACTION_CANCEL:
                status = false;
                break;
        }
        return status;
    }



    private Map<Integer, TouchImageView> imageViewMap = new HashMap();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int pointerId = event.getPointerId(index);
        int pointerIndex = event.findPointerIndex(pointerId);
        int pointerCount = event.getPointerCount();
        int historySize = event.getHistorySize();

        int action = event.getActionMasked();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                float downX = event.getRawX();
                float downY = event.getRawY();
                TouchImageView image = new TouchImageView(this);
                image.setVisibility(View.GONE);
                imageViewMap.put(pointerId, image);
                layAll.addView(image);
                image.setXY(downX - 50, downY - 50); // 50,50为图片的长宽一半（用于计算中心点）
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                float downX1 = event.getRawX();
                float downY1 = event.getRawY();
                TouchImageView images = new TouchImageView(this);
                images.setVisibility(View.GONE);
                imageViewMap.put(pointerId, images);
                layAll.addView(images);
                images.setXY(downX1 - 50, downY1 - 50); // 50,50为图片的长宽一半（用于计算中心点）
                break;
            case MotionEvent.ACTION_MOVE:
                try {
                    for (int i = 0; i < pointerCount; i++) {
                        pointerId = event.getPointerId(i);
                        pointerIndex = event.findPointerIndex(pointerId);
                        Log.d("TEST","pointerId = " + pointerId);
                        TouchImageView imageMove = imageViewMap.get(pointerId);
                        imageMove.setXY(event.getX(pointerIndex) - 50,event.getY(pointerIndex) - 50);
                        imageMove.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case MotionEvent.ACTION_UP:
                layAll.removeAllViews();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                layAll.removeView(imageViewMap.get(pointerId));
                imageViewMap.remove(pointerId);
                break;
        }
        return true;
    }



}

