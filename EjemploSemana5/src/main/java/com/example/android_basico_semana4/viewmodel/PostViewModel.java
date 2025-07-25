package com.example.android_basico_semana4.viewmodel;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_basico_semana4.dm.Post;
import com.example.android_basico_semana4.repository.PostRepository;

import java.util.List;

public class PostViewModel extends ViewModel {
    private PostRepository repo;
    private LiveData<List<Post>> posts;

    public PostViewModel() {
        repo = new PostRepository();
        posts = repo.getPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }
}
