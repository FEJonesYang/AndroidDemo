package com.example.viewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.viewdemo.R;

/**
 * @author JonesYang
 * @Data 2020-12-16
 * @Function
 */
public class TestView extends View {

    /**
     * 从宏观上来讲，自定义 View 可以分为以下几个步骤：
     * 1、自定义属性，以及属性的获取（主要在构造函数内实现）
     * 2、onMeasure(int widthMeasureSpec, int heightMeasureSpec) 得到它的宽高
     * 3、onDraw(Canvas canvas) 进行绘制
     * 4、异常情况下 Activity 被销毁，数据的保存与恢复
     * */

    private boolean mBoolean = false;
    private String mTest = "Imooc";
    private int mInt = 0;
    private int enumTest = 1;
    private float mIntDimension = 10;
    private Paint mPaint;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();


        /**
         * 自定义属性的相关步骤：
         * 1、values 包下建立一个 attrs.xml 的文件夹
         * 2、layout 布局文件中使用我们自定义的 View（应当重新定义一个域名空间）
         * 3、可以在自定义 View 的构造方法中获取 属性
         * */


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestView);

        //一种获取属性的方式
//        boolean booleanTest = typedArray.getBoolean(R.styleable.TestView_test_boolean, false);
//        String stringTest = typedArray.getString(R.styleable.TestView_test_string);
//        int integerTest = typedArray.getInteger(R.styleable.TestView_test_integer, -1);
//        float dimension = typedArray.getDimension(R.styleable.TestView_test_dimension, 0);
//        int enumTest = typedArray.getInt(R.styleable.TestView_test_enum, 1);
//
//        Log.d("TAG", booleanTest + "," + stringTest + "," + integerTest + "," +
//                dimension + "," + enumTest);

        //另一种获取属性的方式，这是一种相对较安全的方式
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.TestView_test_string:
                    mTest = typedArray.getString(R.styleable.TestView_test_string);
                    break;
                case R.styleable.TestView_test_boolean:
                    mBoolean = typedArray.getBoolean(R.styleable.TestView_test_boolean, false);
                    break;
                case R.styleable.TestView_test_integer:
                    mInt = typedArray.getInt(R.styleable.TestView_test_integer, 0);
                    break;
                case R.styleable.TestView_test_dimension:
                    mIntDimension = typedArray.getLayoutDimension(R.styleable.TestView_test_dimension, 10);
                    break;
                case R.styleable.TestView_test_enum:
                    enumTest = typedArray.getInt(R.styleable.TestView_test_enum, 1);
                    break;
            }
        }
        Log.d("TAG", mTest + "," + mIntDimension + "," + mBoolean + "," +
                mInt + "," + enumTest);
        typedArray.recycle();
    }

    /**
     * 两个要点：
     * 1、mode
     * 2、size
     *
     * @param widthMeasureSpec  父控件宽度
     * @param heightMeasureSpec 父控件高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //测量宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int needWidth = measureWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(widthSize, needWidth);
            } else { //UNSPECIFIED
                width = needWidth;
            }
        }

        //测量高度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int needHeight = measuredHeight() + getPaddingBottom() + getPaddingTop();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(needHeight, heightSize);
            } else {  //UNSPECIFIED
                height = needHeight;
            }
        }

        setMeasuredDimension(width, height);
    }

    private int measuredHeight() {
        return 0;
    }

    private int measureWidth() {
        return 0;
    }


    /**
     * 初始化 画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(0xFFFF0000);
        mPaint.setAntiAlias(true);
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2 - mPaint.getStrokeWidth() / 2, mPaint);
        mPaint.setStrokeWidth(1);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaint);

        //绘制文本，演示状态的保存功能
        mPaint.setTextSize(72);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0);
        canvas.drawText(mTest, 0, mTest.length(), (getWidth() - mPaint.getTextSize()) / 8, getHeight() / 2 + mPaint.getTextSize() / 4, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mTest = "8888";
        invalidate();
        return true;
    }


    private static final String INSTANCE = "instance";
    private static final String KEY_TEXT = "key_text";

    /**
     * 数据保存
     *
     * @return
     */
    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TEXT, mTest);
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        return bundle;
    }

    /**
     * 数据恢复
     *
     * @param state
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            mTest = bundle.getString(KEY_TEXT);
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
