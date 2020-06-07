package com.github.aakumykov.lib;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;

public class MultiLineRenderer extends TextRoundedBgRenderer {

    private int horizontalPadding;
    private int verticalPadding;
    private Drawable drawableLeft;
    private Drawable drawableMid;
    private Drawable drawableRight;

    /**
     * Draws the background for text that starts and ends on different lines.
     *
     * @param horizontalPadding the padding to be applied to left & right of the background
     * @param verticalPadding the padding to be applied to top & bottom of the background
     * @param drawableLeft the drawable used to draw left edge of the background
     * @param drawableMid the drawable used to draw for whole line
     * @param drawableRight the drawable used to draw right edge of the background
     */
    public MultiLineRenderer(
            int horizontalPadding,
            int verticalPadding,
            Drawable drawableLeft,
            Drawable drawableMid,
            Drawable drawableRight
    ) {
        super(horizontalPadding, verticalPadding);
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.drawableLeft = drawableLeft;
        this.drawableMid = drawableMid;
        this.drawableRight = drawableRight;
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
        // draw the first line
        int paragDir = layout.getParagraphDirection(startLine);

        int lineEndOffset = (int) ((Layout.DIR_RIGHT_TO_LEFT == paragDir) ?
                layout.getLineLeft(startLine) :
                layout.getLineRight(startLine));

        int lineBottom = getLineBottom(layout, startLine);
        int lineTop = getLineTop(layout, startLine);
        drawStart(canvas, startOffset, lineTop, lineEndOffset, lineBottom);

        // for the lines in the middle draw the mid drawable
        for (int lineNum=startLine+1; lineNum < endLine; lineNum++) {
            lineTop = getLineTop(layout, lineNum);
            lineBottom = getLineBottom(layout, lineNum);
            drawableMid.setBounds(
                    (int) layout.getLineLeft(lineNum) - horizontalPadding,
                    lineTop,
                    (int) layout.getLineRight(lineNum) + horizontalPadding,
                    lineBottom
            );
            drawableMid.draw(canvas);
        }

        // draw the last line
        int lineStartOffset = (int) ((Layout.DIR_RIGHT_TO_LEFT == paragDir) ?
                layout.getLineRight(startLine) + horizontalPadding :
                layout.getLineLeft(startLine) - horizontalPadding);

        lineBottom = getLineBottom(layout, endLine);
        lineTop = getLineTop(layout, endLine);

        drawEnd(canvas, lineStartOffset, lineTop, endOffset, lineBottom);
    }

    /**
     * Draw the first line of a multiline annotation. Handles LTR/RTL.
     *
     * @param canvas Canvas to draw onto
     * @param start start coordinate for the background
     * @param top top coordinate for the background
     * @param end end coordinate for the background
     * @param bottom bottom coordinate for the background
     */
    private void drawStart(
            Canvas canvas,
            int start,
            int top,
            int end,
            int bottom
    ) {
        if (start > end) {
            drawableRight.setBounds(end, top, start, bottom);
            drawableRight.draw(canvas);
        }
        else {
            drawableLeft.setBounds(start, top, end, bottom);
            drawableLeft.draw(canvas);
        }
    }

    /**
     * Draw the last line of a multiline annotation. Handles LTR/RTL.
     *
     * @param canvas Canvas to draw onto
     * @param start start coordinate for the background
     * @param top top position for the background
     * @param end end coordinate for the background
     * @param bottom bottom coordinate for the background
     */
    private void drawEnd(
            Canvas canvas,
            int start,
            int top,
            int end,
            int bottom
    ) {
        if (start > end) {
            drawableLeft.setBounds(end, top, start, bottom);
            drawableLeft.draw(canvas);
        } else {
            drawableRight.setBounds(start, top, end, bottom);
            drawableRight.draw(canvas);
        }
    }
}
