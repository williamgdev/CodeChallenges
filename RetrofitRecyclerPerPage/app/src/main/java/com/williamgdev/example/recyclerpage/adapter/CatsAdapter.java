package com.williamgdev.example.recyclerpage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.williamgdev.example.recyclerpage.R;
import com.williamgdev.example.recyclerpage.dto.CatResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.CatsViewHolder> {
    private List<CatResponse> catsList;
    private Context context;
    private int currentPosition;

    public CatsAdapterListener mListener;
    private boolean isFinal;
    private boolean isLoading;
    private boolean down;

    public CatsAdapter(Context context, List<CatResponse> catsList) {
        this.catsList = catsList;
        this.context = context;
    }

    public void setCatsAdapterListener(CatsAdapterListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public CatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.data_item, parent, false);
        return new CatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatsViewHolder holder, final int position) {
        currentPosition = position;
        holder.setItem(catsList.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (!isLoading && (firstVisibleItemPosition + visibleItemCount) >= totalItemCount && dy > 0) {
                    mListener.loadMoreData();
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return catsList.size();
    }

    public void addCatList(List<CatResponse> catsResponseList) {
        catsList.addAll(catsResponseList);
        isLoading = false;
    }

    public class CatsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_date)
        TextView txtDate;

        @BindView(R.id.item_title)
        TextView txtTitle;

        @BindView(R.id.item_description)
        TextView txtDescription;

        @BindView(R.id.item_image)
        ImageView imageView;


        public CatsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setItem(CatResponse catResponse) {
            txtDate.setText(catResponse.getTimestamp());
            txtTitle.setText(catResponse.getTitle());
            txtDescription.setText(catResponse.getDescription());
            Glide.with(context)
                    .load(catResponse.getImageUrl())
                    .into(imageView);
        }
    }

    public interface CatsAdapterListener {

        void loadMoreData();
    }
}
