package com.vi.spopenapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vi.openapi.LiftSerial;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnTestLocker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnTestLocker = findViewById(R.id.btn_locker);
        mBtnTestLocker.setOnClickListener(this);
        LiftSerial.instance().doorCmd(int door, int light, int add);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_locker:
                startActivity(new Intent(this, TestLockerActivity.class));
                break;
        }
    }
}
