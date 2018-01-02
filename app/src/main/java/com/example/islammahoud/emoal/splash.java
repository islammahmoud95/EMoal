package com.example.islammahoud.emoal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.islammahoud.emoal.parser.Requester;


public class splash extends AppCompatActivity {

    private final static int  SPLASH_DISPLAY_LENGTH =3000;
    private ProgressBar progressBar;
    private Context context;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView view = (ImageView) findViewById(R.id.splash); //Initialize ImageView via FindViewById or programatically
        progressBar=(ProgressBar) findViewById(R.id.prog);
        textView=(TextView)findViewById(R.id.text1);
        //textView.setText( "قرر مكانك من البيت" + "\nE-mool.com");
        progressBar.isShown();
        context=getApplicationContext();
        new Requester().Requestexcute(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this, MainActivity.class);
                splash.this.startActivity(intent);
                splash.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
