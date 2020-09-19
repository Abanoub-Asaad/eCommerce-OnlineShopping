package com.example.e_commerce.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Interface.ItemClickListner;
import com.example.e_commerce.R;

import org.w3c.dom.Text;

public class ItemViewHolderSeller extends RecyclerView.ViewHolder  {

    public TextView txtProductName, txtProductDescription, txtProductPrice, txtProductStatus;
    public ImageView imageView;

    public ItemViewHolderSeller(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.product_image_home_seller);
        txtProductName = (TextView) itemView.findViewById(R.id.product_name_home_seller);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price_home_seller);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description_home_seller);
        txtProductStatus = (TextView) itemView.findViewById(R.id.product_status_home_seller);
    }

}
