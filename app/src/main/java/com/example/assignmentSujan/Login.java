package com.example.assignmentSujan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OnlineClothingApi.UserApi;

import Base_Url.Base_Url;
import Model_API.User_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button btnlogin, btnS;
    private Retrofit retrofit;
    private UserApi userApi;
    private Base_Url api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
        api = new Base_Url();
        getConnection();
    }

    private void getConnection() {
        retrofit =api.getRetrofit();
        userApi = api.getUserApi();
    }

    private void init() {
        username = findViewById(R.id.log_username);
        password = findViewById(R.id.log_password);
        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        btnS = findViewById(R.id.button2);
        btnS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnlogin){
            if(TextUtils.isEmpty(username.getText())){
                username.setError("Please Enter Username");
                return;
            }
            if(TextUtils.isEmpty(password.getText())){
                password.setError("Please Enter Username");
                return;
            }
            AuthenticateUser(username.getText().toString(), password.getText().toString());
        }else if(v.getId() == R.id.button2){
            startActivity(new Intent(Login.this, RegisterActivity.class));
        }
    }

    private void AuthenticateUser(String username, String password) {
        User_model usermodel = new User_model(username, password);
        Call<Void> call = userApi.loginUser(usermodel);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Login.this, "Logged In Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this,Dashboard.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Login.this, "failed", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
