package com.old.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Diviner
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_one)
    Button btnOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_one)
    public void onViewClicked() {
        Toast.makeText(this, "hehe", Toast.LENGTH_SHORT).show();
    }
}
