package com.example.speechrecognitionproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SPEAK_REQUEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager packageManager = this.getPackageManager();

        List<ResolveInfo> infoList = packageManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

        if(infoList.size() > 0){
            Toast.makeText(MainActivity.this, "Device supports speech recognition", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Device does not support speech recognition", Toast.LENGTH_SHORT).show();
        }
    }
}