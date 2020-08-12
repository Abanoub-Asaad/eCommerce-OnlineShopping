package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference ProductRef;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Products> arrayList;
    private FirebaseRecyclerOptions<Products> options;
    private FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter;
    private Products model = new Products();

    @Override
    protected void onStart() {
        adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductRef = FirebaseDatabase.getInstance().getReference().child("Products");
        ProductRef.keepSynced(true);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<Products>();

        options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductRef, Products.class)
                        .build();

        adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull Products products) {

                        productViewHolder.txtProductName.setText(model.getProduct_name());
                        productViewHolder.txtProductPrice.setText("Price = " + model.getProduct_price());
                        productViewHolder.txtProductDescription.setText(model.getProduct_description());

                        Picasso.get().load(model.getProduct_image()).into(productViewHolder.imageView);

                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        return new ProductViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.product_items_layout,parent,false));

                    }
                };
        recyclerView.setAdapter(adapter);
    }


}
