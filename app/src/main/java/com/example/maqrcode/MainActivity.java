package com.example.maqrcode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bntscan;
    TextView txtmonhoc, txtngayhoc;
    int count = 0;
    String arr[] = {"04bde20d0a8f2517da06b829992dbb64","2eaeef59a7497cdd10c6541e5cee7f6a","dd12bf516197c1b236adcdfdbdbc5301","6ba6a285da58dce2de288f585658cb39","3bae37e8833481b67be3a183a474b913","0738da35eaa9774199439b61ea6577a4"};
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String languageToLoad  = "vi"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_main);

        bntscan = (Button) findViewById(R.id.btnscan);
        txtmonhoc = (TextView) findViewById(R.id.txtmonhoc);
        txtngayhoc = (TextView) findViewById(R.id.txtngayhoc);

        bntscan.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning Code");
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String  code = result.getContents();
            if (code == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                for (int i=0; i<6; i++ )
                    if (code.equals(arr[i]) == true) {
                        Intent callerIntent = getIntent();
                        Bundle dataFromIntent = callerIntent.getBundleExtra("DATA");
                        String userId = dataFromIntent.getString("userId");
                        String day = "buoi" + (i+1);
                        mDatabase.child("LT di Dong").child(userId).child(day).setValue("1");
                        Toast.makeText(this, "Điểm danh thành công buổi thứ: " + (i+1),Toast.LENGTH_LONG).show();
                        break;
                    }
                try {
                    JSONObject jsonObject = new JSONObject(result.getContents());
                    txtmonhoc.setText(jsonObject.getString("name"));
                    txtngayhoc.setText(jsonObject.getString("ngay"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}