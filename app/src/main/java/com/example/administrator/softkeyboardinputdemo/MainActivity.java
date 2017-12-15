package com.example.administrator.softkeyboardinputdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * 页面输入框根据键盘弹出而弹起
 */
public class MainActivity extends AppCompatActivity
        implements View.OnLayoutChangeListener {

    private Button btTest1;
    private EditText etInput;
    private LinearLayout llInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btTest1 = findViewById(R.id.btTest1);
        btTest1.setFocusableInTouchMode(true);
        btTest1.setFocusable(true);
        etInput = findViewById(R.id.etInput);
        llInput = findViewById(R.id.llInput);
        btTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyBoard(v);
            }
        });
        findViewById(R.id.viewBlank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiddenKeyBoard(v);
            }
        });
        findViewById(R.id.llRoot).addOnLayoutChangeListener(this);
    }

    public static void showKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hiddenKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //设置adjustSize才生效
        Log.i("EditActivity", "onLayoutChange size:" + (oldBottom - bottom));
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > 300)) {
            Log.i("EditActivity", "键盘弹起");
            llInput.setVisibility(View.VISIBLE);
            etInput.requestFocus();
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > 300)) {
            Log.i("EditActivity", "键盘关闭");
            llInput.setVisibility(View.INVISIBLE);
        }
    }

}
