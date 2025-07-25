package com.example.android_basico_semana4.Adapter;


import com.example.android_basico_semana4.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.android_basico_semana4.dm.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostVH> {
    private List<Post> items = new ArrayList<>();

    public void setItems(List<Post> posts) {
        this.items = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostVH holder, int position) {
        Post p = items.get(position);
        holder.tvTitle.setText(p.getTitle());
        holder.tvBody.setText(p.getBody());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class PostVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        PostVH(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody  = itemView.findViewById(R.id.tvBody);
        }
    }
}
