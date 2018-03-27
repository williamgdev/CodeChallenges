package com.williamgdev.example.earthquakecodetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.williamgdev.example.earthquakecodetest.R;
import com.williamgdev.example.earthquakecodetest.model.EarthQuake;
import com.williamgdev.example.earthquakecodetest.view.HomeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.EarthQuakeViewHolder> {
    private List<EarthQuake> earthQuakeList;
    private Context context;
    private final HomeView view;

    public EarthQuakeAdapter(Context context, HomeView view, List<EarthQuake> earthQuakeList) {
        this.earthQuakeList = earthQuakeList;
        this.context = context;
        this.view = view;
    }

    @Override
    public EarthQuakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.data_item, parent, false);
        return new EarthQuakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarthQuakeViewHolder holder, final int position) {
        holder.setItem(earthQuakeList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.onEarthQuakeSelected(earthQuakeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return earthQuakeList.size();
    }

    public class EarthQuakeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_date)
        TextView txtDate;

        @BindView(R.id.item_place)
        TextView txtPlace;

        @BindView(R.id.item_magnitude)
        TextView txtMagnitude;


        public EarthQuakeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setItem(EarthQuake earthQuake) {
            txtDate.setText(earthQuake.getTime() + "");
            txtMagnitude.setText(earthQuake.getMagnitude() + "");
            txtPlace.setText(earthQuake.getPlace());
        }
    }
}
