package com.github.aakumykov.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.Spanned;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class RoundedBgTextView extends AppCompatTextView {

    private TextRoundedBgHelper textRoundedBgHelper;


    public RoundedBgTextView(Context context) {
        super(context);
        throw new RuntimeException("Cannot use QwertyTextView with constructor with only context argument");
    }

    public RoundedBgTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundedBgTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        CharSequence text = getText();
        Layout layout = getLayout();

        // need to draw bg first so that text can be on top during super.onDraw()
        if (text instanceof Spanned && null != layout)
        {
            canvas.save();
            canvas.translate((float) getTotalPaddingLeft(), (float) getTotalPaddingTop());
            textRoundedBgHelper.draw(canvas, (Spanned) text, layout);
            canvas.restore();
        }

        super.onDraw(canvas);
    }


    private void init(Context context, AttributeSet attrs) {
        TextRoundedBgAttributeReader attributeReader =
                new TextRoundedBgAttributeReader(context, attrs);

        textRoundedBgHelper = new TextRoundedBgHelper(
                attributeReader.horizontalPadding,
                attributeReader.verticalPadding,
                attributeReader.drawable,
                attributeReader.drawableLeft,
                attributeReader.drawableMid,
                attributeReader.drawableRight
        );
    }
}
