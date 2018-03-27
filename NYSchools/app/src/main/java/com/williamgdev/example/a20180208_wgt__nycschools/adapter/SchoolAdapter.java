package com.williamgdev.example.a20180208_wgt__nycschools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.williamgdev.example.a20180208_wgt__nycschools.R;
import com.williamgdev.example.a20180208_wgt__nycschools.model.School;
import com.williamgdev.example.a20180208_wgt__nycschools.view.HomeView;

import java.util.Collections;
import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolsViewHolder> {
    private List<School> schools;
    private Context context;
    private final HomeView view;

    public SchoolAdapter(Context context, HomeView view, List<School> schools) {
        Collections.sort(schools, School.getComparator());
        this.schools = schools;
        this.context = context;
        this.view = view;
    }

    @Override
    public SchoolsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.schools_item, parent, false);
        return new SchoolsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchoolsViewHolder holder, final int position) {
        holder.setName(schools.get(position).getSchoolName());
        holder.setNeighborhood(schools.get(position).getNeighborhood());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.onSchoolSelected(schools.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return schools.size();
    }

    public class SchoolsViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;
        private final TextView txtNeighborhood;

        public SchoolsViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.schools_name);
            txtNeighborhood = view.findViewById(R.id.schools_neighborhood);
        }

        public void setName(String name) {
            txtName.setText(name);
        }

        public void setNeighborhood(String neighborhood) {
            txtNeighborhood.setText(neighborhood);
        }
    }
}
