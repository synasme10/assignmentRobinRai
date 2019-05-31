package OnlineClothingApi;

import Model_API.User_model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("signup")
    Call<Void> registerUser(@Body User_model usermodel);

    @POST("login")
    Call<Void> loginUser(@Body User_model usermodel);
}
