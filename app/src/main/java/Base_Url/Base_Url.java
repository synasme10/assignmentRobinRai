package Base_Url;

import OnlineClothingApi.UserApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Base_Url {
    public  static final String API_URL = "http://10.0.2.2:3000/";
    private   final Retrofit retrofit;
    private  final UserApi userApi;

    public Base_Url() {
        retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        userApi = retrofit.create(UserApi.class);
    }
    public Retrofit getRetrofit(){
        return  retrofit;
    }
    public UserApi getUserApi(){
        return userApi;
    }
}
