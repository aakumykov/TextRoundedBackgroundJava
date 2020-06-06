package com.github.aakumykov.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.Spanned;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class QwertyTextView extends AppCompatTextView {

    private TextRoundedBgHelper textRoundedBgHelper;

    public QwertyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TextRoundedBgAttributeReader attributeReader = new TextRoundedBgAttributeReader(context, attrs);

        textRoundedBgHelper = new TextRoundedBgHelper(
                attributeReader.horizontalPadding,
                attributeReader.verticalPadding,
                attributeReader.drawable,
                attributeReader.drawableLeft,
                attributeReader.drawableMid,
                attributeReader.drawableRight
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        CharSequence text = getText();
        Layout layout = getLayout();

        // need to draw bg first so that text can be on top during super.onDraw()
        if (text instanceof Spanned && null != layout)
        {
            canvas.translate((float) getTotalPaddingLeft(), (float) getTotalPaddingRight());

            textRoundedBgHelper.draw(canvas, (Spanned) text, layout);

            canvas.restoreToCount(canvas.getSaveCount());
        }

        super.onDraw(canvas);
    }
}
