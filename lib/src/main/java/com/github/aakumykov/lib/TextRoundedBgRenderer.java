package com.github.aakumykov.lib;

import android.graphics.Canvas;
import android.text.Layout;

public abstract class TextRoundedBgRenderer {

    private int horizontalPadding;
    private int verticalPadding;

    /**
     * Base class for single and multi line rounded background renderers.
     *
     * @param horizontalPadding the padding to be applied to left & right of the background
     * @param verticalPadding the padding to be applied to top & bottom of the background
     */
    public TextRoundedBgRenderer(int horizontalPadding, int verticalPadding) {
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
    }

    /**
     * Draw the background that starts at the {@code startOffset} and ends at {@code endOffset}.
     *
     * @param canvas Canvas to draw onto
     * @param layout Layout that contains the text
     * @param startLine the start line for the background
     * @param endLine the end line for the background
     * @param startOffset the character offset that the background should start at
     * @param endOffset the character offset that the background should end at
     */
    public abstract void draw(
            Canvas canvas,
            Layout layout,
            int startLine,
            int endLine,
            int startOffset,
            int endOffset
    );

    /**
     * Get the top offset of the line and add padding into account so that there is a gap between
     * top of the background and top of the text.
     *
     * @param layout Layout object that contains the text
     * @param line line number
     */
    protected int getLineTop(Layout layout, int line) {
        RoundedTextBgLayout roundedTextBgLayout = (RoundedTextBgLayout) layout;
        return roundedTextBgLayout.getLineTopWithoutPadding(line) - verticalPadding;
    }

    /**
     * Get the bottom offset of the line and add padding into account so that there is a gap between
     * bottom of the background and bottom of the text.
     *
     * @param layout Layout object that contains the text
     * @param line line number
     */
    protected int getLineBottom(Layout layout, int line) {
        RoundedTextBgLayout roundedTextBgLayout = (RoundedTextBgLayout) layout;
        return roundedTextBgLayout.getLineBottomWithoutPadding(line) - verticalPadding;
    }
}
