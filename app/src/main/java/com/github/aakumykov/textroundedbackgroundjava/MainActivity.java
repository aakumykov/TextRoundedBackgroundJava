package com.github.aakumykov.textroundedbackgroundjava;

import android.os.Bundle;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.aakumykov.lib.RoundedBgTextView;

/**
 * Sample activity that uses [com.github.aakumykov.lib.RoundedBgTextView].
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoundedBgTextView roundedBgTextView = findViewById(R.id.textView);
        roundedBgTextView.setMovementMethod(LinkMovementMethod.getInstance());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Любой кот линяет. Из выпавшей шерсти можно собрать второго кота. По такой же аналогии из Java можно создать новый язык Kotlin, образованный из двух слов Kot linяет. Есть ещё неправдоподобная версия об острове в Финском заливе, которая просто смешна и не заслуживает внимания.");
        stringBuilder.append("Kotlin (Ко́тлин) — статически типизированный, объектно-ориентированный язык программирования, работающий поверх Java Virtual Machine и разрабатываемый компанией JetBrains. Также компилируется в JavaScript и в исполняемый код ряда платформ через инфраструктуру LLVM. Язык назван в честь острова Котлин в Финском заливе, на котором расположен город Кронштадт[4]. ");

        String text = stringBuilder.toString();

        String[] textParts = text.split("\\.\\s+");

        for (int i = 0; i < textParts.length; i++)
        {
            String sentence = textParts[i] + ".";
            int sentenceLength = sentence.length();

            SpannableString spannableString = new SpannableString(sentence);

            Annotation annotation = new Annotation("key", "rounded");
            spannableString.setSpan(annotation, 0, sentenceLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            final int fgColor = getResources().getColor(android.R.color.black);

            ClickableSpan clickableSpan = new TransparentClickableSpan(fgColor, new TextClickListener() {
                @Override
                public void onTextClicked(String text) {
                    Spanned spanned = (Spanned) textView.getText();

                    int start = spanned.getSpanStart(this);
                    int end = spanned.getSpanEnd(this);
                    CharSequence text = spanned.subSequence(start, end).toString();

                    Toast.makeText(widget.getContext(), text, Toast.LENGTH_SHORT).show();
                }
            });

            spannableString.setSpan(clickableSpan, 0, sentenceLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            roundedBgTextView.append(spannableString);
            roundedBgTextView.append("  ");
        }
    }

    interface TextClickListener {
        void onTextClicked(String text);
    }

    private static class TransparentClickableSpan extends ClickableSpan {

        private int fgColor;
        private TextClickListener textClickListener;

        public TransparentClickableSpan(int fgColor, TextClickListener textClickListener) {
            this.fgColor = fgColor;
            this.textClickListener = textClickListener;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if (widget instanceof TextView) {
                TextView textView = (TextView) widget;
                String text = textView.getText().toString();
                textClickListener.onTextClicked(text);
            }
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(fgColor);
        }
    }
}

