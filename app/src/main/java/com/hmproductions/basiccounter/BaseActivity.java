package com.hmproductions.basiccounter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity
{

    Button resetButton, counterButton;
    TextView counterTV;

    private final static String COUNTER_KEY = "counter-key";

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        resetButton = (Button)findViewById(R.id.reset_button);
        counterButton = (Button)findViewById(R.id.counter_button);

        counterTV = (TextView)findViewById(R.id.counter_textView);

        setupCounter();
        AddOneButtonClickListener();
        ResetButtonClickListener();
    }

    private void setupCounter()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        counter = preferences.getInt(COUNTER_KEY, 0);

        counterTV.setText(String.valueOf(counter));
    }

    private void saveCounter()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(COUNTER_KEY, counter);

        editor.apply();
    }

    private void AddOneButtonClickListener()
    {
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                counterTV.setText(String.valueOf(++counter));
            }
        });
    }

    private void ResetButtonClickListener()
    {
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter=0;
                counterTV.setText(String.valueOf(counter));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCounter();
    }
}
