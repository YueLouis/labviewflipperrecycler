package vn.hcmute.labviewflipperrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> originalList;
    private List<Product> displayList;

    public ProductAdapter(List<Product> productList) {
        this.originalList = new ArrayList<>(productList);
        this.displayList = new ArrayList<>(productList);
    }

    public void filter(String query) {
        displayList.clear();
        if (query == null || query.trim().isEmpty()) {
            displayList.addAll(originalList);
        } else {
            String lower = query.toLowerCase();
            for (Product p : originalList) {
                if (p.getName().toLowerCase().contains(lower)) {
                    displayList.add(p);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = displayList.get(position);
        holder.txtName.setText(product.getName());
        holder.imgProduct.setImageResource(product.getImageResId());
    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtProductName);
        }
    }
}
