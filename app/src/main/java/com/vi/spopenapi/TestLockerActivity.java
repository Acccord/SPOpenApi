package com.vi.spopenapi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vi.openapi.LockerSerial;
import com.vi.openapi.listener.OnLockerDataListener;

/**
 * @author Vi
 * @date 2019-11-26 16:53
 * @e-mail cfop_f2l@163.com
 */
public class TestLockerActivity extends AppCompatActivity implements View.OnClickListener,
        OnLockerDataListener {
    private EditText mEtPort;
    private EditText mEtBaud;
    private EditText mEtLockerNum;
    private EditText mEtLockerNum2;
    private EditText mEtDoorNum;
    private Button mBtnConnect;
    private Button mBtnOpenDoor;
    private Button mBtnReadStatus;
    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_locker);

        mEtPort = findViewById(R.id.et_port);
        mEtBaud = findViewById(R.id.et_baud);
        mEtLockerNum = findViewById(R.id.et_locker_number);
        mEtLockerNum2 = findViewById(R.id.et_locker_number2);
        mEtDoorNum = findViewById(R.id.et_door_number);
        mBtnConnect = findViewById(R.id.btn_connect);
        mBtnOpenDoor = findViewById(R.id.btn_open_door);
        mBtnReadStatus = findViewById(R.id.btn_read_status);
        mTvData = findViewById(R.id.tv_data);
        mBtnConnect.setOnClickListener(this);
        mBtnOpenDoor.setOnClickListener(this);
        mBtnReadStatus.setOnClickListener(this);

        mEtPort.setText("/dev/ttyS2");
        mEtBaud.setText("19200");

        //添加串口数据回调监听
        //Add serial data callback listener
        LockerSerial.instance().addDataListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除串口数据回调监听
        //remove serial data callback listener
        LockerSerial.instance().removeDataListener(this);
        LockerSerial.instance().close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect:
                connectCom();
                break;
            case R.id.btn_open_door:
                openDoor();
                break;
            case R.id.btn_read_status:
                readDoorsStatus();
                break;
        }
    }

    /**
     * 打开串口
     * Open serial port
     */
    private void connectCom() {
        mBtnConnect.setEnabled(false);
        boolean isOpen = LockerSerial.instance().isOpen();
        if (!isOpen) {
            String portStr = mEtPort.getText().toString();
            String baudStr = mEtBaud.getText().toString();
            if (TextUtils.isEmpty(portStr) || TextUtils.isEmpty(baudStr)) {
                Toast.makeText(this, getResources().getString(R.string.text_not_empty), Toast.LENGTH_SHORT).show();
                return;
            }

            int openStatus = LockerSerial.instance().open(portStr, Integer.parseInt(baudStr));
            if (openStatus == 0) {
                mBtnConnect.setText(getResources().getString(R.string.text_discon));
                Toast.makeText(this, getResources().getString(R.string.text_open_success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getResources().getString(R.string.text_open_failed), Toast.LENGTH_SHORT).show();
            }
            mBtnConnect.setEnabled(true);
        } else {
            LockerSerial.instance().close();
            mBtnConnect.setText(getResources().getString(R.string.text_con));
            mBtnConnect.setEnabled(true);
        }
    }

    /**
     * 打开箱子
     * open door
     */
    private void openDoor() {
        String lockerNum = mEtLockerNum.getText().toString();
        String doorNum = mEtDoorNum.getText().toString();
        if (TextUtils.isEmpty(lockerNum) || TextUtils.isEmpty(doorNum)) {
            Toast.makeText(this, getResources().getString(R.string.text_number_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        LockerSerial.instance().openDoor(Integer.parseInt(lockerNum), Integer.parseInt(doorNum));
    }

    /**
     * 读取箱子状态
     * read doors status
     */
    private void readDoorsStatus() {
        String lockerNum = mEtLockerNum2.getText().toString();
        if (TextUtils.isEmpty(lockerNum)) {
            Toast.makeText(this, getResources().getString(R.string.text_locker_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        LockerSerial.instance().readDoors(Integer.parseInt(lockerNum));
    }

    @Override
    public void doorStatus(String dataStr) {
        //JsonObject returnData = new JsonParser().parse(dataStr).getAsJsonObject();
        mTvData.setText(dataStr);
    }
}
