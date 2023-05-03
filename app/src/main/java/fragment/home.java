package fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bshop1.BanChayAdapter;
import com.example.bshop1.NewAdapter;
import com.example.bshop1.R;
import com.example.bshop1.Search;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import Model.ItemCommon;
import Model.ItemNew;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends Fragment {
    ImageSlider imsl;
    ImageView imgSearch;
    RecyclerView rccvBanChay,rccvNew;
    SwipeRefreshLayout swipeRefreshLayout;
    NewAdapter newAdapter;
    BanChayAdapter banChayAdapter;
    List<SlideModel> slideModelList;
    List<ItemCommon> itemCommonListSlide;
    List<ItemNew> itemBanChayList;
    List<ItemNew> itemNewList;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        initImageSlide();
        initBanChay();
        initNew();
        clickSearch();
        clickImageSlide();
        swipeRefreshLayout.setOnRefreshListener(lamMoi);
    }

    private void clickSearch() {
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Search.class);
                startActivity(intent);
            }
        });
    }

    public void anhXa(View view){
        rccvNew = view.findViewById(R.id.rccvNew);
        rccvBanChay = view.findViewById(R.id.rccvBanChay);
        imsl = view.findViewById(R.id.imsl);
        imgSearch = view.findViewById(R.id.imgSearch);
        slideModelList = new ArrayList<>();
        swipeRefreshLayout = view.findViewById(R.id.refresh);
        itemCommonListSlide = new ArrayList<>();
        itemNewList = new ArrayList<>();
        itemBanChayList = new ArrayList<>();
    }
    public void initImageSlide(){
        mDatabase = FirebaseDatabase.getInstance().getReference("list_item_slide");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemCommonListSlide.clear();
                slideModelList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ItemCommon itemCommon = postSnapshot.getValue(ItemCommon.class);
                    itemCommonListSlide.add(itemCommon);
                    slideModelList.add(new SlideModel(itemCommon.getImg(),null));
                }
                imsl.setImageList(slideModelList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initBanChay(){
        banChayAdapter = new BanChayAdapter(itemBanChayList);
        setDataBanChay();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rccvBanChay.setLayoutManager(linearLayoutManager);
        rccvBanChay.setAdapter(banChayAdapter);
    }
    public void initNew(){
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        newAdapter = new NewAdapter(itemNewList);
        setDataItemNew();
        rccvNew.setLayoutManager(linearLayoutManager);
        rccvNew.setAdapter(newAdapter);
    }
    SwipeRefreshLayout.OnRefreshListener lamMoi = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Toast.makeText(getContext(), "loading", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    };
    public void clickImageSlide(){
        imsl.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(getContext(), itemCommonListSlide.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void doubleClick(int i) {
                Toast.makeText(getContext(), "2 lan", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setDataItemNew(){
        mDatabase = FirebaseDatabase.getInstance().getReference("list_item");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemNewList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   ItemNew itemNew = postSnapshot.getValue(ItemNew.class);
                    itemNewList.add(0,itemNew);
                }
                newAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setDataBanChay(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("list_item");
        Query query = ref.orderByChild("sold");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                itemBanChayList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ItemNew itemNew = postSnapshot.getValue(ItemNew.class);
                    itemBanChayList.add(0,itemNew);
                    i++;
                    if(i==4){
                        break;
                    }
                }
                banChayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }



}