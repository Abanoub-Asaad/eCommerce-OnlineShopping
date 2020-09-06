package com.example.e_commerce.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;

import org.w3c.dom.Text;

public class CartViewHolder extends RecyclerView.ViewHolder {

    public TextView txtProductName, txtProductPrice, txtProductQuantity;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = (TextView) itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = (TextView) itemView.findViewById(R.id.cart_product_price);
        txtProductQuantity = (TextView) itemView.findViewById(R.id.cart_product_quantity);
    }
}
