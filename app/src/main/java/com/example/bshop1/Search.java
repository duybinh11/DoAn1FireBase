package com.example.bshop1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Search extends AppCompatActivity {
    SearchView searchView;
    RecyclerView rccvSearch;
    NewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        anhXa();
        initRccvSearch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
    }
    public void anhXa(){
        searchView = findViewById(R.id.searchView);
        rccvSearch = findViewById(R.id.rccvSearch);

    }
    public void initRccvSearch(){
//        itemList.add(new Item(1 ,"Clother",R.drawable.clother));
//        itemList.add(new Item(2,"Banh",R.drawable.food));
//        itemList.add(new Item(1 ,"Ao",R.drawable.clother));
//        itemList.add(new Item(2,"Ao khoac",R.drawable.food));
//        itemList.add(new Item(1 ,"Com ga",R.drawable.clother));
//        itemList.add(new Item(2,"com chien",R.drawable.food));
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        adapter = new NewAdapter(itemList);
//        rccvSearch.setLayoutManager(linearLayoutManager);
//        rccvSearch.setAdapter(adapter);
    }
    public void filterList(String data){
//        List<Item> list = new ArrayList<>();
//        for(Item item : itemList){
//            if(item.getName().toLowerCase().contains(data)){
//                list.add(item);
//            }
//        }
//        adapter.setFilterList(list);

    }
}