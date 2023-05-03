package com.example.bshop1;

import android.annotation.SuppressLint;
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
import java.util.Locale;

import Model.ItemNew;

public class NewAdapter extends  RecyclerView.Adapter<NewAdapter.NewHolder>{

    List<ItemNew> itemList;

    public NewAdapter(List<ItemNew> itemList){

        this.itemList = itemList;
    }
    public void setFilterList(List<ItemNew> itemList){
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewnew,parent,false);
        return new NewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemNew item = itemList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getImg())
                .into(holder.img);
        holder.tvName.setText(item.getName());
        holder.tvCost.setText(String.valueOf(item.getCost())+"k");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),ItemSelected.class);
                intent.putExtra("itemNew",item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class NewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName,tvCost;
        public NewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tvName);
            tvCost = itemView.findViewById(R.id.tvCost);
        }
    }
}
