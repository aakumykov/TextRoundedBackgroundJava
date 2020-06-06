package com.github.aakumykov.lib;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;

public class SingleLineRenderer extends TextRoundedBgRenderer {

    private int horizontalPadding;
    private int verticalPadding;
    private Drawable drawable;

    /**
     * Base class for single and multi line rounded background renderers.
     *
     * @param horizontalPadding the padding to be applied to left & right of the background
     * @param verticalPadding   the padding to be applied to top & bottom of the background
     */
    public SingleLineRenderer(
            int horizontalPadding,
            int verticalPadding,
            Drawable drawable
    ) {
        super(horizontalPadding, verticalPadding);
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.drawable = drawable;
    }


    @Override
    public void draw(
            Canvas canvas,
            Layout layout,
            int startLine,
            int endLine,
            int startOffset,
            int endOffset
    ) {
        int lineTop = getLineTop(layout, startLine);
        int lineBottom = getLineBottom(layout, startLine);

        // get min of start/end for left, and max of start/end for right since we don't
        // the language direction
        int left = Math.min(startOffset, endOffset);
        int right = Math.max(startOffset, endOffset);

        drawable.setBounds(left, lineTop, right, lineBottom);
        drawable.draw(canvas);
    }
}
