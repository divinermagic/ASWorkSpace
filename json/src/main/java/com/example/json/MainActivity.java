package com.example.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Diviner
 */
public class MainActivity extends AppCompatActivity {

    private EditText et_phone;
    private Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        buttenClick();
    }

    private void buttenClick() {
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(MainActivity.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                }

                final String path = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="+phone;
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setConnectTimeout(5000);
                            conn.setReadTimeout(5000);
                            int code = conn.getResponseCode();
                            if (code == 200){
                                //得到的json数据是一个字符串
                                InputStream is = conn.getInputStream();
                                //使用StreamUtils把输入流转化成字符串
                                //replace() 把什么什么过滤或替换成什么什么
                                String json = StremUtils.readStream(is).replace("__GetZoneResult_= ", "");
                                //使用JsonObject 把上面的字符串填到参数位置 和Map数字差不多
                                JSONObject jsonObject = new JSONObject(json);
                                final String catName = jsonObject.getString("catName");
                                final String province = jsonObject.getString("province");
                                //这个代码 判断是不是主线程 如果是主线程就直接运行,不是主线程就发消息
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "\"运营商:\"+catName+\"\\n归属地\"+province", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                showToastInAnyThread("资源没找到或服务器挂了");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showToastInAnyThread("访问网络失败");
                        }
                    }
                }.start();
            }
        });
    }

    private void initView() {
        et_phone = findViewById(R.id.et_phone);
        btn_click = findViewById(R.id.btn_click);
    }

    private void showToastInAnyThread(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
