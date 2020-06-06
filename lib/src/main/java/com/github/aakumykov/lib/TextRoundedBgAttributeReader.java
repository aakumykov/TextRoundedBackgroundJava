package com.github.aakumykov.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class TextRoundedBgAttributeReader {

    int horizontalPadding;
    int verticalPadding;
    Drawable drawable;
    Drawable drawableLeft;
    Drawable drawableMid;
    Drawable drawableRight;

    /**
     * Reads default attributes that [TextRoundedBgHelper] needs from resources. The attributes read
     * are:
     *
     * - chHorizontalPadding: the padding to be applied to left & right of the background
     * - chVerticalPadding: the padding to be applied to top & bottom of the background
     * - chDrawable: the drawable used to draw the background
     * - chDrawableLeft: the drawable used to draw left edge of the background
     * - chDrawableMid: the drawable used to draw for whole line
     * - chDrawableRight: the drawable used to draw right edge of the background
     */
    public TextRoundedBgAttributeReader(
            Context context,
            @Nullable AttributeSet attrs
    ) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.TextRoundedBgHelper,
                0,
                R.style.RoundedBgTextView
        );

        horizontalPadding = typedArray.getDimensionPixelSize(
                R.styleable.TextRoundedBgHelper_roundedTextHorizontalPadding,
                0
        );

        verticalPadding = typedArray.getDimensionPixelSize(
                R.styleable.TextRoundedBgHelper_roundedTextVerticalPadding,
                0
        );

        drawable = typedArray.getDrawable(
                R.styleable.TextRoundedBgHelper_roundedTextDrawable
        );
        if (null == drawable)
            throw new RuntimeException("Drawable 'TextRoundedBgHelper_roundedTextDrawable' not found");

        drawableLeft = typedArray.getDrawable(
                R.styleable.TextRoundedBgHelper_roundedTextDrawableLeft
        );
        if (null == drawable)
            throw new RuntimeException("Drawable 'TextRoundedBgHelper_roundedTextDrawableLeft' not found");

        drawableMid = typedArray.getDrawable(
                R.styleable.TextRoundedBgHelper_roundedTextDrawableMid
        );
        if (null == drawable)
            throw new RuntimeException("Drawable 'TextRoundedBgHelper_roundedTextDrawableMid' not found");

        drawableRight = typedArray.getDrawable(
                R.styleable.TextRoundedBgHelper_roundedTextDrawableRight
        );
        if (null == drawable)
            throw new RuntimeException("Drawable 'TextRoundedBgHelper_roundedTextDrawableRight' not found");

        typedArray.recycle();
    }
}
