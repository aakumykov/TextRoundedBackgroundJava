package com.github.aakumykov.lib;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Annotation;
import android.text.Layout;
import android.text.Spanned;


public class TextRoundedBgHelper {

    private TextRoundedBgRenderer singleLineRenderer;
    private TextRoundedBgRenderer multiLineRenderer;

    private int horizontalPadding;
    private int verticalPadding;
    private Drawable drawable;
    private Drawable drawableLeft;
    private Drawable drawableMid;
    private Drawable drawableRight;

    /**
     * Helper class to draw multi-line rounded background to certain parts of a text. The start/end
     * positions of the backgrounds are annotated with [android.text.Annotation] class. Each annotation
     * should have the annotation key set to **rounded**.
     *
     * i.e.:
     * ```
     *    <!--without the quotes at the begining and end Android strips the whitespace and also starts
     *        the annotation at the wrong position-->
     *    <string name="ltr">"this is <annotation key="rounded">a regular</annotation> paragraph."</string>
     * ```
     *
     * **Note:** BiDi text is not supported.
     *
     * @param horizontalPadding the padding to be applied to left & right of the background
     * @param verticalPadding the padding to be applied to top & bottom of the background
     * @param drawable the drawable used to draw the background
     * @param drawableLeft the drawable used to draw left edge of the background
     * @param drawableMid the drawable used to draw for whole line
     * @param drawableRight the drawable used to draw right edge of the background
     */
    public TextRoundedBgHelper(
            int horizontalPadding,
            int verticalPadding,
            Drawable drawable,
            Drawable drawableLeft,
            Drawable drawableMid,
            Drawable drawableRight
    ) {
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.drawable = drawable;
        this.drawableLeft = drawableLeft;
        this.drawableMid = drawableMid;
        this.drawableRight = drawableRight;
    }

    /**
     * Call this function during onDraw of another widget such as TextView.
     *
     * @param canvas Canvas to draw onto
     * @param text
     * @param layout Layout that contains the text
     */
    public void draw(Canvas canvas, Spanned text, Layout layout) {
        // ideally the calculations here should be cached since they are not cheap. However, proper
        // invalidation of the cache is required whenever anything related to text has changed.

        Annotation[] annotationSpans = text.getSpans(0, text.length(), Annotation.class);

        for (Annotation span : annotationSpans) {

            String value = span.getValue();

            if ("rounded".equals(value)) {
                int spanStart = text.getSpanStart(span);
                int spanEnd = text.getSpanEnd(span);
                int startLine = layout.getLineForOffset(spanStart);
                int endLine = layout.getLineForOffset(spanEnd);

                // start can be on the left or on the right depending on the language direction.
                int startOffset = (int) (layout.getPrimaryHorizontal(spanStart)
                        + -1 * layout.getParagraphDirection(startLine) * horizontalPadding);

                // end can be on the left or on the right depending on the language direction.
                int endOffset = (int) (layout.getPrimaryHorizontal(spanEnd)
                        + layout.getParagraphDirection(endLine) * horizontalPadding);

                TextRoundedBgRenderer renderer = (startLine == endLine) ?
                        singleLineRenderer : multiLineRenderer;

                renderer.draw(canvas, layout, startLine, endLine, startOffset, endOffset);
            }
        }
    }
}
