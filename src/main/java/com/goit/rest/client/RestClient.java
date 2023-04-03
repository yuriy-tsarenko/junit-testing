package com.goit.rest.client;

import com.goit.rest.client.exception.RestClientException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import okhttp3.*;

import java.util.List;

@RequiredArgsConstructor
public abstract class RestClient<T, R> {
    protected final String baseUrl;
    protected final Class<T> dtoType;
    protected final TypeToken<R> dtoListType;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    protected final OkHttpClient client = new OkHttpClient();
    private final Request.Builder requestBuilder = new Request.Builder();
    private final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    public abstract List<T> get();

    public abstract T post(T object);

    public abstract void delete(Long id);

    protected Request.Builder createRequest(String endpoint) {
        return requestBuilder.url(baseUrl + endpoint);
    }

    protected T execute(Request request) {
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return gson.fromJson(json, dtoType);
        } catch (Exception e) {
            throw new RestClientException();
        }
    }

    protected R executeAndGetList(Request request) {
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return gson.fromJson(json, dtoListType);
        } catch (Exception e) {
            throw new RestClientException();
        }
    }

    protected RequestBody createBody(T object){
        return RequestBody.create(gson.toJson(object), JSON);
    }
}
