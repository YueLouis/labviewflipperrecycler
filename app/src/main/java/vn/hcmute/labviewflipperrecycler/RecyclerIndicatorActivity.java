package vn.hcmute.labviewflipperrecycler;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator2;

public class RecyclerIndicatorActivity extends AppCompatActivity {

    private RecyclerView recyclerBanner, recyclerProducts;
    private CircleIndicator2 indicator;
    private SearchView searchView;

    private BannerAdapter bannerAdapter;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_indicator);

        // Ánh xạ view
        recyclerBanner = findViewById(R.id.recyclerBanner);
        recyclerProducts = findViewById(R.id.recyclerProducts);
        indicator = findViewById(R.id.indicator);
        searchView = findViewById(R.id.searchView);

        // Data mẫu
        productList = new ArrayList<>();
        productList.add(new Product("Shoes", R.mipmap.ic_launcher));
        productList.add(new Product("Shirt", R.mipmap.ic_launcher));
        productList.add(new Product("Watch", R.mipmap.ic_launcher));
        productList.add(new Product("Headphone", R.mipmap.ic_launcher));
        productList.add(new Product("Backpack", R.mipmap.ic_launcher));
        productList.add(new Product("Hat", R.mipmap.ic_launcher));

        // ======= RecyclerView ngang + indicator =======
        bannerAdapter = new BannerAdapter(productList);
        LinearLayoutManager bannerLayout =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerBanner.setLayoutManager(bannerLayout);
        recyclerBanner.setAdapter(bannerAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerBanner);
        indicator.attachToRecyclerView(recyclerBanner, snapHelper);
        bannerAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

        // ======= RecyclerView dọc + Search =======
        productAdapter = new ProductAdapter(productList);
        recyclerProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerProducts.setAdapter(productAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.filter(newText);
                return true;
            }
        });
    }
}
