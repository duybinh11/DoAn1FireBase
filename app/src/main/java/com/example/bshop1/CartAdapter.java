package com.example.bshop1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import Model.ItemCart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{
    List<ItemCart> itemCartList;
    public CartAdapter(List<ItemCart> itemCartList){
        this.itemCartList = itemCartList;
    }
    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item_cart,parent,false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        ItemCart itemCart = itemCartList.get(position);
        holder.tvName.setText(itemCart.getItemNew().getName());
        Glide.with(holder.itemView.getContext())
                .load(itemCart.getItemNew().getImg())
                .into(holder.img);
        holder.tvName.setText(itemCart.getItemNew().getName());
        holder.tvDate.setText(itemCart.getDate());
        holder.tvMoney.setText(String.valueOf(itemCart.getSLM()*itemCart.getItemNew().getCost())+"k");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),StatusItem.class);
                intent.putExtra("itemCart",itemCart);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCartList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvMoney,tvDate;
        ImageView img;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img4);
            tvName = itemView.findViewById(R.id.tvName4);
            tvMoney = itemView.findViewById(R.id.tvMoney);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
