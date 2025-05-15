package com.example.cochesnavigation;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        initializeCarList();
        setupRecyclerView(view);

        return view;
    }

    /** Datos de prueba */
    private void initializeCarList() {
        carList.clear();
        carList.add(new Car("Porsche 911 Carrera", "GBP 98,700.00", "2021", R.drawable.porsche_911));
        carList.add(new Car("Mercedes SLS AMG", "GBP 98,700.00", "2018", R.drawable.mercedes_sls));
        carList.add(new Car("840i Coupe M Sport", "GBP 82,000.00", "2018", R.drawable.bmw_840i));
        carList.add(new Car("Mercedes-Benz ML 400", "GBP 98,700.00", "2018", R.drawable.mercedes_ml));
        carList.add(new Car("McLaren 720S Coupe", "GBP 230,500.00", "2018", R.drawable.mclaren_720s));
    }

    /** Configura RecyclerView + swipe-to-delete */
    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteAdapter(carList);
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false; // no drag-and-drop
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getBindingAdapterPosition();
                carList.remove(position);
                adapter.notifyItemRemoved(position);
                // (Opcional: aquí puedes mostrar Snackbar para "Deshacer")
            }

            /** Dibuja fondo rojo + icono papelera mientras se desliza */
            @Override
            public void onChildDraw(@NonNull Canvas c,
                                    @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {

                View item = viewHolder.itemView;

                if (dX < 0) {                 // solo al deslizar a la izquierda
                    // 1. Fondo rojo
                    Paint paint = new Paint();
                    // Usa tu propio color (R.color.swipe_red) o un rojo fijo:
                    paint.setColor(ContextCompat.getColor(requireContext(), R.color.swipe_red));
                    RectF rect = new RectF(item.getRight() + dX, item.getTop(),
                            item.getRight(),     item.getBottom());
                    c.drawRect(rect, paint);

                    // 2. Icono papelera
                    Drawable icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete_24);
                    if (icon != null) {
                        int iconMargin = (item.getHeight() - icon.getIntrinsicHeight()) / 2;
                        int iconTop    = item.getTop() + iconMargin;
                        int iconBottom = iconTop + icon.getIntrinsicHeight();
                        int iconLeft   = item.getRight() - iconMargin - icon.getIntrinsicWidth();
                        int iconRight  = item.getRight() - iconMargin;
                        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                        icon.draw(c);
                    }
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY,
                        actionState, isCurrentlyActive);
            }

        }).attachToRecyclerView(recyclerView);
    }
}
