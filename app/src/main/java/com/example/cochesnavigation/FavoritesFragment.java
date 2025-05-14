package com.example.cochesnavigation;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private final List<Car> carList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        initializeCarList();
        setupRecyclerView(view);

        return view;
    }

    private void initializeCarList() {
        carList.clear();
        carList.add(new Car("Porsche 911 Carrera", "GBP 98,700.00", "2021", R.drawable.porsche_911));
        carList.add(new Car("Mercedes SLS AMG", "GBP 98,700.00", "2018", R.drawable.mercedes_sls));
        carList.add(new Car("840i Coupe M Sport", "GBP 82,000.00", "2018", R.drawable.bmw_840i));
        carList.add(new Car("Mercedes-Benz ML 400", "GBP 98,700.00", "2018", R.drawable.mercedes_ml));
        carList.add(new Car("McLaren 720S Coupe", "GBP 230,500.00", "2018", R.drawable.mclaren_720s));
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteAdapter(carList);
        recyclerView.setAdapter(adapter);

        // Swipe to delete functionality
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                carList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(recyclerView);
    }
}