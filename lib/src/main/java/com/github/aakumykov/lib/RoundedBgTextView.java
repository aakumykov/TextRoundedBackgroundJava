package com.github.aakumykov.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class RoundedBgTextView extends AppCompatTextView {

    public RoundedBgTextView(Context context) {
        super(context);
    }

    public RoundedBgTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedBgTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
    }
}
