package com.example.assignmentSujan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import Adapter.ItemAdapter;
import OnlineClothingApi.ItemApi;

import Base_Url.Base_Url;
import Model_API.Item_Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recycle;
    private List<Item_Model> list = new ArrayList<>();
    private Button btnAddItem;
    private Retrofit retrofit;
    private ItemApi item_api;
    Base_Url baseUrl;
    private ItemAdapter adap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashbaord);
        recycle = findViewById(R.id.recycleView);
        btnAddItem = findViewById(R.id.btnaddItem);
        btnAddItem.setOnClickListener(this);
        baseUrl = new Base_Url();
        retrofit= baseUrl.getRetrofit();
        item_api = retrofit.create(ItemApi.class);
        getData();
    }

    private void getData(){
        Call<List<Item_Model>> data = item_api.getAllItems();
        data.enqueue(new Callback<List<Item_Model>>() {
            @Override
            public void onResponse(Call<List<Item_Model>> call, Response<List<Item_Model>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    adap = new ItemAdapter(Dashboard.this, list);
                    recycle.setAdapter(adap);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycle.setLayoutManager(mLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<List<Item_Model>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnaddItem){
            startActivity(new Intent(Dashboard.this,Item.class));
        }
    }
}
