package com.example.att_20250125.Utils;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class TextToVoice implements TextToSpeech.OnInitListener {
    private TextToSpeech textToSpeech;
    private List<String> messages;

    public TextToVoice(Context context, List<String> messages) {
        textToSpeech = new TextToSpeech(context, this);
        this.messages = messages;
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            Locale locale = new Locale("pt", "br");
            int result = textToSpeech.setLanguage(locale);
            textToSpeech.setSpeechRate(1.35f);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.i("TAG", "Idioma não suportado");
            } else {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    textToSpeech.speak(messages.toString(), TextToSpeech.QUEUE_ADD, null, null);
                } else {
                    textToSpeech.speak(messages.toString(), TextToSpeech.QUEUE_ADD, null);
                }
            }
        }
    }
}
