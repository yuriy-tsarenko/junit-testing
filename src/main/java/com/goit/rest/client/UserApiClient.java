package com.goit.rest.client;

import com.goit.dto.UserDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.util.List;

public class RestClientIml implements RestClient {

    private final OkHttpClient client = new OkHttpClient();
    Request.Builder requestBuilder = new Request.Builder();

    @Override
    public List<UserDto> get(String url) {
        
        return null;
    }

    @Override
    public UserDto post(String url) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
