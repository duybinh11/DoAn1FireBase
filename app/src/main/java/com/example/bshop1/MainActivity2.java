package com.example.bshop1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fragment.cart;
import fragment.home;
import fragment.my;

public class MainActivity2 extends AppCompatActivity {
    BottomNavigationView btnv ;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        anhXa();


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,new home()).commit();
        btnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.menuHome:{
                        fragmentTransaction.replace(R.id.frame,new home()).commit();
                        break;
                    }
                    case R.id.menuCart:{
                        fragmentTransaction.replace(R.id.frame,new cart()).commit();
                        break;
                    }
                    case R.id.menuMy:{
                        fragmentTransaction.replace(R.id.frame,new my()).commit();
                        break;
                    }
                }
                return true;
            }
        });
    }
    public void anhXa() {
        mAuth = FirebaseAuth.getInstance();
        btnv = findViewById(R.id.btnv);
    }
}