package com.example.maqrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangKyActivity extends AppCompatActivity {

    Button btndangky,btnquaylai;
    EditText txtgmail,txtpass,txthoten,txtmssv;
    String keyid;
    private FirebaseAuth mAuth;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky_new_layout);

        anhxa();
        mAuth = FirebaseAuth.getInstance();
        mData =  FirebaseDatabase.getInstance().getReference();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });

        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void anhxa(){
        btndangky = (Button)findViewById(R.id.btndangky);
        txtgmail = (EditText) findViewById(R.id.txtgmail);
        txtpass = (EditText) findViewById(R.id.txtpass);
        txthoten = (EditText) findViewById(R.id.txthoten);
        txtmssv = (EditText) findViewById(R.id.txtmssv);
        btnquaylai = (Button) findViewById(R.id.btnquaylai);
    }
    private void DangKy(){
        String email = txtgmail.getText().toString();
        String password = txtpass.getText().toString();
        final SinhVien sv = new SinhVien(txthoten.getText().toString(),txtmssv.getText().toString(),"0","0","0","0","0","0");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //dung
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công" ,Toast.LENGTH_SHORT).show();
                            keyid = mAuth.getUid().toString();
                            mData.child("LT di Dong").child(keyid).setValue(sv);
                            Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                            Bundle data = new Bundle();
                            data.putString("userId", keyid);
                            intent.putExtra("DATA",data);
                            startActivity(intent);
                        }
                        else {
                            //loi
                            Toast.makeText(getApplicationContext(), "Lỗi" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}