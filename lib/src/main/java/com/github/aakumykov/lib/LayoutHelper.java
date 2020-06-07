package com.github.aakumykov.lib;

import android.os.Build;
import android.text.Layout;

public class LayoutHelper {

    private final static float DEFAULT_LINESPACING_EXTRA = 0f;
    private final static float DEFAULT_LINESPACING_MULTIPLIER = 1f;

    private LayoutHelper() {}

    /**
     * Returns the top of the Layout after removing the extra padding applied by  the Layout.
     */
    public static int getLineTopWithoutPadding(Layout layout, int line) {
        int lineTop = layout.getLineTop(line);
        if (0 == line) {
            lineTop -= layout.getTopPadding();
        }
        return lineTop;
    }

    /**
     * Returns the bottom of the Layout after removing the extra padding applied by the Layout.
     */
    public static int getLineBottomWithoutPadding(Layout layout, int line) {
        int lineBottom = getLineBottomWithoutSpacing(layout, line);
        if (line == (layout.getLineCount() - 1)) {
            lineBottom -= layout.getBottomPadding();
        }
        return lineBottom;
    }

    /**
     * Get the line bottom discarding the line spacing added.
     */
    public static int getLineBottomWithoutSpacing(Layout layout, int line) {
        int lineBottom = layout.getLineBottom(line);
        boolean lastLineSpacingNotAdded = Build.VERSION.SDK_INT >= 19;
        boolean isLastLine = line == (layout.getLineCount() - 1);

        int lineBottomWithoutSpacing;
        float lineSpacingExtra = layout.getSpacingAdd();
        float lineSpacingMultiplier = layout.getSpacingMultiplier();
        boolean hasLineSpacing = lineSpacingExtra != DEFAULT_LINESPACING_EXTRA
                || lineSpacingMultiplier != DEFAULT_LINESPACING_MULTIPLIER;

        if (!hasLineSpacing || isLastLine && lastLineSpacingNotAdded) {
            lineBottomWithoutSpacing = lineBottom;
        }
        else {
            float extra;

            if (0 != Float.compare(lineSpacingMultiplier, DEFAULT_LINESPACING_MULTIPLIER)) {
                int lineHeight = getLineHeight(layout, line);
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
    public static int getLineHeight(Layout layout, int line) {
        return layout.getLineTop(line + 1) - layout.getLineTop(line);
    }
}
