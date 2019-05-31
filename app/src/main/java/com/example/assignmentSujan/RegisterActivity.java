package com.example.assignmentSujan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, firstname, lastname, password;
    private Button btnsignup;
    public static final String URL = "http://192.168.0.106:3000/";
    private Retrofit retrofit ;
    private UserApi userApi;
    private Base_Url baseUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseUrl =  new Base_Url();
        init();
        getConnectionToAPI();
    }

    private void getConnectionToAPI() {
        retrofit = baseUrl.getRetrofit();
        userApi = baseUrl.getUserApi();
    }

    private void init() {
        username = findViewById(R.id.etUsername);
        firstname = findViewById(R.id.etFirstName);
        lastname = findViewById(R.id.etLastname);
        password = findViewById(R.id.etPassword);
        btnsignup = findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(this);
    }

    private void RegisterUser(User_model usermodel){
        Call<Void> call = userApi.registerUser(usermodel);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegisterActivity.this, "User registered Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,Login.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Could not load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnsignup){
            if(TextUtils.isEmpty(username.getText())){
                username.setError("Please Enter Username");
                return;
            }
            if(TextUtils.isEmpty(firstname.getText())){
                firstname.setError("Please Enter Username");
                return;
            }
            if(TextUtils.isEmpty(lastname.getText())){
                lastname.setError("Please Enter Username");
                return;
            }
            if(TextUtils.isEmpty(password.getText())){
                password.setError("Please Enter Username");
                return;
            }
            User_model data = new User_model(username.getText().toString(), firstname.getText().toString(), lastname.getText().toString(), password.getText().toString());
            RegisterUser(data);
        }
    }
}
