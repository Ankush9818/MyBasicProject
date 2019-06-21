package com.example.hugotech;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    private VideoView video;
    private EditText username;
    private EditText password;
    private Intent nextIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindActivity();
    }

    private void bindActivity() {
        video = findViewById(R.id.asteriodBelt);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.asteriod);

        video.setVideoURI(uri);
        video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ",null );
                video.stopPlayback();
    }

    @Override
    protected void onPostResume() {

        super.onPostResume();
        Log.e(TAG, "onPostResume: ",null );
        video.start();
    }


    public void login(View view) {
        String str1 = username.getText().toString();
        String str2 = password.getText().toString();

        if (str1.length() == 0) {
            Toast.makeText(this, "Enter Username or Email Id", Toast.LENGTH_LONG).show();
        } else if (str2.length() <5) {
            if (str2.length() == 0) {
                Toast.makeText(this, "Please, Enter Password", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
            }
        }

        else {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

            nextIntent = new Intent(this, nextPage.class);
            startActivity(nextIntent);
        }

    }

}