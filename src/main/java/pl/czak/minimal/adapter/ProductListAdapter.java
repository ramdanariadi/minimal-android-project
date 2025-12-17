package pl.czak.minimal.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.czak.minimal.R;
import pl.czak.minimal.dto.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private final List<Product> products;

    public ProductListAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.product_list_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = this.products.get(i);
        productViewHolder.productName.setText(product.getName());
        productViewHolder.productPrice.setText(String.valueOf(product.getPrice()));
        productViewHolder.productImage.setImageURI(Uri.parse(product.getImg()));
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productPrice;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            this.productName = itemView.findViewById(R.id.product_name);
            this.productPrice = itemView.findViewById(R.id.product_price);
            this.productImage = itemView.findViewById(R.id.prod_item_img);

        }
    }
}
