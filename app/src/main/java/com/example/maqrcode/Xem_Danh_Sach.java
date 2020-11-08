package com.example.maqrcode;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
public class Xem_Danh_Sach extends AppCompatActivity {
    Button btn_xuat;
    SinhVienAdapter adapter = null;
    ListView list;
    private List<SinhVien> ds_Sinh_Vien = new ArrayList<SinhVien>();
    private DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mSinhVienReference;
    private ChildEventListener mSinhVienListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_activity);
        list = findViewById(R.id.list);
        adapter = new SinhVienAdapter();
        list.setAdapter(adapter);
        btn_xuat = findViewById(R.id.btn_xuat);
        mSinhVienReference = database.getReference().child("LT di Dong");
        mSinhVienReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ds_Sinh_Vien.clear();
                for (DataSnapshot svSnapshot : dataSnapshot.getChildren() ) {
                    SinhVien sv = svSnapshot.getValue(SinhVien.class);
                    ds_Sinh_Vien.add(sv);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    deleteItemToSheet();
                    Thread.sleep(500);
                    add_tieu_de();
                    Thread.sleep(500);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                for (SinhVien a:ds_Sinh_Vien
                ) {
                    String ten = a.getHoten();
                    String ma = a.getMssv();
                    String buoi_1 = a.getBuoi1();
                    String buoi_2 = a.getBuoi2();
                    String buoi_3 = a.getBuoi3();
                    String buoi_4 = a.getBuoi4();
                    String buoi_5 = a.getBuoi5();
                    String buoi_6 = a.getBuoi6();
                    addItemToSheet(ten,ma,buoi_1,buoi_2,buoi_3,buoi_4,buoi_5,buoi_6);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(Xem_Danh_Sach.this,Xuat_excel.class);
                startActivity(intent);
            }
        });
    }
    // Xoá mọi thứ trong excel để tạo ra trang tính mới
    private void deleteItemToSheet() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycby07MdKx1L57jX7P1YAc2YrWkXTOjGXkbm-kMewqjCYL5ddxzU/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","xoa");
                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
    // Tạo ra dòng  đầu tiên trong trang tính với các trạng thái MSSV,ho_ten,các buổi học
    private void add_tieu_de() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycby07MdKx1L57jX7P1YAc2YrWkXTOjGXkbm-kMewqjCYL5ddxzU/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","tieu_de");
                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }
    // them tung dong vao sheet
    private void addItemToSheet(final String ten, final String ma, final String b1,final String b2,final String b3, final String b4, final String b5, final String b6) {

        final ProgressDialog loading = ProgressDialog.show(this,"Đang xuất danh sách","Vui lòng đợi");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycby07MdKx1L57jX7P1YAc2YrWkXTOjGXkbm-kMewqjCYL5ddxzU/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(Xem_Danh_Sach.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("Name",ten);
                parmas.put("MSSV",ma);
                parmas.put("tuan_1",b1);
                parmas.put("tuan_2",b2);
                parmas.put("tuan_3",b3);
                parmas.put("tuan_4",b4);
                parmas.put("tuan_5",b5);
                parmas.put("tuan_6",b6);
                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }
    class SinhVienAdapter extends ArrayAdapter<SinhVien>{
        public SinhVienAdapter(Context context,int textViewReSourceId)
        {
            super(context,textViewReSourceId);
        }
        public SinhVienAdapter()
        {
            super(Xem_Danh_Sach.this,android.R.layout.simple_list_item_1,ds_Sinh_Vien);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_activity, null);

            }
            SinhVien a = ds_Sinh_Vien.get(position);
            String tmp = a.hoten +"-" +a.mssv;
            String buoi_1 = "Buổi 1:"+a.buoi1;
            String buoi_2 = "Buổi 2:"+a.buoi2;
            String buoi_3 = "Buổi 3:"+a.buoi3;
            String buoi_4 = "Buổi 4:"+a.buoi4;
            String buoi_5 = "Buổi 5:"+a.buoi5;
            String buoi_6 = "Buổi 6:"+a.buoi6;
            ((TextView)row.findViewById(R.id.txt_Ten_MSSV)).setText(tmp);
            ((TextView)row.findViewById(R.id.txt_buoi1)).setText(buoi_1);
            ((TextView)row.findViewById(R.id.txt_buoi2)).setText(buoi_2);
            ((TextView)row.findViewById(R.id.txt_buoi3)).setText(buoi_3);
            ((TextView)row.findViewById(R.id.txt_buoi4)).setText(buoi_4);
            ((TextView)row.findViewById(R.id.txt_buoi5)).setText(buoi_5);
            ((TextView)row.findViewById(R.id.txt_buoi6)).setText(buoi_6);
            return row;
        }
    }
}
