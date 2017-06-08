package com.example.android.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        TextView textView = (TextView) findViewById(R.id.title_txt);
        textView.setText(title);
    }
}
