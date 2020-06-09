package com.github.aakumykov.textroundedbackgroundjava;

import android.os.Bundle;
import android.text.Annotation;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
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

        /*SpannableString spannableString = new SpannableString("My spantastic text");
        Annotation annotation = new Annotation("key", "rounded");
        spannableString.setSpan(annotation, 3, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        roundedBgTextView.setText(spannableString);*/

        /*StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Любой кот линяет. Из выпавшей шерсти можно собрать второго кота. По такой же аналогии из Java можно создать новый язык Kotlin, образованный из двух слов Kot linяет. Есть ещё неправдоподобная версия об острове в Финском заливе, которая просто смешна и не заслуживает внимания.");
        stringBuilder.append("Kotlin (Ко́тлин) — статически типизированный, объектно-ориентированный язык программирования, работающий поверх Java Virtual Machine и разрабатываемый компанией JetBrains. Также компилируется в JavaScript и в исполняемый код ряда платформ через инфраструктуру LLVM. Язык назван в честь острова Котлин в Финском заливе, на котором расположен город Кронштадт[4]. ");

        String text = stringBuilder.toString();

        String[] textParts = text.split("\\.\\s+");

        for (int i=0; i<textParts.length; i++) {
            String sentence = textParts[i] + ".";

            int sentenceLength = sentence.length();

            Annotation annotation = new Annotation("key", "rounded");

            SpannableString spannableString = new SpannableString(sentence);
//            spannableString.setSpan(annotation, 0, sentenceLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            final int fgColor = getResources().getColor(android.R.color.black);

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        Spanned spanned = (Spanned) textView.getText();

                        int start = spanned.getSpanStart(this);
                        int end = spanned.getSpanEnd(this);
                        CharSequence text = spanned.subSequence(start, end).toString();

                        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                    ds.setColor(fgColor);
                }
            };


            spannableString.setSpan(clickableSpan, 0, sentenceLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//            roundedBgTextView.append(spannableString);
//            roundedBgTextView.append("  ");*/

            String text = "Язык назван в честь острова Котлин в Финском заливе, на котором расположен город Кронштадт[4].";

            SpannableString spannableString = new SpannableString(text);

            final int fgColor = getResources().getColor(android.R.color.black);

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        Spanned spanned = (Spanned) textView.getText();

                        int start = spanned.getSpanStart(this);
                        int end = spanned.getSpanEnd(this);
                        CharSequence text = spanned.subSequence(start, end).toString();

                        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                    ds.setColor(fgColor);
                }
            };

            Annotation annotation = new Annotation("mode", "rounded");

            spannableString.setSpan(annotation, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannableString.setSpan(clickableSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            roundedBgTextView.setTextColor(fgColor);

            roundedBgTextView.setText(spannableString);
        }
}

