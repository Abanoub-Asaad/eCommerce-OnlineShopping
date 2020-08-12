package com.example.test;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ProductViewHolder extends RecyclerView.ViewHolder  {

    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.product_image_home);
        txtProductName = (TextView) itemView.findViewById(R.id.product_name_home);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price_home);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description_home);
    }
}
