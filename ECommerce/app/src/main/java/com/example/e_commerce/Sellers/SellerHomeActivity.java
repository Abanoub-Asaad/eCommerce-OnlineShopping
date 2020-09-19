package com.example.e_commerce.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e_commerce.Admin.AdminCheckNewProductsActivity;
import com.example.e_commerce.MainActivity;
import com.example.e_commerce.Model.Products;
import com.example.e_commerce.R;
import com.example.e_commerce.ViewHolder.ItemViewHolderSeller;
import com.example.e_commerce.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SellerHomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        recyclerView = findViewById(R.id.seller_unverified_products_checkList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        unverifiedProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add:
                    {
                        Intent intentCategory = new Intent(SellerHomeActivity.this, SellerProductCategoryActivity.class);
                        startActivity(intentCategory);

                        overridePendingTransition(0, 0);
                    }
                        break;
                    case R.id.logout:
                    {
                        final FirebaseAuth auth= FirebaseAuth.getInstance();
                        auth.signOut();

                        Intent intentMain = new Intent(SellerHomeActivity.this, MainActivity.class);
                        intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentMain);
                        finish();
                        overridePendingTransition(0, 0);
                    }
                        break;
                    case R.id.home:
                        break;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(unverifiedProductsRef.orderByChild("seller_id").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()), Products.class)
                        .build();

        FirebaseRecyclerAdapter<Products, ItemViewHolderSeller> adapter =
                new FirebaseRecyclerAdapter<Products, ItemViewHolderSeller>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ItemViewHolderSeller productViewHolder, int position, @NonNull Products products) {
                        productViewHolder.txtProductName.setText(products.getProduct_name());
                        productViewHolder.txtProductPrice.setText("Price = " + products.getProduct_price());
                        productViewHolder.txtProductDescription.setText(products.getProduct_description());
                        productViewHolder.txtProductStatus.setText("State : "+products.getProductState());
                        Picasso.get().load(products.getProduct_image()).into(productViewHolder.imageView);

                        final Products itemClick = products;
                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String productId = itemClick.getProduct_id();

                                String  options[] = new String []
                                        {
                                                "Yes",
                                                "No"
                                        };

                                final AlertDialog.Builder builder = new AlertDialog.Builder(SellerHomeActivity.this);
                                builder.setTitle("Do you want to delete this product ?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        if(i==0)
                                            deleteProduct(productId);
                                    }
                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ItemViewHolderSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(SellerHomeActivity.this).inflate(R.layout.seller_item_view, parent, false);
                            ItemViewHolderSeller holder = new ItemViewHolderSeller(view);

                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void deleteProduct(String productId) {
        unverifiedProductsRef.child(productId)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(SellerHomeActivity.this, "That item has been removed successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
