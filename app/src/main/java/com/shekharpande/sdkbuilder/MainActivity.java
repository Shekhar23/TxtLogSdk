package com.shekharpande.sdkbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shekharpande.txtlog.TxtLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAddErrorLine = findViewById(R.id.btnAddErrorLine);
        btnAddErrorLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TxtLog.sdkInitialize(getApplication(),true);
                TxtLog.write("Data added ");
            }
        });


    }
}