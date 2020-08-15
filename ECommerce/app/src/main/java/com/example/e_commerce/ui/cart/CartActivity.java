package com.example.e_commerce.ui.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.HomeActivity;
import com.example.e_commerce.Model.CartList;
import com.example.e_commerce.Prevalent.Prevalent;
import com.example.e_commerce.ProductDetailsActivity;
import com.example.e_commerce.R;
import com.example.e_commerce.ViewHolder.CartViewHolder;
import com.example.e_commerce.ViewHolder.ProductViewHolder;
import com.example.e_commerce.ui.settings.SettingsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartListRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button nextBtn;
    private TextView txtTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toast.makeText(CartActivity.this, "Cart", Toast.LENGTH_SHORT).show();

        cartListRecyclerView = (RecyclerView) findViewById(R.id.cart_list_recyclerView);
        cartListRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        cartListRecyclerView.setLayoutManager(layoutManager);

        nextBtn = (Button) findViewById(R.id.next_btn);
        txtTotalPrice = (TextView) findViewById(R.id.total_price);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<CartList> options =
                new FirebaseRecyclerOptions.Builder<CartList>()
                        .setQuery(cartListRef.child("User View")
                                .child(Prevalent.currentOnlineUser.getPhone()).child("Products"), CartList.class).build();

        FirebaseRecyclerAdapter<CartList, CartViewHolder> adapter =
                new FirebaseRecyclerAdapter<CartList, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position, @NonNull CartList model) {

                        cartViewHolder.txtProductName.setText(model.getProduct_name());
                        cartViewHolder.txtProductPrice.setText("Price/One = "+model.getProduct_price());
                        cartViewHolder.txtProductQuantity.setText("Quantity : "+model.getQuantity());

                        cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(CartActivity.this).inflate(R.layout.cart_items_layout, parent, false);
                        CartViewHolder holder = new CartViewHolder(view);

                        return holder;
                    }
                };
        cartListRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CartActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
