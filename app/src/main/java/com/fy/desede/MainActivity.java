package com.fy.desede;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    /*****加密密钥****/
    private EditText et_secretKey;
    /*****加密密钥长度****/
    private TextView tv_length;
    /*****加密文字****/
    private EditText et_encryptionContext;
    /****加密结果*****/
    private TextView tvEncryption;
    /*****解密结果****/
    private TextView tvDecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        et_secretKey = (EditText) findViewById(R.id.editText1);
        et_secretKey.addTextChangedListener(textWatcher);
        et_encryptionContext = (EditText) findViewById(R.id.editText2);
        tv_length = (TextView) findViewById(R.id.length);
        tvEncryption = (TextView) findViewById(R.id.tv2);
        tvDecode = (TextView) findViewById(R.id.tv3);
        findViewById(R.id.button).setOnClickListener(onClickListener);
    }

    /**
     * Button监听
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String password = et_secretKey.getText().toString();
            if (password.length() != 24) {
                Toast.makeText(MainActivity.this, "密码需要24位", Toast.LENGTH_SHORT).show();
                return;
            }

            String content = et_encryptionContext.getText().toString();
            if (content.isEmpty() || content == null) {
                Toast.makeText(MainActivity.this, "请输入密钥", Toast.LENGTH_SHORT).show();
                return;
            }

            /***********加密开始************/
            String s1 = "";
            try {
                s1 = DESede.encrypt3DES(content.getBytes(), password.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "加密失败", Toast.LENGTH_SHORT).show();
                return;
            }
            tvEncryption.setText(s1);
            /***********加密结束************/


            /***********解密开始************/
            String s2 = "";
            try {
                s2 = DESede.decrypt3DES(s1, password.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "解密失败", Toast.LENGTH_SHORT).show();
                return;
            }
            tvDecode.setText(s2);
            /***********解密结束************/

        }
    };

    TextWatcher textWatcher = new TextWatcher(){

        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override public void afterTextChanged(Editable editable) {
            //监听输入
            int textLength = et_secretKey.getText().length();
            tv_length.setText(textLength + "");
            if (textLength != 24) {
                tv_length.setTextColor(Color.rgb(255, 0, 0));
            } else {
                tv_length.setTextColor(Color.rgb(0, 0, 255));
            }
        }
    };
}
