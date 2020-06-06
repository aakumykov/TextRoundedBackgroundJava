package com.github.aakumykov.lib;

import android.graphics.Canvas;
import android.text.Layout;

public abstract class TextRoundedBgRenderer {

    public abstract void draw(Canvas canvas, Layout layout, int startLine, int endLine, int startOffset, int endOffset);

}
