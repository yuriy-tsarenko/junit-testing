package com.goit.rest.client;

import com.goit.dto.UserDto;
import com.goit.rest.client.types.UserListType;
import okhttp3.Request;

import java.util.List;

public class UserApiClient extends RestClient<UserDto, List<UserDto>> {
    public static final String USER_ENDPOINT = "/users";

    public UserApiClient(String baseUrl) {
        super(baseUrl, UserDto.class, new UserListType());
    }

    public List<UserDto> get() {
        Request.Builder request = createRequest(USER_ENDPOINT);
        return executeAndGetList(request.build());
    }

    @Override
    public UserDto post(UserDto user) {
        Request request = createRequest(USER_ENDPOINT)
                .post(createBody(user))
                .build();
        return execute(request);
    }

    @Override
    public void delete(Long id) {
        Request request = createRequest(USER_ENDPOINT + "/" + id)
                .delete()
                .build();
        executeVoid(request);
    }
}
