package com.example.android_basico_semana4.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.android_basico_semana4.network.ApiService;
import com.example.android_basico_semana4.dm.Post;
import com.example.android_basico_semana4.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private ApiService apiService;

    public PostRepository() {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    public LiveData<List<Post>> getPosts() {
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        apiService.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> resp) {
                if (resp.isSuccessful()) {
                    data.postValue(resp.body());
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }
}
