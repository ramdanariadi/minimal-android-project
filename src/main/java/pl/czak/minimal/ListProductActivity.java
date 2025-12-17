package pl.czak.minimal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import pl.czak.minimal.adapter.ProductListAdapter;
import pl.czak.minimal.dto.Product;

public class ListProductActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        ProductListAdapter adapter = new ProductListAdapter(Product.createDummy());
        RecyclerView recyclerView = findViewById(R.id.rc_product);
        recyclerView.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
