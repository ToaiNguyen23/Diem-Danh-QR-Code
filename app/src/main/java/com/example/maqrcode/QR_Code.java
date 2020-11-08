package com.example.maqrcode;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QR_Code extends AppCompatActivity {
    String dstuan[] = {"Tuần 1","Tuần 2","Tuần 3","Tuần 4","Tuần 5","Tuần 6"};
    ImageView img1;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    Character a;
    Button btn_Xac_Nhan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_activity);
        btn_Xac_Nhan = findViewById(R.id.btn_xac_nhan);
        img1 = findViewById(R.id.image_1);
        spinner =findViewById(R.id.spinner);
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dstuan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String test = dstuan[position];
                a = test.charAt(5);
                Toast.makeText(getApplicationContext(),"Bạn đang chọn "+test,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_Xac_Nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(a.toString())==1)
                {
                    img1.setImageResource(R.drawable.buoi1);
                    img1.setVisibility(View.VISIBLE);
                }
                if(Integer.parseInt(a.toString())==2){
                    img1.setImageResource(R.drawable.buoi2);
                    img1.setVisibility(View.VISIBLE);
                }
                if (Integer.parseInt(a.toString())==3)
                {
                    img1.setImageResource(R.drawable.buoi3);
                    img1.setVisibility(View.VISIBLE);
                }
                if (Integer.parseInt(a.toString())==4)
                {
                    img1.setImageResource(R.drawable.buoi4);
                    img1.setVisibility(View.VISIBLE);
                }
                if (Integer.parseInt(a.toString())==5)
                {
                    img1.setImageResource(R.drawable.buoi5);
                    img1.setVisibility(View.VISIBLE);
                }
                if (Integer.parseInt(a.toString())==6)
                {
                    img1.setImageResource(R.drawable.buoi6);
                    img1.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
