package vn.hcmute.labviewflipperrecycler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApi {

    // Dùng luôn categories.php như project Retrofit2
    @GET("categories.php")
    Call<List<Category>> getCategories();
}
