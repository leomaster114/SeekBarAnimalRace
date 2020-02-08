package com.example.seekbaranimalrace;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtdiemso;
    CheckBox cb1,cb2,cb3;
    SeekBar sk1,sk2,sk3;
    ImageButton btn;
    int sodiem = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        disableSeekbar();
        txtdiemso.setText(sodiem+"");
        final CountDownTimer countDownTimer = new CountDownTimer(30000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random rd = new Random();
                int number = 10;
                int x = rd.nextInt(number)+1;
                int y = rd.nextInt(number)+1;
                int z = rd.nextInt(number)+1;
                //kiểm tra win
                if(sk1.getProgress()>= sk1.getMax()){
                    this.cancel();//thoat khong chay nua
                    btn.setVisibility(View.VISIBLE);
                    enableCheckbox();
                    Toast.makeText(MainActivity.this,"One Win",Toast.LENGTH_SHORT).show();
                    //kiểm tra đặt cược
                    if(cb1.isChecked()){
                        Toast.makeText(MainActivity.this,"You win - cộng 10 điểm",Toast.LENGTH_SHORT).show();
                        sodiem+=10;
                    }
                    else{
                        Toast.makeText(MainActivity.this,"You lost - trừ 5 điểm",Toast.LENGTH_SHORT).show();
                        sodiem-=5;
                    }
                    txtdiemso.setText(sodiem+"");
                }
                if(sk2.getProgress()>= sk2.getMax()){
                    this.cancel();//thoat khong chay nua
                    btn.setVisibility(View.VISIBLE);
                    enableCheckbox();
                    Toast.makeText(MainActivity.this,"Two Win",Toast.LENGTH_SHORT).show();
                    //kiểm tra đặt cược
                    if(cb2.isChecked()){
                        Toast.makeText(MainActivity.this,"You win - cộng 10 điểm",Toast.LENGTH_SHORT).show();
                        sodiem+=10;
                    }
                    else{
                        Toast.makeText(MainActivity.this,"You lost - trừ 5 điểm",Toast.LENGTH_SHORT).show();
                        sodiem-=5;

                    }
                    txtdiemso.setText(sodiem+"");
                }
                if(sk3.getProgress()>= sk3.getMax()){
                    this.cancel();//thoat khong chay nua
                    btn.setVisibility(View.VISIBLE);
                    enableCheckbox();
                    Toast.makeText(MainActivity.this,"Three Win",Toast.LENGTH_SHORT).show();
                    //kiểm tra đặt cược
                    if(cb3.isChecked()){
                        Toast.makeText(MainActivity.this,"You win - cộng 10 điểm",Toast.LENGTH_SHORT).show();
                        sodiem+=10;
                    }
                    else{
                        Toast.makeText(MainActivity.this,"You lost - trừ 5 điểm",Toast.LENGTH_SHORT).show();
                        sodiem-=5;
                    }
                    txtdiemso.setText(sodiem+"");
                }
                sk1.setProgress(sk1.getProgress()+x);
                sk2.setProgress(sk2.getProgress()+y);
                sk3.setProgress(sk3.getProgress()+z);
            }

            @Override
            public void onFinish() {

            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked()||cb2.isChecked()||cb3.isChecked()){
                    disableCheckbox();
                    btn.setVisibility(View.INVISIBLE);//cho ẩn nút xuống khi nhấn
                    sk1.setProgress(0);
                    sk2.setProgress(0);
                    sk3.setProgress(0);
                    countDownTimer.start();
                }else{
                    enableCheckbox();
                    Toast.makeText(MainActivity.this,"Vui lòng đặt cược trước khi chơi",Toast.LENGTH_LONG).show();
                }
            }
        });
        //chon doi dua
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){//nếu chọn 1 thì bỏ chọn 2 và 3
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){//nếu chọn 2 thì bỏ chọn 1 và 3
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){//nếu chọn 3 thì bỏ chọn 1 và 2
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });
    }
    private void enableCheckbox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }
    private void disableCheckbox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }
    private void disableSeekbar(){
        sk1.setEnabled(false);
        sk2.setEnabled(false);
        sk3.setEnabled(false);
    }
    private  void anhxa(){
        txtdiemso = (TextView) findViewById(R.id.txtdiemso);
        cb1 = (CheckBox) findViewById(R.id.checkbox1);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);
        cb3 = (CheckBox) findViewById(R.id.checkbox3);

        sk1 = (SeekBar) findViewById(R.id.seekbar1);
        sk2 = (SeekBar) findViewById(R.id.seekbar2);
        sk3 = (SeekBar) findViewById(R.id.seekbar3);
        btn = (ImageButton) findViewById(R.id.imagebutton);
    }
}

