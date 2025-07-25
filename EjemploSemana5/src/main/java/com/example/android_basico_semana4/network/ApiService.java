package com.example.android_basico_semana4.network;



import com.example.android_basico_semana4.dm.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("posts")
    Call<List<Post>> getPosts();
}
