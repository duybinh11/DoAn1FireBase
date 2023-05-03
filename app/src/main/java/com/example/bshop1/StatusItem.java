package com.example.bshop1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import Model.ItemCart;

public class StatusItem extends AppCompatActivity {
    FirebaseDatabase database;
    ItemCart itemCart;
    ImageView img;
    private FirebaseAuth mAuth;
    TextView tvName,tvSl,tvMoney,tvDate,tvStatus;
    Button btnHuy,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_item);
        anhXa();
        setData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemCart.getTrangThai().equals("Chuẩn Bị Hàng")){
                    DatabaseReference mData = database.getReference("list_cart/"+mAuth.getUid());
                    Toast.makeText(StatusItem.this, itemCart.getId(), Toast.LENGTH_SHORT).show();
                    mData.child(itemCart.getId()).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(StatusItem.this, "Đã Xóa", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(StatusItem.this, "K Thể Hủy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void anhXa(){
        itemCart = (ItemCart) getIntent().getSerializableExtra("itemCart");
        database = FirebaseDatabase.getInstance();
        img = findViewById(R.id.img6);
        tvDate = findViewById(R.id.tvDate6);
        tvStatus = findViewById(R.id.tvStatus6);
        tvName = findViewById(R.id.tvName6);
        tvSl = findViewById(R.id.tvSl6);
        tvMoney = findViewById(R.id.tvMoney6);
        btnBack = findViewById(R.id.btnBack6);
        mAuth = FirebaseAuth.getInstance();
        btnHuy = findViewById(R.id.btnHuy6);
    }
    public void setData(){
        Glide.with(this)
                .load(itemCart.getItemNew().getImg())
                .into(img);
        tvName.setText(itemCart.getItemNew().getName());
        tvStatus.setText(itemCart.getTrangThai());
        tvSl.setText(String.valueOf(itemCart.getSLM()));
        tvMoney.setText(String.valueOf(itemCart.getSLM()*itemCart.getItemNew().getCost())+"k");
        tvDate.setText(itemCart.getDate());
    }



}