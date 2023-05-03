package com.example.bshop1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Model.User;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnSignUp;
    static final int CODE = 10;
    EditText edtUser,edtPass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        btnLogin.setOnClickListener(AC_BTN);
        btnSignUp.setOnClickListener(AC_BTN);
    }
    public void anhXa(){
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        mAuth = FirebaseAuth.getInstance();
    }

    View.OnClickListener AC_BTN = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnLogin:{
                    String email = edtUser.getText().toString();
                    String pass = edtPass.getText().toString();
                    if(email.equals("db") && pass.equals("zxc")){
                        Intent intent = new Intent();
                        startActivity(intent);
                    }else{
                        login(email,pass);
                    }
                    break;
                }
                case R.id.btnSignUp:{
                    Intent intent = new Intent(MainActivity.this,SignUp.class);
                    startActivityForResult(intent,CODE);
                    break;
                }
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE && resultCode == RESULT_OK ){
            User updatedUser = (User) data.getSerializableExtra("user");
            edtUser.setText(updatedUser.getEmail());
            edtPass.setText(updatedUser.getPass());
        }
    }
    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}