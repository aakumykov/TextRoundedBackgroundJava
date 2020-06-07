package com.github.aakumykov.textroundedbackgroundjava;

import android.os.Bundle;
import android.text.Annotation;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;

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


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Любой кот линяет. Из выпавшей шерсти можно собрать второго кота. По такой же аналогии из Java можно создать новый язык Kotlin, образованный из двух слов Kot linяет. Есть ещё неправдоподобная версия об острове в Финском заливе, которая просто смешна и не заслуживает внимания.");
        stringBuilder.append("Kotlin (Ко́тлин) — статически типизированный, объектно-ориентированный язык программирования, работающий поверх Java Virtual Machine и разрабатываемый компанией JetBrains. Также компилируется в JavaScript и в исполняемый код ряда платформ через инфраструктуру LLVM. Язык назван в честь острова Котлин в Финском заливе, на котором расположен город Кронштадт[4]. ");

        String text = stringBuilder.toString();

        String[] textParts = text.split("\\.\\s+");

        for (int i=0; i<textParts.length; i++) {
            String sentence = textParts[i] + ".";

            SpannableString spannableString = new SpannableString(sentence);
            spannableString.setSpan(new Annotation("key", "rounded"), 0, sentence.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            roundedBgTextView.append(spannableString);
            roundedBgTextView.append("  ");
        }

    }
}
