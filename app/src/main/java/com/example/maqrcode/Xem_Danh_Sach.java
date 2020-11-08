package com.example.maqrcode;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;

public class Xem_Danh_Sach extends AppCompatActivity {

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
    }
    private void getData()
    {
        SinhVien a = new SinhVien();
        a.hoten = "Phan Nguyen Manh Hoang";
        a.mssv = "171770058";
        a.buoi1= "1";
        a.buoi2="1";
        a.buoi3="0";
        a.buoi4="1";
        a.buoi5="0";
        a.buoi6="1";
        ds_Sinh_Vien.add(a);
        SinhVien b = new SinhVien();
        b.hoten = "Toai";
        b.mssv = "171770058";
        b.buoi1= "0";
        b.buoi2="0";
        b.buoi3="0";
        b.buoi4="0";
        b.buoi5="0";
        b.buoi6="0";
        ds_Sinh_Vien.add(b);

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
