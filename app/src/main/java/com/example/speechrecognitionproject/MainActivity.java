package com.example.speechrecognitionproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int SPEAK_REQUEST = 10;
    TextView txtValue;
    Button btnVoiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValue = (TextView) findViewById(R.id.txtValue);
        btnVoiceIntent = (Button) findViewById(R.id.btnVoiceIntent);

        btnVoiceIntent.setOnClickListener(MainActivity.this);

        PackageManager packageManager = this.getPackageManager();

        List<ResolveInfo> infoList = packageManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

        if(infoList.size() > 0){
            Toast.makeText(MainActivity.this, "Device supports speech recognition", Toast.LENGTH_SHORT).show();
            listenToVoice();
        } else {
            Toast.makeText(MainActivity.this, "Device does not support speech recognition", Toast.LENGTH_SHORT).show();
        }
    }

    private void listenToVoice(){
        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening!");
        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        startActivityForResult(voiceIntent, SPEAK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SPEAK_REQUEST && resultCode == RESULT_OK){
            ArrayList<String> voiceWords = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            float [] configLevels = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);

            int index = 0;

            for(String userWord : voiceWords){
                if(configLevels != null && index < configLevels.length){
                    txtValue.setText(userWord + "-" + configLevels[index]);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        listenToVoice();
    }
}