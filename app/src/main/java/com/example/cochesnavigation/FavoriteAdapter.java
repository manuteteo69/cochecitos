package com.example.cochesnavigation;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final List<Car> carList;

    public FavoriteAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.carName.setText(car.getName());
        holder.carPrice.setText(car.getPrice());
        holder.carYear.setText(car.getYear());
        holder.carImage.setImageResource(car.getImageResId());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView carImage;
        final TextView carName;
        final TextView carPrice;
        final TextView carYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.car_image);
            carName = itemView.findViewById(R.id.car_name);
            carPrice = itemView.findViewById(R.id.car_price);
            carYear = itemView.findViewById(R.id.car_year);
        }
    }
}