package com.ayuan.siyifu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //用来显示操作后的图片
        final ImageView imageView = (ImageView) findViewById(R.id.pre);
        //1.获取要操作图片的原图
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pre19);
        //2.创建一个副本,相当于有了一个和原图大小一样的白纸
        final Bitmap copy = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //创建画笔
        paint = new Paint();
        //创建画布
        canvas = new Canvas(copy);
        canvas.drawBitmap(bitmap, new Matrix(), paint);
        imageView.setImageBitmap(copy);
        //3.给imageView设置触摸事件
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE://移动事件
//                        int x = (int) event.getX() % copy.getWidth();
//                        int y = (int) event.getY() % copy.getHeight();
                        try {
                            for (int i = -20; i < 20; i++) {
                                for (int j = -20; j < 20; j++) {
                                    int x = (int) event.getX() + i;
                                    int y = (int) event.getY() + j;
                                    if (20 >= Math.sqrt(i * i + j * j)) {
                                        copy.setPixel(x, y, Color.TRANSPARENT);
                                    }
                                }
                            }
                            imageView.setImageBitmap(copy);
                        } catch (Exception e) {
                            Log.i(TAG, "有问题");
                        }
                        break;
                }
                return true;
            }
        });
    }
}
