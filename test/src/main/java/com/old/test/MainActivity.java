package com.old.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Diviner
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_s)
    Button btnS;
    @BindView(R.id.btn_f)
    Button btnF;
    @BindView(R.id.btn_fi)
    Button btnFi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Utils.init(this);
    }


    @OnClick(R.id.btn_one)
    public void onViewClicked() {
        Toast.makeText(this, "设备相关工具类DeviceUtils测试", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.btn_two, R.id.btn_s, R.id.btn_f, R.id.btn_fi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_two:
                int code = DeviceUtils.getSDKVersionCode();
                Toast.makeText(this, "当前设备系统版本号是:"+code, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_s:
                String name = DeviceUtils.getSDKVersionName();
                Toast.makeText(this, "当前设备系统版本号是:"+name, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_f:
                @SuppressLint("MissingPermission") String macAddress = DeviceUtils.getMacAddress();
                Toast.makeText(this, "当前设备MAC地址是:"+macAddress, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_fi:
                String manufacturer = DeviceUtils.getManufacturer();
                Toast.makeText(this, "当前设备厂商是:"+manufacturer, Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
    }
}
