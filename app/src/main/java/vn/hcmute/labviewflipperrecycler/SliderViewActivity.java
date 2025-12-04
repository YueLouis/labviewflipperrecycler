package vn.hcmute.labviewflipperrecycler;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderViewActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private ViewPager viewPagerRetrofit;
    private CircleIndicator indicatorRetrofit;

    private RetrofitSliderAdapter sliderAdapter;
    private List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_view);

        // --- ViewFlipper local images ---
        viewFlipper = findViewById(R.id.viewFlipper);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();

        // --- ViewPager + Indicator ---
        viewPagerRetrofit = findViewById(R.id.viewPagerRetrofit);
        indicatorRetrofit = findViewById(R.id.indicatorRetrofit);

        sliderAdapter = new RetrofitSliderAdapter(this, categoryList);
        viewPagerRetrofit.setAdapter(sliderAdapter);
        indicatorRetrofit.setViewPager(viewPagerRetrofit);

        loadCategories();
    }

    private void loadCategories() {
        FoodApi api = ApiClient.getClient().create(FoodApi.class);

        api.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call,
                                   Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList.clear();
                    categoryList.addAll(response.body());
                    sliderAdapter.notifyDataSetChanged();
                    indicatorRetrofit.invalidate();
                } else {
                    Toast.makeText(SliderViewActivity.this,
                            "Không tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(SliderViewActivity.this,
                        "Lỗi Retrofit: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
