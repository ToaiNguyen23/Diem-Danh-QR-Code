package com.example.maqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GVActivity extends AppCompatActivity {
    Button btn_QRcode,btn_XuatList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gvactivity);
        btn_QRcode = findViewById(R.id.btnLayHinh);
        btn_XuatList = findViewById(R.id.btnDanhSach);
        btn_QRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GVActivity.this,QR_Code.class);
                startActivity(intent);
            }
        });
        btn_XuatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GVActivity.this,Xem_Danh_Sach.class);
                startActivity(intent);
            }
        });
    }
}
