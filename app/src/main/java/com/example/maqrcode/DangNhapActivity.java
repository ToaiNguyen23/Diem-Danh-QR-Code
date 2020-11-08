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

public class DangNhapActivity extends AppCompatActivity {

    Button btndangky,btndangnhap;
    EditText txtgmail,txtpass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btndangky = (Button) findViewById(R.id.btndangky);
        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        txtgmail = (EditText) findViewById(R.id.txtgmail);
        txtpass = (EditText) findViewById(R.id.txtpass);

        mAuth = FirebaseAuth.getInstance();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdangnhap = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intentdangnhap);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });

    }
    private void DangNhap(){
        final String email = txtgmail.getText().toString();
        String password = txtpass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công!" ,Toast.LENGTH_SHORT).show();
                            Intent intent;
                            String userId = mAuth.getUid().toString();
                            if (userId.equals("BDw9cEsBCjVyzdaSvPS94VAieGE3")) {
                                intent = new Intent(DangNhapActivity.this, GVActivity.class);

                            } else {
                                intent = new Intent(DangNhapActivity.this, MainActivity.class);
                            }
                            Bundle data = new Bundle();
                            data.putString("userId", userId);
                            intent.putExtra("DATA",data);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng. Mời nhập lại!" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}