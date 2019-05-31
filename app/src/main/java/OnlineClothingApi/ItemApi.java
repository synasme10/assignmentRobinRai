package OnlineClothingApi;

import Model_API.ImageResponse;
import Model_API.Item_Model;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ItemApi {



    @POST("items")
    Call<Void> insertItem(@Body Item_Model item_model);

    @Multipart
    @POST("image")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("items")
    Call<List<Item_Model>> getAllItems();
}
