package com.example.casio.fx;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casio.R;

import androidx.appcompat.app.AppCompatActivity;


public class FxActivity extends AppCompatActivity {
    int key;
    TextView tvTitle, tvResult,tvX;
    Button btn_timX;
    EditText etA, etB,etC;
    LinearLayout llResult,ll_typeC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fx);
        Intent intent = getIntent();
        key = intent.getIntExtra("key",0);
        tvTitle = findViewById(R.id.tv_Title);
        tvResult = findViewById(R.id.tv_result);
        tvX = findViewById(R.id.tv_X);
        btn_timX = findViewById(R.id.btn_timX);
        etA = findViewById(R.id.et_A);
        etB = findViewById(R.id.et_B);
        etC = findViewById(R.id.et_C);
        llResult = findViewById(R.id.ll_result);
        ll_typeC = findViewById(R.id.ll_typeC);
        ll_typeC.setVisibility(key != 0 ? View.VISIBLE : View.INVISIBLE);
        tvTitle.setText(key == 0 ? "Giải phương trình bậc 1 ax + b = 0" : "Giải phương trình bậc 2 ax^2 + bx + c = 0");

        btn_timX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a", ""+ key);
                if (key == 0) {
                    if (etA.getText().toString().trim().length() >0 && etB.getText().toString().trim().length() >0) {
                        ptbac1(Double.parseDouble(etA.getText().toString()), Double.parseDouble(etB.getText().toString()));
                    }
                    else {
                        Toast.makeText(getBaseContext(),"Vui lòng nhập a, b", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (etA.getText().toString().trim().length() >0 && etB.getText().toString().trim().length() >0 &&etC.getText().toString().trim().length() > 0) {
                        ptbac2(Double.parseDouble(etA.getText().toString()), Double.parseDouble(etB.getText().toString()), Double.parseDouble(etC.getText().toString()));
                    } else {
                        Toast.makeText(getBaseContext(),"Vui lòng nhập a, b, c", Toast.LENGTH_SHORT).show();
                    }
                }
                llResult.setVisibility(View.VISIBLE);
            }
        });
    }

    void ptbac1(double a, double b) {
        tvResult.setText("");
        tvX.setText("");
        if(a == 0) {
            tvResult.setText(b==0 ? "Phương trình có vô số nghiệm" : "Phương trình có vô nghiệm");
        }
        else {
            double x = -b/a;
            tvResult.setText("Phương trình có nghiệm là:");
            tvX.setText("x= " +x);
        }
    }

    void ptbac2(double a, double b, double c) {
        tvResult.setText("");
        tvX.setText("");
        if(a==0) {
            if(b==0) {
                tvResult.setText("Phương trình có vô nghiệm");
            }
            else {
                tvResult.setText("Phương trình có 1 nghiệm duy nhất");
                double x = -c/b;
                tvX.setText("x= " +x);
            }
        } else {
            double delta = b*b - 4*a*c;
            double x1, x2;
            if(delta > 0) {
                x1 = (-b + Math.sqrt(delta)) / (2*a*c);
                x2 = (-b - Math.sqrt(delta)) / (2*a*c);
                tvResult.setText("Phương trình có 2 nghiệm là");
                tvX.setText("x1= " +x1 +"          x2= "+x2);
            } else if(delta == 0) {
                x1 = (-b / (2 * a));
                tvResult.setText("Phương trình có nghiệm kép là");
                tvX.setText("x1=x2 = " +x1);
            } else {
                tvResult.setText("Phương trình có vô nghiệm");
            }
        }
    }
}