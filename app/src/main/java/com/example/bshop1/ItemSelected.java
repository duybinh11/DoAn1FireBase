package com.example.bshop1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import Model.ItemNew;

public class ItemSelected extends AppCompatActivity {
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    ItemNew itemNew;
    ImageView img1;
    TextView tvSL,tvSold,tvCost,tvName,tvDate;
    EditText edtSLM;
    Button btnBuy,btnCannel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        anhXa();
        setData();
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }
    public void anhXa(){
        mAuth = FirebaseAuth.getInstance();
        itemNew = (ItemNew) getIntent().getSerializableExtra("itemNew");
        database = FirebaseDatabase.getInstance();
        img1 = findViewById(R.id.img1);
        tvSL = findViewById(R.id.tvSL);
        tvCost = findViewById(R.id.tvCost);
        tvSold = findViewById(R.id.tvSold);
        edtSLM = findViewById(R.id.edtSLM);
        tvDate = findViewById(R.id.tvDate);
        btnBuy = findViewById(R.id.btnMua);
        tvName = findViewById(R.id.tvName);
        btnCannel = findViewById(R.id.btnCannel);
    }
    public void setData(){
        Glide.with(this)
                .load(itemNew.getImg())
                .into(img1);
        tvName.setText(itemNew.getName());
        tvSold.setText(String.valueOf(itemNew.getSold()));
        tvCost.setText(String.valueOf(itemNew.getCost())+"k");
        tvDate.setText(itemNew.getDate());
        tvSL.setText(String.valueOf(itemNew.getSL()));
    }
    public void dialog(){
        AlertDialog.Builder dia = new AlertDialog.Builder(ItemSelected.this);
        int slm = Integer.valueOf(edtSLM.getText().toString());
        int sold = itemNew.getSold()+slm;
        int SL = itemNew.getSL()-slm;
        dia.setMessage("Số tiền phải thanh toán là : "+itemNew.getCost()*slm +"k");
        dia.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ItemSelected.this, "có", Toast.LENGTH_SHORT).show();
                DatabaseReference mData = database.getReference("list_item/"+itemNew.getId()).child("sold");
                DatabaseReference mData1 = database.getReference("list_item/"+itemNew.getId()).child("SL");
                mData.setValue(sold);
                mData1.setValue(SL);
                String idUser = mAuth.getUid();
                addCart(idUser,slm);
            }
        });
        dia.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dia.create().show();
    }
    public void addCart(String id,int slm){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        DatabaseReference mData = database.getReference("list_cart/"+id).push();
        String key = mData.getKey();
        mData.child("SLM").setValue(slm);
        mData.child("date").setValue(currentDateandTime);
        mData.child("itemNew").setValue(itemNew);
        mData.child("trangThai").setValue("Chuẩn Bị Hàng");
    }
}