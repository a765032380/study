package com.example.gll.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.gll.study.R;
import com.example.gll.study.view.MyViewFlipper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gll on 17-4-26.
 */

public class MyActivity extends AppCompatActivity{
    private MyViewFlipper mViewFlipper;
    private ImageView image_one,image_two,image_three;
    private int resId []={R.drawable.aa1,R.drawable.a2,R.drawable.a3};
    private List<ImageView> imageList;
    public boolean isSoroll=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        mViewFlipper= (MyViewFlipper) findViewById(R.id.viewflipper);
        image_one= (ImageView) findViewById(R.id.image_one);
        image_two= (ImageView) findViewById(R.id.image_two);
        image_three= (ImageView) findViewById(R.id.image_three);
        imageList=new ArrayList<>();
        imageList.add(image_one);
        imageList.add(image_two);
        imageList.add(image_three);
        for (int i=0;i<resId.length;i++){
            mViewFlipper.addView(getImage(resId[i]));
        }
        mViewFlipper.setFlipInterval(3000);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setOnFacousChangedListener(new MyViewFlipper.FlipperFacousChangedListener() {
            @Override
            public void onFliperChanged(int index) {
                Log.i("TEST",String.valueOf(index));
//                for (int i=0;i<imageList.size();i++){
//                    imageList.get(i).setBackgroundResource(R.drawable.head_1);
//                }
//                switch (index){
//                    case 0:
//                        imageList.get(1).setBackgroundResource(R.drawable.head_3);
//                        break;
//                    case 1:
//                        imageList.get(2).setBackgroundResource(R.drawable.head_3);
//                        break;
//                    case 2:
//                        imageList.get(0).setBackgroundResource(R.drawable.head_3);
//                        break;
//                }
                for (int i=0;i<imageList.size();i++){
                    if (i==index){
                        imageList.get(i).setBackgroundResource(R.drawable.head_3);
                    }else{
                        imageList.get(i).setBackgroundResource(R.drawable.head_1);
                    }
                }

            }
        });
        mViewFlipper.setInAnimation(this, R.anim.push_left_in);
        mViewFlipper.setOutAnimation(this,R.anim.push_left_out);
        mViewFlipper.setOnIsSorollListener(new MyViewFlipper.IsSorollListener() {
            @Override
            public void onIsSoroll(boolean isSoroll) {
                MyActivity.this.isSoroll=isSoroll;
            }
        });


    }

//    public boolean dispatchTouchEvent(MotionEvent ev){
//        this.detector.onTouchEvent(ev);//在这里先处理下你的手势左右滑动事件
//        return super.dispatchTouchEvent(ev);
//    }

        @Override
    public boolean onTouchEvent(MotionEvent event) {
            return mViewFlipper.onTouchEvent(event);
        }


    private ImageView getImage(int resId){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(resId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSoroll) {
                    Toast.makeText(MyActivity.this, "你点击了第" + String.valueOf(mViewFlipper.getDisplayedChild() + 1) + "张图片", Toast.LENGTH_LONG).show();
                    Log.i("TOG", "你点击了第" + String.valueOf(mViewFlipper.getDisplayedChild() + 1) + "张图片");
                }
            }
        });

        return imageView;
    }
}
