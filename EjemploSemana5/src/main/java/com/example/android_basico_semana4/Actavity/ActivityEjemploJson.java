package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_basico_semana4.Adapter.PostAdapter;
import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.viewmodel.PostViewModel;

public class ActivityEjemploJson extends AppCompatActivity {

    private PostViewModel viewModel;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo_json);

        // RecyclerView
        RecyclerView rv = findViewById(R.id.recyclerViewPosts);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter();
        rv.setAdapter(adapter);

        // ViewModel + LiveData
        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
        viewModel.getPosts().observe(this, posts -> {
            if (posts != null) {
                adapter.setItems(posts);
            }
        });



    }
}