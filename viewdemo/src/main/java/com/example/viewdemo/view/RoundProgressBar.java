package com.example.viewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.viewdemo.R;

/**
 * @author JonesYang
 * @Data 2020-12-16
 * @Function 自定义 View 的小 demo，自定义圆形进度条
 */
public class RoundProgressBar extends View {

    private int mRadius;
    private int mColor;
    private int mTextSize;
    private int mProgress = 30;
    private int mLineWidth;
    private Paint mPaint;

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化属性
        initAttrs(context, attrs);
        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        mColor = typedArray.getColor(R.styleable.RoundProgressBar_color, 0xffff0000);
        mProgress = typedArray.getInt(R.styleable.RoundProgressBar_android_progress, 30);
        mTextSize = (int) typedArray.getDimension(R.styleable.RoundProgressBar_android_textSize, dp2px(16));
        mLineWidth = (int) typedArray.getDimension(R.styleable.RoundProgressBar_line_width, dp2px(3));
        mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
        String p = "199";


//        //TODO: something wrong
//        int count = typedArray.getIndexCount();
//        for (int i = 0; i < count; i++) {
//            int index = typedArray.getIndex(i);
//            switch (index) {
//                case R.styleable.RoundProgressBar_color:
//                    mColor = typedArray.getColor(R.styleable.RoundProgressBar_color, 0xffff0000);
//                    break;
//                case R.styleable.RoundProgressBar_android_progress:
//                    mProgress = typedArray.getInt(R.styleable.RoundProgressBar_android_progress, 30);
//                    break;
//                case R.styleable.RoundProgressBar_android_textSize:
//                    mTextSize = (int) typedArray.getDimension(R.styleable.RoundProgressBar_android_textSize, dp2px(16));
//                    break;
//                case R.styleable.RoundProgressBar_line_width:
//                    mLineWidth = (int) typedArray.getDimension(R.styleable.RoundProgressBar_line_width, dp2px(3));
//                    break;
//                case R.styleable.RoundProgressBar_radius:
//                    mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
//                    break;
//            }
//        }
        typedArray.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
    }

    /**
     * dp 向 px 转换
     *
     * @param dpVal
     * @return
     */
    private float dp2px(int dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal,
                getResources().getDisplayMetrics());
    }

    /**
     * 1.mode
     * 2.size
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //通过 MeasureSpec 获取 mode 以及 size
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0;
        //根据不同的 mode 得到不同的 size
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int needWidth = measureWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(widthSize, needWidth);
            } else {
                width = needWidth;
            }
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int needSize = measureHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(needSize, heightSize);
            } else {
                height = needSize;
            }
        }
        //存储测量得到的宽高
        setMeasuredDimension(width, height);
    }

    private int measureHeight() {
        return mRadius * 2;
    }

    private int measureWidth() {
        return mRadius * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //空心
        mPaint.setStyle(Paint.Style.STROKE);
        // 画笔的宽度为正常宽度的四分之一
        mPaint.setStrokeWidth(mLineWidth * 1.0f / 4);
        //绘制里面的小圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2,
                getHeight() / 2 - getPaddingLeft() - mPaint.getStrokeWidth() / 2, mPaint);
        //更改画笔的宽度
        mPaint.setStrokeWidth(mLineWidth);
        canvas.save();

        canvas.translate(getPaddingLeft(), getPaddingTop());
        //绘制圆弧
        float angle = mProgress * 1.0f / 100 * 360;
        canvas.drawArc(new RectF(0, 0, getWidth() - getPaddingLeft() * 2, getHeight() - getPaddingTop() * 2),
                0, angle,
                false,
                mPaint);
        canvas.restore();

        //绘制文本
        String text = mProgress + "%";
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        Rect bound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), bound);
        int textHeight = bound.height();
        canvas.drawText(text, 0, text.length(),
                getWidth() / 2,
                getHeight() / 2 + textHeight / 2,
                mPaint);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    /**
     * 获得进度
     *
     * @return
     */
    public int getProgress() {
        return mProgress;
    }

    //分别用来作为保存 父控件 和 子控件 的状态的 key
    private static final String INSTANCE = "instance";
    private static final String KEY_PROGRESS = "key_progress";

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        // 保存该控件的信息
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PROGRESS, mProgress);
        //保存父控件的信息
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            //获取父控件的数据信息
            Parcelable parcelable = ((Bundle) state).getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            //获取该控件的信息
            mProgress = ((Bundle) state).getInt(KEY_PROGRESS);
        }
    }

}
