package com.fancy.validtext;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fancy.validlib.ValidText;

/**
 * Created by sunflowerseat on 2016/8/4 .
 */
public class MainActivity extends AppCompatActivity {

    TextView changeText;
    ValidText valid1;
    ValidText valid2;
    ValidText valid3;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeText = (TextView) findViewById(R.id.changeText);
        valid1 = (ValidText) findViewById(R.id.valid1);
        valid2 = (ValidText) findViewById(R.id.valid2);
        valid3 = (ValidText) findViewById(R.id.valid3);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);
        //设置下划线
        changeText.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid3.changeText();
            }
        });


    }

    public void varifyText1(View view) {
        if (valid1.equalText(edit1.getText().toString())) {
            Toast.makeText(this, "验证码1输入正确", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "验证码1输入错误", Toast.LENGTH_SHORT).show();
        }
    }

    public void varifyText2(View view) {
        if (valid2.equalText(edit2.getText().toString())) {
            Toast.makeText(this, "验证码2输入正确", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "验证码2输入错误", Toast.LENGTH_SHORT).show();
        }
    }

    public void varifyText3(View view) {
        if (valid3.equalText(edit3.getText().toString())) {
            Toast.makeText(this, "验证码3输入正确", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "验证码3输入错误", Toast.LENGTH_SHORT).show();
        }
    }

}
