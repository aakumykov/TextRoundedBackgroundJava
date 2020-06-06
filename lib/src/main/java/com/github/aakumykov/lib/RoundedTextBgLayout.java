package com.github.aakumykov.lib;

import android.os.Build;
import android.text.Layout;
import android.text.TextPaint;

public abstract class RoundedTextBgLayout extends Layout {

    private final static float DEFAULT_LINESPACING_EXTRA = 0f;
    private final static float DEFAULT_LINESPACING_MULTIPLIER = 1f;


    /**
     * Subclasses of Layout use this constructor to set the display text,
     * width, and other standard properties.
     *
     * @param text        the text to render
     * @param paint       the default paint for the layout.  Styles can override
     *                    various attributes of the paint.
     * @param width       the wrapping width for the text.
     * @param align       whether to left, right, or center the text.  Styles can
     *                    override the alignment.
     * @param spacingMult factor by which to scale the font size to get the
     *                    default line spacing
     * @param spacingAdd  amount to add to the default line spacing
     */
    protected RoundedTextBgLayout(CharSequence text, TextPaint paint, int width, Alignment align, float spacingMult, float spacingAdd) {
        super(text, paint, width, align, spacingMult, spacingAdd);
    }


    /**
     * Returns the top of the Layout after removing the extra padding applied by  the Layout.
     */
    public int getLineTopWithoutPadding(int line) {
        int lineTop = getLineTop(line);
        if (0 == line) {
            lineTop -= getTopPadding();
        }
        return lineTop;
    }

    /**
     * Returns the bottom of the Layout after removing the extra padding applied by the Layout.
     */
    public int getLineBottomWithoutPadding(int line) {
        int lineBottom = getLineBottomWithoutSpacing(line);
        if (line == (getLineCount() - 1)) {
            lineBottom -= getBottomPadding();
        }
        return lineBottom;
    }


    /**
     * Get the line bottom discarding the line spacing added.
     */
    public int getLineBottomWithoutSpacing(int line) {
        int lineBottom = getLineBottom(line);
        boolean lastLineSpacingNotAdded = Build.VERSION.SDK_INT >= 19;
        boolean isLastLine = line == (getLineCount() - 1);

        int lineBottomWithoutSpacing;
        float lineSpacingExtra = getSpacingAdd();
        float lineSpacingMultiplier = getSpacingMultiplier();
        boolean hasLineSpacing = lineSpacingExtra != DEFAULT_LINESPACING_EXTRA
                || lineSpacingMultiplier != DEFAULT_LINESPACING_MULTIPLIER;

        if (!hasLineSpacing || isLastLine && lastLineSpacingNotAdded) {
            lineBottomWithoutSpacing = lineBottom;
        }
        else {
            float extra;

            if (0 != Float.compare(lineSpacingMultiplier, DEFAULT_LINESPACING_MULTIPLIER)) {
                int lineHeight = getLineHeight(line);
                extra = lineHeight - (lineHeight - lineSpacingExtra) / lineSpacingMultiplier;
            } else {
                extra = lineSpacingExtra;
            }

            lineBottomWithoutSpacing = (int) (lineBottom - extra);
        }

        return lineBottomWithoutSpacing;
    }

    /**
     * Get the line height of a line.
     */
    public int getLineHeight(int line) {
        return getLineTop(line + 1) - getLineTop(line);
    }

}
